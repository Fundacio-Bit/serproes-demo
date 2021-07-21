package es.caib.serproes.entity;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CuamunEntityPK implements Serializable {
    private MuniciEntity cumCodmun;
    private CuadriEntity cumCodcua;

    @ManyToOne(targetEntity = MuniciEntity.class)
    public MuniciEntity getCumCodmun() {
        return cumCodmun;
    }

    public void setCumCodmun(MuniciEntity cumCodmun) {
        this.cumCodmun = cumCodmun;
    }

    @ManyToOne(targetEntity = CuadriEntity.class)
    public CuadriEntity getCumCodcua() {
        return cumCodcua;
    }

    public void setCumCodcua(CuadriEntity cumCodcua) {
        this.cumCodcua = cumCodcua;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CuamunEntityPK)) return false;
        CuamunEntityPK that = (CuamunEntityPK) o;
        return Objects.equals(cumCodmun, that.cumCodmun) &&
                Objects.equals(cumCodcua, that.cumCodcua);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cumCodmun, cumCodcua);
    }
}
