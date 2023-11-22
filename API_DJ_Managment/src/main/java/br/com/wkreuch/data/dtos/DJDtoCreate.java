package br.com.wkreuch.data.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Objects;

public class DJDtoCreate {

    @NotBlank
    @Size(min = 2, max = 125)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 125)
    private String lastName;

    @NotNull
    @Past
    private LocalDate birthDate;

    @NotBlank
    @Size(min = 2, max = 50)
    private String artistName;

    @NotBlank
    @Size(min = 1, max = 50)
    private String countryIdRegistration;

    public DJDtoCreate() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DJDtoCreate djDtoCreate = (DJDtoCreate) o;
        return Objects.equals(firstName, djDtoCreate.firstName) && Objects.equals(lastName, djDtoCreate.lastName) && Objects.equals(birthDate, djDtoCreate.birthDate) && Objects.equals(artistName, djDtoCreate.artistName) && Objects.equals(countryIdRegistration, djDtoCreate.countryIdRegistration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, birthDate, artistName, countryIdRegistration);
    }
}
