package es.caib.serproes.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name = "SER_HISESP",
        indexes = {
                @Index(name = "ser_hisesp_pk ", columnList = "HIS_CODI", unique = true)})

public class HisespEntity {
    private Double hisCodi;
    private String hisSintax;
    private String hisBorrad;
    private EspeciEntity hisCodesp;

    @Id
    @Column(name = "HIS_CODI")
    public Double getHisCodi() {
        return hisCodi;
    }

    public void setHisCodi(Double hisCodi) {
        this.hisCodi = hisCodi;
    }

    @Column(name = "HIS_SINTAX")
    public String getHisSintax() {
        return hisSintax;
    }

    public void setHisSintax(String hisSintax) {
        this.hisSintax = hisSintax;
    }

    @Column(name = "HIS_BORRAD")
    public String getHisBorrad() {
        return hisBorrad;
    }

    public void setHisBorrad(String hisBorrad) {
        this.hisBorrad = hisBorrad;
    }

    @ManyToOne(targetEntity = EspeciEntity.class)
    @JoinColumn(name = "HIS_CODESP", referencedColumnName="ESP_CODI", foreignKey = @ForeignKey(name = "SER_HISESP_FK"))
    public EspeciEntity getHisCodesp() {
        return hisCodesp;
    }

    public void setHisCodesp(EspeciEntity hisCodesp) {
        this.hisCodesp = hisCodesp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HisespEntity)) return false;
        HisespEntity that = (HisespEntity) o;
        return Objects.equals(hisCodi, that.hisCodi) &&
                Objects.equals(hisSintax, that.hisSintax) &&
                Objects.equals(hisBorrad, that.hisBorrad) &&
                Objects.equals(hisCodesp, that.hisCodesp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hisCodi, hisSintax, hisBorrad, hisCodesp);
    }
}
