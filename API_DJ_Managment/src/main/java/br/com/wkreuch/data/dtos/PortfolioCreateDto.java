package br.com.wkreuch.data.dtos;

import br.com.wkreuch.models.enums.TypePortfolio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class PortfolioCreateDto {

    @Size(max = 2000)
    private String link;

    @Size(max = 500)
    private String description;

    @NotBlank
    private TypePortfolio typePortfolio;

    @NotNull
    private Long idDj;

    public PortfolioCreateDto() {
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

    public Long getIdDj() {
        return idDj;
    }

    public void setIdDj(Long idDj) {
        this.idDj = idDj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PortfolioCreateDto that = (PortfolioCreateDto) o;
        return Objects.equals(link, that.link) && Objects.equals(description, that.description) && typePortfolio == that.typePortfolio && Objects.equals(idDj, that.idDj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link, description, typePortfolio, idDj);
    }
}
