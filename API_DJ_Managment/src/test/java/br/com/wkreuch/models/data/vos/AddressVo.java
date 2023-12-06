package br.com.wkreuch.models.data.vos;

import java.util.Objects;

public class AddressVo {
    private String street;

    private Long number;

    private String complement;

    private String district;

    private String city;

    private String state;

    private String postalCode;

    private String country;

    public AddressVo() {
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
        AddressVo addressVo = (AddressVo) o;
        return Objects.equals(street, addressVo.street) && Objects.equals(number, addressVo.number) && Objects.equals(complement, addressVo.complement) && Objects.equals(district, addressVo.district) && Objects.equals(city, addressVo.city) && Objects.equals(state, addressVo.state) && Objects.equals(postalCode, addressVo.postalCode) && Objects.equals(country, addressVo.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, number, complement, district, city, state, postalCode, country);
    }
}
