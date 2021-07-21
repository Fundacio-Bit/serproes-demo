package es.caib.serproes.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name="ser_illa",
        indexes = {
                @Index(name = "ser_illa_pk", columnList = "ILA_CODI", unique = true),
                @Index(name = "ser_illa_nom_i", columnList = "ILA_NOM")})

public class IllaEntity {
    private long ilaCodi;
    private String ilaNom;

    @Id
    @GeneratedValue
    @Column(name="ILA_CODI")
    public long getIlaCodi() {
        return ilaCodi;
    }

    public void setIlaCodi(long ilaCodi) {
        this.ilaCodi = ilaCodi;
    }

    @Column(name="ILA_NOM")
    public String getIlaNom() {
        return ilaNom;
    }

    public void setIlaNom(String ilaNom) {
        this.ilaNom = ilaNom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IllaEntity)) return false;
        IllaEntity that = (IllaEntity) o;
        return ilaCodi == that.ilaCodi &&
                Objects.equals(ilaNom, that.ilaNom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ilaCodi, ilaNom);
    }
}
