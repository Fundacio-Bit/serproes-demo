package es.caib.serproes.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name = "SER_REGIST",
    indexes = {
        @Index(name = "ser_registro_pk_i", columnList = "REG_CODI", unique = true),
        @Index(name = "ser_regist_fecha_i", columnList = "REG_FECHA"),
        @Index(name = "ser_regist_codtir_i", columnList = "REG_CODTIR"),
        @Index(name = "ser_regist_codill_i", columnList = "REG_CODILL"),
        @Index(name = "ser_regist_codfue_i", columnList = "REG_CODFUE"),
        @Index(name = "ser_regist_codesp_i", columnList = "REG_CODESP"),
        @Index(name = "ser_regist_codcua_i", columnList = "REG_CODCUA")})

public class RegistEntity {
    private Double regCodi;
    private Timestamp regFecbd;
    private String regDescon;
    private Integer regCodtir;
    private Timestamp regFecha;
    private String regBorrad;
    private Double regXdetal;
    private Double regYdetal;
    private String regForfec;
    private String regTipcua;
    private String errorOldData;
    private EspeciEntity regCodesp;
    private FuenteEntity regCodfue;
    private CuadriEntity regCodcua;
    private FamiliEntity.IllotEntity regCodill;

    @Id
    @GeneratedValue
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "REG_CODI")
    public Double getRegCodi() {
        return regCodi;
    }

    public void setRegCodi(Double regCodi) {
        this.regCodi = regCodi;
    }

    @Column(name = "REG_FECBD")
    public Timestamp getRegFecbd() {
        return regFecbd;
    }

    public void setRegFecbd(Timestamp regFecbd) {
        this.regFecbd = regFecbd;
    }

    @Column(name = "REG_DESCON")
    public String getRegDescon() {
        return regDescon;
    }

    public void setRegDescon(String regDescon) {
        this.regDescon = regDescon;
    }

    @Column(name = "REG_CODTIR")
    public Integer getRegCodtir() {
        return regCodtir;
    }

    public void setRegCodtir(Integer regCodtir) {
        this.regCodtir = regCodtir;
    }

    @Column(name = "REG_FECHA")
    public Timestamp getRegFecha() {
        return regFecha;
    }

    public void setRegFecha(Timestamp regFecha) {
        this.regFecha = regFecha;
    }

    @Column(name = "REG_BORRAD")
    public String getRegBorrad() {
        return regBorrad;
    }

    public void setRegBorrad(String regBorrad) {
        this.regBorrad = regBorrad;
    }

    @Column(name = "REG_XDETAL")
    public Double getRegXdetal() {
        return regXdetal;
    }

    public void setRegXdetal(Double regXdetal) {
        this.regXdetal = regXdetal;
    }

    @Column(name = "REG_YDETAL")
    public Double getRegYdetal() {
        return regYdetal;
    }

    public void setRegYdetal(Double regYdetal) {
        this.regYdetal = regYdetal;
    }

    @Column(name = "REG_FORFEC")
    public String getRegForfec() {
        return regForfec;
    }

    public void setRegForfec(String regForfec) {
        this.regForfec = regForfec;
    }

    @Column(name = "REG_TIPCUA")
    public String getRegTipcua() {
        return regTipcua;
    }

    public void setRegTipcua(String regTipcua) {
        this.regTipcua = regTipcua;
    }

    @Column(name = "ERROR_OLD_DATA")
    public String getErrorOldData() {
        return errorOldData;
    }

    public void setErrorOldData(String errorOldData) {
        this.errorOldData = errorOldData;
    }

    @ManyToOne(targetEntity = EspeciEntity.class)
    @JoinColumn(name = "REG_CODESP", referencedColumnName="ESP_CODI", foreignKey = @ForeignKey(name = "SER_REGIST_ESP_FK"))
    public EspeciEntity getRegCodesp() {
        return regCodesp;
    }

    public void setRegCodesp(EspeciEntity regCodesp) {
        this.regCodesp = regCodesp;
    }

    @ManyToOne(targetEntity = FuenteEntity.class)
    @JoinColumn(name = "REG_CODFUE", referencedColumnName="FUE_CODI", foreignKey = @ForeignKey(name = "SER_REGIST_FUE_FK"))
    public FuenteEntity getRegCodfue() {
        return regCodfue;
    }

    public void setRegCodfue(FuenteEntity regCodfue) {
        this.regCodfue = regCodfue;
    }

    @ManyToOne(targetEntity = CuadriEntity.class)
    @JoinColumn(name = "REG_CODCUA", referencedColumnName="CUA_CODI", foreignKey = @ForeignKey(name = "SER_REGIST_CUA_FK"))
    public CuadriEntity getRegCodcua() {
        return regCodcua;
    }

    public void setRegCodcua(CuadriEntity regCodcua) {
        this.regCodcua = regCodcua;
    }

    @ManyToOne(targetEntity = FamiliEntity.IllotEntity.class)
    @JoinColumn(name = "REG_CODILL", referencedColumnName="ILL_CODI", foreignKey = @ForeignKey(name = "SER_REGIST_ILL_FK"))
    public FamiliEntity.IllotEntity getRegCodill() {
        return regCodill;
    }

    public void setRegCodill(FamiliEntity.IllotEntity regCodill) {
        this.regCodill = regCodill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegistEntity)) return false;
        RegistEntity that = (RegistEntity) o;
        return Objects.equals(regCodi, that.regCodi) &&
                Objects.equals(regFecbd, that.regFecbd) &&
                Objects.equals(regDescon, that.regDescon) &&
                Objects.equals(regCodtir, that.regCodtir) &&
                Objects.equals(regFecha, that.regFecha) &&
                Objects.equals(regBorrad, that.regBorrad) &&
                Objects.equals(regXdetal, that.regXdetal) &&
                Objects.equals(regYdetal, that.regYdetal) &&
                Objects.equals(regForfec, that.regForfec) &&
                Objects.equals(regTipcua, that.regTipcua) &&
                Objects.equals(errorOldData, that.errorOldData) &&
                Objects.equals(regCodesp, that.regCodesp) &&
                Objects.equals(regCodfue, that.regCodfue) &&
                Objects.equals(regCodcua, that.regCodcua) &&
                Objects.equals(regCodill, that.regCodill);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regCodi, regFecbd, regDescon, regCodtir, regFecha, regBorrad, regXdetal, regYdetal, regForfec, regTipcua, errorOldData, regCodesp, regCodfue, regCodcua, regCodill);
    }
}
