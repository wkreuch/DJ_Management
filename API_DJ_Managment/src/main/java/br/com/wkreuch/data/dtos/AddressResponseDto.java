package br.com.wkreuch.data.dtos;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class AddressResponseDto {

    private String street;

    private Long number;

    private String complement;

    private String district;

    private String city;

    private String state;

    private String postalCode;

    private String country;

    public AddressResponseDto() {
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressResponseDto address = (AddressResponseDto) o;
        return Objects.equals(street, address.street) && Objects.equals(number, address.number) && Objects.equals(complement, address.complement) && Objects.equals(district, address.district) && Objects.equals(city, address.city) && Objects.equals(state, address.state) && Objects.equals(postalCode, address.postalCode) && Objects.equals(country, address.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, number, complement, district, city, state, postalCode, country);
    }
}
