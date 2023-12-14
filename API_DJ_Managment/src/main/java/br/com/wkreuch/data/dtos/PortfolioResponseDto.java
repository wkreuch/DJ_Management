package br.com.wkreuch.data.dtos;

import br.com.wkreuch.controllers.DJController;
import br.com.wkreuch.models.enums.TypePortfolio;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Objects;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class PortfolioResponseDto extends RepresentationModel<DJResponseDto> implements Serializable  {

    private String link;

    private String description;

    private TypePortfolio typePortfolio;

    private Long idDj;

    public PortfolioResponseDto() {
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

    public void addHateosLink() {
        this.add(linkTo(methodOn(DJController.class).findById(idDj)).withSelfRel());
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PortfolioResponseDto that = (PortfolioResponseDto) o;
        return Objects.equals(link, that.link) && Objects.equals(description, that.description) && typePortfolio == that.typePortfolio && Objects.equals(idDj, that.idDj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link, description, typePortfolio, idDj);
    }
}
