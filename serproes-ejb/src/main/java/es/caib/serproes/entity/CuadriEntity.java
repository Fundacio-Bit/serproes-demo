package es.caib.serproes.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name = "SER_CUADRI",
    indexes = {
        @Index(name = "ser_cuadri_pk_i", columnList = "CUA_CODI", unique = true),
        @Index(name = "ser_cuadri_cuax_i", columnList = "CUA_X"),
        @Index(name = "ser_cuadri_cuay_i", columnList = "CUA_Y")})
public class CuadriEntity {
    private Long cuaCodi;
    private Integer cuaX;
    private Integer cuaY;
    private String cuaLongit;
    private String cuaLatitu;
    private Integer cuaCodisl;
    private String cuaBorrad;
    private Long cuaCodq5;
    private Long cuaCodq10;

    @Id
    @GeneratedValue
    @Column(name = "CUA_CODI")
    public Long getCuaCodi() {
        return cuaCodi;
    }

    public void setCuaCodi(Long cuaCodi) {
        this.cuaCodi = cuaCodi;
    }

    @Column(name = "CUA_X")
    public Integer getCuaX() {
        return cuaX;
    }

    public void setCuaX(Integer cuaX) {
        this.cuaX = cuaX;
    }

    @Column(name = "CUA_Y")
    public Integer getCuaY() {
        return cuaY;
    }

    public void setCuaY(Integer cuaY) {
        this.cuaY = cuaY;
    }

    @Column(name = "CUA_LONGIT")
    public String getCuaLongit() {
        return cuaLongit;
    }

    public void setCuaLongit(String cuaLongit) {
        this.cuaLongit = cuaLongit;
    }

    @Column(name = "CUA_LATITU")
    public String getCuaLatitu() {
        return cuaLatitu;
    }

    public void setCuaLatitu(String cuaLatitu) {
        this.cuaLatitu = cuaLatitu;
    }

    @Column(name = "CUA_CODISL", nullable = false)
    public Integer getCuaCodisl() {
        return cuaCodisl;
    }

    public void setCuaCodisl(Integer cuaCodisl) {
        this.cuaCodisl = cuaCodisl;
    }

    @Column(name = "CUA_BORRAD", columnDefinition = "VARCHAR(1) CHECK (CUA_BORRAD IN ('S','N'))")
    public String getCuaBorrad() {
        return cuaBorrad;
    }
    public void setCuaBorrad(String cuaBorrad) {
        this.cuaBorrad = cuaBorrad;
    }

    @Column(name = "CUA_CODQ5")
    public Long getCuaCodq5() {
        return cuaCodq5;
    }

    public void setCuaCodq5(Long cuaCodq5) {
        this.cuaCodq5 = cuaCodq5;
    }

    @Column(name = "CUA_CODQ10")
    public Long getCuaCodq10() {
        return cuaCodq10;
    }

    public void setCuaCodq10(Long cuaCodq10) {
        this.cuaCodq10 = cuaCodq10;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CuadriEntity that = (CuadriEntity) o;
        return Objects.equals(cuaCodi, that.cuaCodi) &&
                Objects.equals(cuaX, that.cuaX) &&
                Objects.equals(cuaY, that.cuaY) &&
                Objects.equals(cuaLongit, that.cuaLongit) &&
                Objects.equals(cuaLatitu, that.cuaLatitu) &&
                Objects.equals(cuaCodisl, that.cuaCodisl) &&
                Objects.equals(cuaBorrad, that.cuaBorrad) &&
                Objects.equals(cuaCodq5, that.cuaCodq5) &&
                Objects.equals(cuaCodq10, that.cuaCodq10);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cuaCodi, cuaX, cuaY, cuaLongit, cuaLatitu, cuaCodisl, cuaBorrad, cuaCodq5, cuaCodq10);
    }
}
