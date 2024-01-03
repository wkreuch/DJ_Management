package br.com.wkreuch.models.mock;

import br.com.wkreuch.models.DJ;
import br.com.wkreuch.models.Portfolio;
import br.com.wkreuch.models.enums.TypePortfolio;

public class PortfolioMock {

    public Portfolio mockPortfolio(Long id, DJ dj) {
        Portfolio portfolio = new Portfolio();

        portfolio.setIdPortfolio(id);
        portfolio.setDescription("Test mock portfolio " + id);
        portfolio.setTypePortfolio(TypePortfolio.PICTURE);
        portfolio.setLink("a/picture.pgn");

        portfolio.setDj(dj);

        return portfolio;
    }
}
