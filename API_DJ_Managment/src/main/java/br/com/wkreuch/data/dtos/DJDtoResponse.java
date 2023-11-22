package br.com.wkreuch.data.dtos;

import br.com.wkreuch.controllers.DJController;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class DJDtoResponse extends RepresentationModel<DJDtoResponse> implements Serializable {

    private Long idDj;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String artistName;
    private String countryIdRegistration;

    public DJDtoResponse() {
    }

    public Long getIdDj() {
        return idDj;
    }

    public void setIdDj(Long idDj) {
        this.idDj = idDj;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getCountryIdRegistration() {
        return countryIdRegistration;
    }

    public void setCountryIdRegistration(String countryIdRegistration) {
        this.countryIdRegistration = countryIdRegistration;
    }

    public void addHateosLink() {
        this.add(linkTo(methodOn(DJController.class).findById(idDj)).withSelfRel());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DJDtoResponse that = (DJDtoResponse) o;
        return Objects.equals(idDj, that.idDj) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(birthDate, that.birthDate) && Objects.equals(artistName, that.artistName) && Objects.equals(countryIdRegistration, that.countryIdRegistration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idDj, firstName, lastName, birthDate, artistName, countryIdRegistration);
    }
}
