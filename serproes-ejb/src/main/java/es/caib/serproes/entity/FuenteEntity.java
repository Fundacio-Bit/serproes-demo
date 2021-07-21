package es.caib.serproes.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name = "SER_FUENTE",
        indexes = {
                @Index(name = "ser_fuente_pk_i", columnList = "FUE_CODI", unique = true),
                @Index(name = "ser__fuente_autor_i", columnList = "FUE_AUTOR"),
                @Index(name = "ser_fuente_refere_i", columnList = "FUE_REFERE")})

public class FuenteEntity {
    private Long fueCodi;
    private String fueAutor;
    private Timestamp fueAnyo;
    private String fueRefere;
    private Integer fueCodtif;
    private String fueBorrad;

    @Id
    @Column(name = "FUE_CODI")
    public Long getFueCodi() {
        return fueCodi;
    }

    public void setFueCodi(Long fueCodi) {
        this.fueCodi = fueCodi;
    }

    @Column(name = "FUE_AUTOR")
    public String getFueAutor() {
        return fueAutor;
    }

    public void setFueAutor(String fueAutor) {
        this.fueAutor = fueAutor;
    }

    @Column(name = "FUE_ANYO")
    public Timestamp getFueAnyo() {
        return fueAnyo;
    }

    public void setFueAnyo(Timestamp fueAnyo) {
        this.fueAnyo = fueAnyo;
    }

    @Column(name = "FUE_REFERE", length = 500, nullable = false)
    public String getFueRefere() {
        return fueRefere;
    }

    public void setFueRefere(String fueRefere) {
        this.fueRefere = fueRefere;
    }

    @Column(name = "FUE_CODTIF")
    public Integer getFueCodtif() {
        return fueCodtif;
    }

    public void setFueCodtif(Integer fueCodtif) {
        this.fueCodtif = fueCodtif;
    }

    @Column(name = "FUE_BORRAD", columnDefinition = "VARCHAR(1) CHECK (FUE_BORRAD IN ('S','N'))")
    public String getFueBorrad() {
        return fueBorrad;
    }

    public void setFueBorrad(String fueBorrad) {
        this.fueBorrad = fueBorrad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuenteEntity that = (FuenteEntity) o;
        return Objects.equals(fueCodi, that.fueCodi) &&
                Objects.equals(fueAutor, that.fueAutor) &&
                Objects.equals(fueAnyo, that.fueAnyo) &&
                Objects.equals(fueRefere, that.fueRefere) &&
                Objects.equals(fueCodtif, that.fueCodtif) &&
                Objects.equals(fueBorrad, that.fueBorrad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fueCodi, fueAutor, fueAnyo, fueRefere, fueCodtif, fueBorrad);
    }
}
