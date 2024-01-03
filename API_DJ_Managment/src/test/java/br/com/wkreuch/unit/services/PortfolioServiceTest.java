package br.com.wkreuch.unit.services;

import br.com.wkreuch.exceptions.ErrorCode;
import br.com.wkreuch.exceptions.RequiredObjectIsNullException;
import br.com.wkreuch.exceptions.ResourceNotFoundException;
import br.com.wkreuch.models.DJ;
import br.com.wkreuch.models.Portfolio;
import br.com.wkreuch.models.enums.TypePortfolio;
import br.com.wkreuch.models.mock.DJMock;
import br.com.wkreuch.models.mock.PortfolioMock;
import br.com.wkreuch.repositories.PortfolioRepository;
import br.com.wkreuch.services.DJService;
import br.com.wkreuch.services.PortfolioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class PortfolioServiceTest {

    public static final long ID_DJ = 1L;
    PortfolioMock input;

    DJMock djMock;

    DJ dj;

    @InjectMocks
    private PortfolioService service;

    @Mock
    PortfolioRepository portfolioRepository;

    @Mock
    DJService djService;


    @BeforeEach
    void setUpMocks() {
        input = new PortfolioMock();
        djMock = new DJMock();
        dj = djMock.mockDJ(ID_DJ);

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        Portfolio portfolio = input.mockPortfolio(null, null);
        Portfolio portfolioPersisted = input.mockPortfolio(1L, dj);

        when(djService.findById(ID_DJ)).thenReturn(dj);
        when(portfolioRepository.save(portfolio)).thenReturn(portfolioPersisted);

        var result = service.create(portfolio, ID_DJ);

        assertNotNull(result);
        assertNotNull(result.getIdPortfolio());

        assertEquals(1L, result.getIdPortfolio());
        assertEquals("Test mock portfolio 1", result.getDescription());
        assertEquals("a/picture.pgn", result.getLink());
        assertEquals(TypePortfolio.PICTURE, result.getTypePortfolio());
        assertEquals(dj.getIdDj(), result.getDj().getIdDj());

    }

    @Test
    void testUpdate() {
        Portfolio portfolioWithChanges = input.mockPortfolio(1L, dj);
        portfolioWithChanges.setDescription("Description updated 1");
        portfolioWithChanges.setLink("Link updated 1");
        portfolioWithChanges.setTypePortfolio(TypePortfolio.MUSIC);

        Portfolio portfolioPersisted = input.mockPortfolio(1L, dj);

        when(portfolioRepository.findById(1L)).thenReturn(Optional.of(portfolioPersisted));
        when(portfolioRepository.save(portfolioPersisted)).thenReturn(portfolioPersisted);

        var result = service.update(1L, portfolioWithChanges);

        assertNotNull(result);
        assertNotNull(result.getIdPortfolio());

        assertEquals(1L, result.getIdPortfolio());
        assertEquals("Description updated 1", result.getDescription());
        assertEquals("Link updated 1", result.getLink());
        assertEquals(TypePortfolio.MUSIC, result.getTypePortfolio());
        assertEquals(dj.getIdDj(), result.getDj().getIdDj());

    }

    @Test
    void testFindByID() {
        Portfolio portfolioPersisted = input.mockPortfolio(1L, dj);

        when(portfolioRepository.findById(1L)).thenReturn(Optional.of(portfolioPersisted));

        var result = service.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getIdPortfolio());

        assertEquals(1L, result.getIdPortfolio());
        assertEquals("Test mock portfolio 1", result.getDescription());
        assertEquals("a/picture.pgn", result.getLink());
        assertEquals(TypePortfolio.PICTURE, result.getTypePortfolio());
        assertEquals(dj.getIdDj(), result.getDj().getIdDj());

    }

    @Test
    void testDelete() {
        var id = 22L;
        Portfolio portfolioPersisted = input.mockPortfolio(id, dj);
        when(portfolioRepository.findById(id)).thenReturn(Optional.of(portfolioPersisted));

        service.delete(id);
    }

    @Test
    void testCreateWithNullPortfolio() {
        assertThrows(RequiredObjectIsNullException.class, () -> {
            service.create(null,null);
        });

        Portfolio portfolio = input.mockPortfolio(null, null);

        assertThrows(RequiredObjectIsNullException.class, () -> {
            service.create(portfolio,null);
        });
    }

    @Test
    void testFindByIdWrong() {

        when(portfolioRepository.findById(123L)).thenThrow(new ResourceNotFoundException(ErrorCode.NOT_FOUND.getMessage()));

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            service.findById(123L);
        });

        String expectedMessage = "Not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testUpdateWithNullPortfolio() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.update(1L, null);
        });

        String expectedMessage = "It is not allowed to persist a null object!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
