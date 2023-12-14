package br.com.wkreuch.models;

import br.com.wkreuch.data.vos.AddressVo;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "djs")
public class DJ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idDj;

    @Column(name = "first_name", length = 125, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 125, nullable = false)
    private String lastName;

    @Column(name = "birth_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate birthDate;

    @Column(name = "artist_name", length = 50, nullable = false)
    private String artistName;

    @Column(name = "country_id_registration", length = 50, nullable = false)
    private String countryIdRegistration;

    @Embedded
    private AddressVo address;

    @OneToMany(mappedBy = "dj", cascade = CascadeType.ALL)
    private List<Portfolio> portfolios;
    public DJ() {
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

    public AddressVo getAddress() {
        return address;
    }

    public void setAddress(AddressVo address) {
        this.address = address;
    }

    public List<Portfolio> getPortfolios() {
        return portfolios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DJ dj = (DJ) o;
        return Objects.equals(idDj, dj.idDj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDj);
    }
}
