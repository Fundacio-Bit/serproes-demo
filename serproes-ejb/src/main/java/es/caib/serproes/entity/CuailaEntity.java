package es.caib.serproes.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name = "SER_CUAILA",
        indexes = {
                @Index(name = "ser_cuaila_pk_i", columnList = "CUL_CODILA, CUL_CODCUA", unique = true),
                @Index(name = "ser_cuaila_illa_fk_i", columnList = "CUL_CODILA"),
                @Index(name = "ser_cuaila_cuadri_fk_i", columnList = "CUL_CODCUA")})
@AssociationOverrides({
        @AssociationOverride(name = "cuailaPK.culCodila",
                foreignKey = @ForeignKey(name = "SER_CUAILA_ILLA_FK"),
                joinColumns = @JoinColumn(name = "CUL_CODILA")),
        @AssociationOverride(name = "cuailaPK.culCodcua",
                foreignKey = @ForeignKey(name = "SER_CUAILA_CUADRI_FK"),
                joinColumns = @JoinColumn(name = "CUL_CODCUA")) })

public class CuailaEntity {
    private CuailaEntityPK cuailaPK = new CuailaEntityPK();

    @EmbeddedId
    public CuailaEntityPK getCuailaPK() {
        return cuailaPK;
    }

    public void setCuailaPK(CuailaEntityPK cuailaPK) {
        this.cuailaPK = cuailaPK;
    }
    @Transient
    public IllaEntity getCulCodila() {
        return getCuailaPK().getCulCodila();
    }

    public void setCulCodila(IllaEntity codila) {
        getCuailaPK().setCulCodila(codila);
    }

    @Transient
    public CuadriEntity getCulCodcua() {
        return getCuailaPK().getCulCodcua();
    }

    public void setCulCodcua(CuadriEntity codcua) {
        getCuailaPK().setCulCodcua(codcua);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CuailaEntity)) return false;
        CuailaEntity that = (CuailaEntity) o;
        return Objects.equals(cuailaPK, that.cuailaPK);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cuailaPK);
    }
}
