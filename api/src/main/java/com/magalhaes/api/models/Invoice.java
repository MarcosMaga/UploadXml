package com.magalhaes.api.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "invoices")
public class Invoice implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Column(name = "dhemi")
    private LocalDateTime dhEmi;

    @Column(name = "nnf")
    private Integer nNF;

    @Column(name = "cuf")
    private Integer cUF;

    @Column(name = "emit_cnpj")
    private String emitCnpj;

    @Column(name = "xfant")
    private String xFant;

    @Column(name = "dest_cnpj")
    private String destCnpj;

    @Column(name = "xnome")
    private String xNome;

    @Column(name = "vtottrib")
    private BigDecimal vTotTrib;

    @Column(name = "vnf")
    private BigDecimal vNF;

    @OneToOne(mappedBy = "invoice")
    private BinaryXml binary;

    public BinaryXml getBinary() {
        return binary;
    }

    public void setBinary(BinaryXml binary) {
        this.binary = binary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDhEmi() {
        return dhEmi;
    }

    public void setDhEmi(LocalDateTime dhEmi) {
        this.dhEmi = dhEmi;
    }

    public Integer getnNF() {
        return nNF;
    }

    public void setnNF(Integer nNF) {
        this.nNF = nNF;
    }

    public Integer getcUF() {
        return cUF;
    }

    public void setcUF(Integer cUF) {
        this.cUF = cUF;
    }

    public String getEmitCnpj() {
        return emitCnpj;
    }

    public void setEmitCnpj(String emitCnpj) {
        this.emitCnpj = emitCnpj;
    }

    public String getxFant() {
        return xFant;
    }

    public void setxFant(String xFant) {
        this.xFant = xFant;
    }

    public String getDestCnpj() {
        return destCnpj;
    }

    public void setDestCnpj(String destCnpj) {
        this.destCnpj = destCnpj;
    }

    public String getxNome() {
        return xNome;
    }

    public void setxNome(String xNome) {
        this.xNome = xNome;
    }

    public BigDecimal getvTotTrib() {
        return vTotTrib;
    }

    public void setvTotTrib(BigDecimal vTotTrib) {
        this.vTotTrib = vTotTrib;
    }

    public BigDecimal getvNF() {
        return vNF;
    }

    public void setvNF(BigDecimal vNF) {
        this.vNF = vNF;
    }
}