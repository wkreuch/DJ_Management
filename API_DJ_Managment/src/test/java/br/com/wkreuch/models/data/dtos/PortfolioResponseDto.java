package br.com.wkreuch.models.data.dtos;

import br.com.wkreuch.models.enums.TypePortfolio;

import java.io.Serializable;

public record PortfolioResponseDto(Long idPortfolio, String link, String description, TypePortfolio typePortfolio, Long idDj) implements Serializable {
}
