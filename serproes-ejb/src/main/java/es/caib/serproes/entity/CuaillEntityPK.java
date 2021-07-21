package es.caib.serproes.entity;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CuaillEntityPK implements Serializable {
    private FamiliEntity.IllotEntity culCodill;
    private CuadriEntity culCodcua;

    @ManyToOne(targetEntity = FamiliEntity.IllotEntity.class)
    public FamiliEntity.IllotEntity getCuiCodill() {
        return culCodill;
    }

    public void setCuiCodill(FamiliEntity.IllotEntity cumCodill) {
        this.culCodill = culCodill;
    }

    @ManyToOne(targetEntity = CuadriEntity.class)
    public CuadriEntity getCuiCodcua() { return culCodcua; }

    public void setCuiCodcua(CuadriEntity cumCodcua) {
        this.culCodcua = culCodcua;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CuaillEntityPK)) return false;
        CuaillEntityPK that = (CuaillEntityPK) o;
        return culCodill.equals(that.culCodill) &&
                culCodcua.equals(that.culCodcua);
    }

    @Override
    public int hashCode() {
        return Objects.hash(culCodill, culCodcua);
    }
}
