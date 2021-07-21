package es.caib.serproes.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name = "SER_FAMILI",
        indexes = {
                @Index(name = "ser_famili_pk_i", columnList = "FAM_CODI", unique = true),
                @Index(name = "ser_famili_nombre_i", columnList = "FAM_NOMBRE")})

public class FamiliEntity {
    private Integer famCodi;
    private String famNombre;
    private String famBorrad;
    private GrupoEntity famCodgru;

    @Id
    @GeneratedValue
    @Column(name = "FAM_CODI")
    public Integer getFamCodi() {
        return famCodi;
    }

    public void setFamCodi(Integer famCodi) {
        this.famCodi = famCodi;
    }

    @Basic
    @Column(name = "FAM_NOMBRE")
    public String getFamNombre() {
        return famNombre;
    }

    public void setFamNombre(String famNombre) {
        this.famNombre = famNombre;
    }

    @Basic
    @Column(name = "FAM_BORRAD", columnDefinition = "VARCHAR(1) CHECK (FAM_BORRAD IN ('S','N'))")
    public String getFamBorrad() {
        return famBorrad;
    }

    public void setFamBorrad(String famBorrad) {
        this.famBorrad = famBorrad;
    }

    @ManyToOne(targetEntity = GrupoEntity.class)
    @JoinColumn(name = "FAM_CODGRU", referencedColumnName="GRU_CODI", foreignKey = @ForeignKey(name = "SER_FAMGR_FK"))
    public GrupoEntity getFamCodgru() { return famCodgru; }

    public void setFamCodgru(GrupoEntity famCodgru) { this.famCodgru = famCodgru; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FamiliEntity)) return false;
        FamiliEntity that = (FamiliEntity) o;
        return Objects.equals(famCodi, that.famCodi) &&
                Objects.equals(famNombre, that.famNombre) &&
                Objects.equals(famBorrad, that.famBorrad) &&
                Objects.equals(famCodgru, that.famCodgru);
    }

    @Override
    public int hashCode() {
        return Objects.hash(famCodi, famNombre, famBorrad, famCodgru);
    }

    @Entity
    @XmlRootElement
    @XmlAccessorType(XmlAccessType.FIELD)
    @Table(name="ser_illot",
            indexes = {
                    @Index(name = "ser_illot_pk_i", columnList = "ILL_CODI", unique = true),
                    @Index(name = "ser_illot_nom_i", columnList = "ILL_NOM")})

    public static class IllotEntity {
        private long illCodi;
        private String illNom;
        private Double illX;
        private Double illY;
        private String illGrup;
        private Double illPeri;
        private Double illArea;

        @Id
        @GeneratedValue
        @Column(name = "ILL_CODI", nullable = false)
        public long getIllCodi() {
            return illCodi;
        }

        public void setIllCodi(long illCodi) {
            this.illCodi = illCodi;
        }

        @Column(name = "ILL_NOM", nullable = true, length = 200)
        public String getIllNom() {
            return illNom;
        }

        public void setIllNom(String illNom) {
            this.illNom = illNom;
        }

        @Column(name = "ILL_X", nullable = true, precision = 0)
        public Double getIllX() {
            return illX;
        }

        public void setIllX(Double illX) {
            this.illX = illX;
        }

        @Column(name = "ILL_Y", nullable = true, precision = 0)
        public Double getIllY() {
            return illY;
        }

        public void setIllY(Double illY) {
            this.illY = illY;
        }

        @Column(name = "ILL_GRUP", nullable = true, length = 50)
        public String getIllGrup() {
            return illGrup;
        }

        public void setIllGrup(String illGrup) {
            this.illGrup = illGrup;
        }

        @Column(name = "ILL_PERI", nullable = true, precision = 0)
        public Double getIllPeri() {
            return illPeri;
        }

        public void setIllPeri(Double illPeri) {
            this.illPeri = illPeri;
        }

        @Column(name = "ILL_AREA", nullable = true, precision = 0)
        public Double getIllArea() {
            return illArea;
        }

        public void setIllArea(Double illArea) {
            this.illArea = illArea;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof IllotEntity)) return false;
            IllotEntity that = (IllotEntity) o;
            return illCodi == that.illCodi &&
                    Objects.equals(illNom, that.illNom) &&
                    Objects.equals(illX, that.illX) &&
                    Objects.equals(illY, that.illY) &&
                    Objects.equals(illGrup, that.illGrup) &&
                    Objects.equals(illPeri, that.illPeri) &&
                    Objects.equals(illArea, that.illArea);
        }

        @Override
        public int hashCode() {
            return Objects.hash(illCodi, illNom, illX, illY, illGrup, illPeri, illArea);
        }
    }
}
