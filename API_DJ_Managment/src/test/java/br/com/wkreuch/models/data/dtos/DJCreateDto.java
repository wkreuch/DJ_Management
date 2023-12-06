package br.com.wkreuch.models.data.dtos;

import jakarta.persistence.Embedded;

import java.time.LocalDate;
import java.util.Objects;

public class DJCreateDto {

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private String artistName;

    private String countryIdRegistration;

    @Embedded
    private AddressCreateDto address;

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

    public AddressCreateDto getAddress() {
        return address;
    }

    public void setAddress(AddressCreateDto address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DJCreateDto that = (DJCreateDto) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(birthDate, that.birthDate) && Objects.equals(artistName, that.artistName) && Objects.equals(countryIdRegistration, that.countryIdRegistration) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, birthDate, artistName, countryIdRegistration, address);
    }
}
