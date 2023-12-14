package br.com.wkreuch.utils.mapper;

import br.com.wkreuch.data.dtos.PortfolioCreateDto;
import br.com.wkreuch.data.dtos.PortfolioResponseDto;
import br.com.wkreuch.data.dtos.PortfolioUpdateDto;
import br.com.wkreuch.models.Portfolio;

import java.util.List;

public class PortfolioMapper extends BeanUtilsMapper {

    public static PortfolioResponseDto convert(Portfolio portfolio, boolean hateoas) {
        PortfolioResponseDto portfolioResponseDto = new PortfolioResponseDto();
        copyProperties(portfolio, portfolioResponseDto);
        portfolioResponseDto.setIdDj(portfolio.getDj().getIdDj());
        if (hateoas) portfolioResponseDto.addHateosLink();

        return portfolioResponseDto;
    }
    public static Portfolio convert(PortfolioCreateDto portfolioCreateDto) {
        Portfolio portfolio = new Portfolio();
        copyProperties(portfolioCreateDto, portfolio);

        return portfolio;
    }
    public static Portfolio convert(PortfolioUpdateDto portfolioUpdateDto) {
        Portfolio portfolio = new Portfolio();
        copyProperties(portfolioUpdateDto, portfolio);

        return portfolio;
    }
    public static List<PortfolioResponseDto> convert(List<Portfolio> portfolios, boolean hateoas) {
        return portfolios.stream().map(portfolio -> convert(portfolio, hateoas)).toList();
    }

}
