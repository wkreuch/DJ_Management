package br.com.wkreuch.integration.controllers;

import br.com.wkreuch.adapters.LocalDateAdapter;
import br.com.wkreuch.config.TestConfigs;
import br.com.wkreuch.config.containers.AbstractIntegration;
import br.com.wkreuch.models.data.dtos.AddressCreateDto;
import br.com.wkreuch.models.data.dtos.DJCreateDto;
import br.com.wkreuch.models.data.dtos.DJResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.shaded.com.google.common.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DJControllerTest extends AbstractIntegration {

    private static RequestSpecification specification;
    private static ObjectMapper objectMapper;

    private static DJCreateDto djCreateDto;

    private static Long idCreated;

    @BeforeAll
    public static void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.registerModule(new JavaTimeModule());

        djCreateDto = new DJCreateDto();
    }

    @Test
    @Order(2)
    public void testCreate() throws JsonProcessingException {
        mockDjCreate();

        specification = new RequestSpecBuilder()
                .setBasePath("/api/dj")
                .setPort(TestConfigs.SERVER_PORT)
                .setAccept(TestConfigs.CONTENT_TYPE_JSON)
                .setContentType(TestConfigs.CONTENT_TYPE_JSON)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        var content = given().spec(specification)
                .body(djCreateDto)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        DJResponseDto djResponseDto = objectMapper.readValue(content, DJResponseDto.class);

        assertNotNull(djResponseDto);
        assertNotNull(djResponseDto.getIdDj());

        idCreated = djResponseDto.getIdDj();

        assertEquals("Pierre David", djResponseDto.getFirstName());
        assertEquals("Guetta", djResponseDto.getLastName());
        assertEquals(LocalDate.parse("1967-10-07"), djResponseDto.getBirthDate());
        assertEquals("David Guetta", djResponseDto.getArtistName());
        assertEquals("123456", djResponseDto.getCountryIdRegistration());

        assertEquals("Elliot Courts", djResponseDto.getAddress().getStreet());
        assertEquals(634L, djResponseDto.getAddress().getNumber());
        assertEquals("Apt. 492", djResponseDto.getAddress().getComplement());
        assertEquals("Central", djResponseDto.getAddress().getDistrict());
        assertEquals("Kameronmouth", djResponseDto.getAddress().getCity());
        assertEquals("South Carolina", djResponseDto.getAddress().getState());
        assertEquals("United States", djResponseDto.getAddress().getCountry());
        assertEquals("997720868", djResponseDto.getAddress().getPostalCode());
    }

    @Test
    @Order(3)
    public void testUpdate() throws JsonProcessingException {
        djCreateDto.setFirstName("Pierre");
        djCreateDto.setCountryIdRegistration("12456");
        djCreateDto.getAddress().setCity("Tallahassee");
        djCreateDto.getAddress().setState("Florida");

        var content = given().spec(specification)
                .body(djCreateDto)
                .pathParam("id", idCreated)
                .when()
                .put("{id}")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        DJResponseDto djResponseDto = objectMapper.readValue(content, DJResponseDto.class);
        assertNotNull(djResponseDto);
        assertNotNull(djResponseDto.getIdDj());

        assertEquals(idCreated, djResponseDto.getIdDj());
        assertEquals("Pierre", djResponseDto.getFirstName());
        assertEquals("Guetta", djResponseDto.getLastName());
        assertEquals(LocalDate.parse("1967-10-07"), djResponseDto.getBirthDate());
        assertEquals("David Guetta", djResponseDto.getArtistName());
        assertEquals("12456", djResponseDto.getCountryIdRegistration());

        assertEquals("Elliot Courts", djResponseDto.getAddress().getStreet());
        assertEquals(634L, djResponseDto.getAddress().getNumber());
        assertEquals("Apt. 492", djResponseDto.getAddress().getComplement());
        assertEquals("Central", djResponseDto.getAddress().getDistrict());
        assertEquals("Tallahassee", djResponseDto.getAddress().getCity());
        assertEquals("Florida", djResponseDto.getAddress().getState());
        assertEquals("United States", djResponseDto.getAddress().getCountry());
        assertEquals("997720868", djResponseDto.getAddress().getPostalCode());
    }

    @Test
    @Order(4)
    public void testFindByID() throws JsonProcessingException {

        var content = given().spec(specification)
                .pathParam("id", idCreated)
                .when()
                .get("{id}")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        DJResponseDto djResponseDto = objectMapper.readValue(content, DJResponseDto.class);
        assertNotNull(djResponseDto);
        assertNotNull(djResponseDto.getIdDj());

        assertEquals(idCreated, djResponseDto.getIdDj());
        assertEquals("Pierre", djResponseDto.getFirstName());
        assertEquals("Guetta", djResponseDto.getLastName());
        assertEquals(LocalDate.parse("1967-10-07"), djResponseDto.getBirthDate());
        assertEquals("David Guetta", djResponseDto.getArtistName());
        assertEquals("12456", djResponseDto.getCountryIdRegistration());

        assertEquals("Elliot Courts", djResponseDto.getAddress().getStreet());
        assertEquals(634L, djResponseDto.getAddress().getNumber());
        assertEquals("Apt. 492", djResponseDto.getAddress().getComplement());
        assertEquals("Central", djResponseDto.getAddress().getDistrict());
        assertEquals("Tallahassee", djResponseDto.getAddress().getCity());
        assertEquals("Florida", djResponseDto.getAddress().getState());
        assertEquals("United States", djResponseDto.getAddress().getCountry());
        assertEquals("997720868", djResponseDto.getAddress().getPostalCode());
    }

    @Test
    @Order(5)
    public void testDelete() {

        given().spec(specification)
                .pathParam("id", idCreated)
                .when()
                .delete("{id}")
                .then()
                .statusCode(204);

    }

    @Test
    @Order(6)
    public void testFindAll() throws JsonProcessingException {

        for (int i = 0; i < 3; i++) {
            mockDjCreate();
            djCreateDto.setFirstName(djCreateDto.getFirstName() + i);

            var content = given().spec(specification)
                    .body(djCreateDto)
                    .when()
                    .post()
                    .then()
                    .statusCode(200)
                    .extract()
                    .body()
                    .asString();

            DJResponseDto djResponseDto = objectMapper.readValue(content, DJResponseDto.class);

            assertNotNull(djResponseDto);
            assertNotNull(djResponseDto.getIdDj());

        }

        var content = given().spec(specification)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        assertNotNull(content);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        Type djResponseDtoType = new TypeToken<ArrayList<DJResponseDto>>(){}.getType();
        ArrayList<DJResponseDto> djResponseDtos = gson.fromJson(content, djResponseDtoType);

        assertEquals(3,djResponseDtos.size());

    }
    private void mockDjCreate() {
        djCreateDto.setFirstName("Pierre David");
        djCreateDto.setLastName("Guetta");
        djCreateDto.setBirthDate(LocalDate.parse("1967-10-07"));
        djCreateDto.setArtistName("David Guetta");
        djCreateDto.setCountryIdRegistration("123456");

        AddressCreateDto address = new AddressCreateDto();
        address.setStreet("Elliot Courts");
        address.setNumber(634L);
        address.setComplement("Apt. 492");
        address.setDistrict("Central");
        address.setCity("Kameronmouth");
        address.setState("South Carolina");
        address.setCountry("United States");
        address.setPostalCode("997720868");
        djCreateDto.setAddress(address);
    }

}
