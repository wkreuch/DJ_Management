package br.com.wkreuch.data.dtos;

import br.com.wkreuch.models.enums.TypePortfolio;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class PortfolioUpdateDto {

    @Size(max = 2000)
    private String link;

    @Size(max = 500)
    private String description;

    @NotNull
    private TypePortfolio typePortfolio;

    public PortfolioUpdateDto() {
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypePortfolio getTypePortfolio() {
        return typePortfolio;
    }

    public void setTypePortfolio(TypePortfolio typePortfolio) {
        this.typePortfolio = typePortfolio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PortfolioUpdateDto that = (PortfolioUpdateDto) o;
        return Objects.equals(link, that.link) && Objects.equals(description, that.description) && typePortfolio == that.typePortfolio;
    }

    @Override
    public int hashCode() {
        return Objects.hash(link, description, typePortfolio);
    }
}
