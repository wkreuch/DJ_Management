package br.com.wkreuch.services;

import br.com.wkreuch.models.DJ;
import br.com.wkreuch.models.mock.DJMock;
import br.com.wkreuch.repositories.DJRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
}
