package br.com.wkreuch.models.data.dtos;

import br.com.wkreuch.models.enums.TypePortfolio;

import java.io.Serializable;

public record PortfolioUpdateDto(String link, String description, TypePortfolio typePortfolio)  implements Serializable  {
}
