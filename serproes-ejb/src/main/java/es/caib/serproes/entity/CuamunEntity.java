package es.caib.serproes.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name = "SER_CUAMUN",
        indexes = {
            @Index(name = "ser_cuamun_pk_i ", columnList = "CUM_CODMUN, CUM_CODCUA", unique = true),
            @Index(name = "ser_cuamun_munici_fk_i", columnList = "CUM_CODMUN"),
            @Index(name = "ser_cuamun_cuadri_fk_i", columnList = "CUM_CODCUA")})
@AssociationOverrides({
        @AssociationOverride(name = "cuamunPK.cumCodmun",
                foreignKey = @ForeignKey(name = "SER_CUAMUN_MUNICI_FK"),
                joinColumns = @JoinColumn(name = "CUM_CODMUN")),
        @AssociationOverride(name = "cuamunPK.cumCodcua",
                foreignKey = @ForeignKey(name = "SER_CUAMUN_CUADRI_FK"),
                joinColumns = @JoinColumn(name = "CUM_CODCUA")) })

public class CuamunEntity {
    private CuamunEntityPK cuamunPK = new CuamunEntityPK();

    @EmbeddedId
    public CuamunEntityPK getCuamunPK() {
        return cuamunPK;
    }

    public void setCuamunPK(CuamunEntityPK cuamunPK) {
        this.cuamunPK = cuamunPK;
    }

    @Transient
    public MuniciEntity getCumCodmun() { return getCuamunPK().getCumCodmun(); }

    public void setCumCodmun(MuniciEntity codmun) {
        getCuamunPK().setCumCodmun(codmun);
    }

    @Transient
    public CuadriEntity getCulCodcua() {
        return getCuamunPK().getCumCodcua();
    }

    public void setCulCodcua(CuadriEntity codcua) {
        getCuamunPK().setCumCodcua(codcua);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CuamunEntity)) return false;
        CuamunEntity that = (CuamunEntity) o;
        return Objects.equals(cuamunPK, that.cuamunPK);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cuamunPK);
    }
}
