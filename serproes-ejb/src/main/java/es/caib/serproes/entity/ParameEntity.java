package es.caib.serproes.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name = "SER_PARAME",
        indexes = {
                @Index(name = "ser_parame_pk_i", columnList = "PAR_CODI", unique = true)})
public class ParameEntity {
    private Integer parCodi;
    private String parNombre;
    private String parValor;
    private String parDescri;
    private String parBorrad;

    @Id
    @Column(name = "PAR_CODI")
    public Integer getParCodi() {
        return parCodi;
    }

    public void setParCodi(Integer parCodi) {
        this.parCodi = parCodi;
    }

    @Column(name = "PAR_NOMBRE")
    public String getParNombre() {
        return parNombre;
    }

    public void setParNombre(String parNombre) {
        this.parNombre = parNombre;
    }

    @Column(name = "PAR_VALOR")
    public String getParValor() {
        return parValor;
    }

    public void setParValor(String parValor) {
        this.parValor = parValor;
    }

    @Column(name = "PAR_DESCRI")
    public String getParDescri() {
        return parDescri;
    }

    public void setParDescri(String parDescri) {
        this.parDescri = parDescri;
    }

    @Column(name = "PAR_BORRAD", columnDefinition = "VARCHAR(1) CHECK (PAR_BORRAD IN ('S','N'))")
    public String getParBorrad() {
        return parBorrad;
    }

    public void setParBorrad(String parBorrad) {
        this.parBorrad = parBorrad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParameEntity that = (ParameEntity) o;
        return Objects.equals(parCodi, that.parCodi) &&
                Objects.equals(parNombre, that.parNombre) &&
                Objects.equals(parValor, that.parValor) &&
                Objects.equals(parDescri, that.parDescri) &&
                Objects.equals(parBorrad, that.parBorrad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parCodi, parNombre, parValor, parDescri, parBorrad);
    }
}
