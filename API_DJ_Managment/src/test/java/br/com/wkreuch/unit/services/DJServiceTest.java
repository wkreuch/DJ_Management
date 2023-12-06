package br.com.wkreuch.unit.services;

import br.com.wkreuch.exceptions.ErrorCode;
import br.com.wkreuch.exceptions.RequiredObjectIsNullException;
import br.com.wkreuch.exceptions.ResourceNotFoundException;
import br.com.wkreuch.models.DJ;
import br.com.wkreuch.models.mock.DJMock;
import br.com.wkreuch.repositories.DJRepository;
import br.com.wkreuch.services.DJService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class DJServiceTest {

    DJMock input;

    @InjectMocks
    private DJService service;

    @Mock
    DJRepository repository;

    @BeforeEach
    void setUpMocks() {
        input = new DJMock();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        DJ dj = input.mockDJ(null);
        DJ djPersisted = input.mockDJ(1L);

        when(repository.save(dj)).thenReturn(djPersisted);

        var result = service.create(dj);

        assertNotNull(result);
        assertNotNull(result.getIdDj());

        assertEquals(1L, result.getIdDj());
        assertEquals("Pierre David 1", result.getFirstName());
        assertEquals("Guetta 1", result.getLastName());
        assertEquals(LocalDate.parse("1967-10-07"), result.getBirthDate());
        assertEquals("David Guetta", result.getArtistName());
        assertEquals("123456", result.getCountryIdRegistration());

        assertEquals("Elliot Courts 1", result.getAddress().getStreet());
        assertEquals(634L, result.getAddress().getNumber());
        assertEquals("Apt. 492", result.getAddress().getComplement());
        assertEquals("Central", result.getAddress().getDistrict());
        assertEquals("Kameronmouth", result.getAddress().getCity());
        assertEquals("South Carolina", result.getAddress().getState());
        assertEquals("United States", result.getAddress().getCountry());
        assertEquals("997720868", result.getAddress().getPostalCode());
    }

    @Test
    void testUpdate() {
        DJ dj = input.mockDJ(22L);
        DJ djPersisted = input.mockDJ(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(djPersisted));
        when(repository.save(djPersisted)).thenReturn(djPersisted);

        var result = service.update(1L, dj);

        assertNotNull(result);
        assertNotNull(result.getIdDj());

        assertEquals(1L, result.getIdDj());
        assertEquals("Pierre David 22", result.getFirstName());
        assertEquals("Guetta 22", result.getLastName());
        assertEquals(LocalDate.parse("1967-10-07"), result.getBirthDate());
        assertEquals("David Guetta", result.getArtistName());
        assertEquals("123456", result.getCountryIdRegistration());

        assertEquals("Elliot Courts 22", result.getAddress().getStreet());
        assertEquals(634L, result.getAddress().getNumber());
        assertEquals("Apt. 492", result.getAddress().getComplement());
        assertEquals("Central", result.getAddress().getDistrict());
        assertEquals("Kameronmouth", result.getAddress().getCity());
        assertEquals("South Carolina", result.getAddress().getState());
        assertEquals("United States", result.getAddress().getCountry());
        assertEquals("997720868", result.getAddress().getPostalCode());
    }

    @Test
    void testFindByID() {
        DJ djPersisted = input.mockDJ(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(djPersisted));

        var result = service.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getIdDj());

        assertEquals(1L, result.getIdDj());
        assertEquals("Pierre David 1", result.getFirstName());
        assertEquals("Guetta 1", result.getLastName());
        assertEquals(LocalDate.parse("1967-10-07"), result.getBirthDate());
        assertEquals("David Guetta", result.getArtistName());
        assertEquals("123456", result.getCountryIdRegistration());

        assertEquals("Elliot Courts 1", result.getAddress().getStreet());
        assertEquals(634L, result.getAddress().getNumber());
        assertEquals("Apt. 492", result.getAddress().getComplement());
        assertEquals("Central", result.getAddress().getDistrict());
        assertEquals("Kameronmouth", result.getAddress().getCity());
        assertEquals("South Carolina", result.getAddress().getState());
        assertEquals("United States", result.getAddress().getCountry());
        assertEquals("997720868", result.getAddress().getPostalCode());
    }

    @Test
    void testDelete() {
        var id = 22L;
        DJ djPersisted = input.mockDJ(id);
        when(repository.findById(id)).thenReturn(Optional.of(djPersisted));

        service.delete(id);
    }

    @Test
    void testCreateWithNullDj() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.create(null);
        });
    }

    @Test
    void testFindByIdWrong() {

        when(repository.findById(123L)).thenThrow(new ResourceNotFoundException(ErrorCode.NOT_FOUND.getMessage()));

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            service.findById(123L);
        });

        String expectedMessage = "Not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testUpdateWithNullDj() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.update(1L, null);
        });

        String expectedMessage = "It is not allowed to persist a null object!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
