package es.caib.serproes.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name="SER_ESPECI",
        indexes = {
                @Index(name = "ser_especi_pk_i", columnList = "ESP_CODI", unique = true),
                @Index(name = "ser_especi_taxon_i", columnList = "ESP_TAXON"),
                @Index(name = "ser_especi_nomcomes_i", columnList = "ESP_NOMCOMES"),
                @Index(name = "ser_especi_nomcom_i", columnList = "ESP_NOMCOM")})

public class EspeciEntity {
    private Double espCodi;
    private String espTaxon;
    private String espNomcom;
    private String espCatalo;
    private String espAmenaz;
    private Double espEndemi;
    private String espBorrad;
    private String espVis1X1;
    private String espNomcomes;
    private FamiliEntity espCodfam;

    @Id
    @GeneratedValue
    @Column(name="ESP_CODI")
    public Double getEspCodi() {
        return espCodi;
    }

    public void setEspCodi(Double espCodi) {
        this.espCodi = espCodi;
    }

    @Column(name="ESP_TAXON")
    public String getEspTaxon() {
        return espTaxon;
    }

    public void setEspTaxon(String espTaxon) {
        this.espTaxon = espTaxon;
    }

    @Column(name="ESP_NOMCOM")
    public String getEspNomcom() {
        return espNomcom;
    }

    public void setEspNomcom(String espNomcom) {
        this.espNomcom = espNomcom;
    }

    @Column(name="ESP_CATALO")
    public String getEspCatalo() {
        return espCatalo;
    }

    public void setEspCatalo(String espCatalo) {
        this.espCatalo = espCatalo;
    }

    @Column(name="ESP_AMENAZ")
    public String getEspAmenaz() {
        return espAmenaz;
    }

    public void setEspAmenaz(String espAmenaz) {
        this.espAmenaz = espAmenaz;
    }

    @Column(name="ESP_ENDEMI")
    public Double getEspEndemi() {
        return espEndemi;
    }

    public void setEspEndemi(Double espEndemi) {
        this.espEndemi = espEndemi;
    }

    @Column(name = "ESP_BORRAD", columnDefinition = "VARCHAR(1) CHECK (ESP_BORRAD IN ('S','N'))")
    public String getEspBorrad() {
        return espBorrad;
    }

    public void setEspBorrad(String espBorrad) {
        this.espBorrad = espBorrad;
    }

    @Column(name="ESP_VIS1x1", columnDefinition = "VARCHAR(1) DEFAULT 'S'")
    public String getEspVis1X1() {
        return espVis1X1;
    }

    public void setEspVis1X1(String espVis1X1) {
        this.espVis1X1 = espVis1X1;
    }

    @Column(name="ESP_NOMCOMES")
    public String getEspNomcomes() {
        return espNomcomes;
    }

    public void setEspNomcomes(String espNomcomes) {
        this.espNomcomes = espNomcomes;
    }

    @ManyToOne(targetEntity = FamiliEntity.class)
    @JoinColumn(name = "ESP_CODFAM", referencedColumnName="FAM_CODI", foreignKey = @ForeignKey(name = "SER_ESPFAM_FK"))
    public FamiliEntity getEspCodfam() { return espCodfam; }

    public void setEspCodfam(FamiliEntity espCodfam) {
        this.espCodfam = espCodfam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EspeciEntity)) return false;
        EspeciEntity that = (EspeciEntity) o;
        return Objects.equals(espCodi, that.espCodi) &&
                Objects.equals(espCodfam, that.espCodfam) &&
                Objects.equals(espTaxon, that.espTaxon) &&
                Objects.equals(espNomcom, that.espNomcom) &&
                Objects.equals(espCatalo, that.espCatalo) &&
                Objects.equals(espAmenaz, that.espAmenaz) &&
                Objects.equals(espEndemi, that.espEndemi) &&
                Objects.equals(espBorrad, that.espBorrad) &&
                Objects.equals(espVis1X1, that.espVis1X1) &&
                Objects.equals(espNomcomes, that.espNomcomes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(espCodi, espCodfam, espTaxon, espNomcom, espCatalo, espAmenaz, espEndemi, espBorrad, espVis1X1, espNomcomes);
    }
}
