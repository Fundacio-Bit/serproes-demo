package es.caib.serproes.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name = "SER_GRUPO",
        indexes = {
                @Index(name = "ser_grupo_pk_i", columnList = "GRU_CODI", unique = true),
                @Index(name = "ser_grupo_nombre_i", columnList = "GRU_NOMBRE"),
                @Index(name = "ser_grupo_tipo_i", columnList = "GRU_TIPO")})

public class GrupoEntity {
    private Integer gruCodi;
    private String gruNombre;
    private String gruBorrad;
    private String gruTipo;

    @Id
    @GeneratedValue
    @Column(name = "GRU_CODI")
    public Integer getGruCodi() {
        return gruCodi;
    }

    public void setGruCodi(Integer gruCodi) {
        this.gruCodi = gruCodi;
    }

    @Column(name = "GRU_NOMBRE")
    public String getGruNombre() {
        return gruNombre;
    }

    public void setGruNombre(String gruNombre) {
        this.gruNombre = gruNombre;
    }

    @Column(name = "GRU_BORRAD")
    public String getGruBorrad() {
        return gruBorrad;
    }

    public void setGruBorrad(String gruBorrad) {
        this.gruBorrad = gruBorrad;
    }

    @Column(name = "GRU_TIPO")
    public String getGruTipo() {
        return gruTipo;
    }

    public void setGruTipo(String gruTipo) {
        this.gruTipo = gruTipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrupoEntity that = (GrupoEntity) o;
        return Objects.equals(gruCodi, that.gruCodi) &&
                Objects.equals(gruNombre, that.gruNombre) &&
                Objects.equals(gruBorrad, that.gruBorrad) &&
                Objects.equals(gruTipo, that.gruTipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gruCodi, gruNombre, gruBorrad, gruTipo);
    }
}
