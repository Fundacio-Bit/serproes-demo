package es.caib.serproes.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CuailaEntityPK implements Serializable {
    private IllaEntity culCodila;
    private CuadriEntity culCodcua;

    @ManyToOne(targetEntity = IllaEntity.class)
    public IllaEntity getCulCodila() {
        return culCodila;
    }

    public void setCulCodila(IllaEntity cumCodila) {
        this.culCodila = culCodila;
    }

    @ManyToOne(targetEntity = CuadriEntity.class)
    public CuadriEntity getCulCodcua() { return culCodcua; }

    public void setCulCodcua(CuadriEntity cumCodcua) {
        this.culCodcua = culCodcua;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CuailaEntityPK)) return false;
        CuailaEntityPK that = (CuailaEntityPK) o;
        return culCodila.equals(that.culCodila) &&
                culCodcua.equals(that.culCodcua);
    }

    @Override
    public int hashCode() {
        return Objects.hash(culCodila, culCodcua);
    }
}
