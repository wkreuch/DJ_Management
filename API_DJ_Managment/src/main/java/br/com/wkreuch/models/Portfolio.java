package br.com.wkreuch.models;

import br.com.wkreuch.models.enums.TypePortfolio;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "portfolios")
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPortfolio;

    @Column(length = 2000)
    private String link;

    @Column(length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private TypePortfolio typePortfolio;

    @ManyToOne
    @JoinColumn(name = "idDj", nullable = false)
    private DJ dj;

    public Portfolio() {
    }

    public Long getIdPortfolio() {
        return idPortfolio;
    }

    public void setIdPortfolio(Long idPortfolio) {
        this.idPortfolio = idPortfolio;
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

    public DJ getDj() {
        return dj;
    }

    public void setDj(DJ dj) {
        this.dj = dj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Portfolio portfolio = (Portfolio) o;
        return Objects.equals(idPortfolio, portfolio.idPortfolio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPortfolio);
    }
}
