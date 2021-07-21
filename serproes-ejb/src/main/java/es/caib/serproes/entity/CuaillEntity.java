package es.caib.serproes.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name = "SER_CUAILL",
        indexes = {
                @Index(name = "ser_cuaill_pk_i ", columnList = "CUI_CODILL, CUI_CODCUA", unique = true),
                @Index(name = "ser_cuaill_codill_i ", columnList = "CUI_CODILL"),
                @Index(name = "ser_cuaill_codcua_i", columnList = "CUI_CODCUA")})
@AssociationOverrides({
        @AssociationOverride(name = "cuaillPK.cuiCodill",
                foreignKey = @ForeignKey(name = "SER_CUAILL_ILLOT_FK"),
                joinColumns = @JoinColumn(name = "CUI_CODILL")),
        @AssociationOverride(name = "cuaillPK.cuiCodcua",
                foreignKey = @ForeignKey(name = "SER_CUAILL_CUADRI_FK"),
                joinColumns = @JoinColumn(name = "CUI_CODCUA")) })

public class CuaillEntity {
    private CuaillEntityPK cuaillPK = new CuaillEntityPK();

    @EmbeddedId
    public CuaillEntityPK getCuaillPK() {
        return cuaillPK;
    }

    public void setCuaillPK(CuaillEntityPK cuaillPK) {
        this.cuaillPK = cuaillPK;
    }

    @Transient
    public FamiliEntity.IllotEntity getCuiCodill() {
        return getCuaillPK().getCuiCodill();
    }

    public void setCuiCodill(FamiliEntity.IllotEntity codill) {
        getCuaillPK().setCuiCodill(codill);
    }

    @Transient
    public CuadriEntity getCuiCodcua() {
        return getCuaillPK().getCuiCodcua();
    }

    public void setCuiCodcua(CuadriEntity codcua) {
        getCuaillPK().setCuiCodcua(codcua);
    }

    }
