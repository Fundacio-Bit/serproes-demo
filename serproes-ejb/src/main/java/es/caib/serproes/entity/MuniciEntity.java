package es.caib.serproes.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name = "SER_MUNICI",
        indexes = {
                @Index(name = "ser_munici_pk_i", columnList = "MUN_CODI", unique = true),
                @Index(name = "ser_munici_nom_i", columnList = "MUN_NOM"),
                @Index(name = "ser_munici_illa_fk_i", columnList = "MUN_CODILA")})

public class MuniciEntity {
    private String munCodi;
    private String munNom;
    private IllaEntity munCodila;

    @Id
    @GeneratedValue
    @Column(name = "MUN_CODI", nullable = false, length = 3)
    public String getMunCodi() {
        return munCodi;
    }

    public void setMunCodi(String munCodi) {
        this.munCodi = munCodi;
    }

    @Column(name = "MUN_NOM", nullable = true, length = 255)
    public String getMunNom() {
        return munNom;
    }

    public void setMunNom(String munNom) {
        this.munNom = munNom;
    }

    @ManyToOne(targetEntity = IllaEntity.class)
    @JoinColumn(name = "MUN_CODILA", referencedColumnName="ILA_CODI", foreignKey = @ForeignKey(name = "SER_MUNICI_ILLA_FK"))
    public IllaEntity getMunCodila() {
        return munCodila;
    }
    public void setMunCodila(IllaEntity munCodila) {
        this.munCodila = munCodila;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MuniciEntity)) return false;
        MuniciEntity that = (MuniciEntity) o;
        return Objects.equals(munCodi, that.munCodi) &&
                Objects.equals(munNom, that.munNom) &&
                Objects.equals(munCodila, that.munCodila);
    }

    @Override
    public int hashCode() {
        return Objects.hash(munCodi, munNom, munCodila);
    }
}
