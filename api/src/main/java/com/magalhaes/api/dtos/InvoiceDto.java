package com.magalhaes.api.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.magalhaes.api.models.Invoice;

public class InvoiceDto {
    private String id;
    private LocalDateTime dhEmi;
    private Integer nNF;
    private Integer cUF;
    private String emitCnpj;
    private String xFant;
    private String destCnpj;
    private String xNome;
    private BigDecimal vTotTrib;
    private BigDecimal vNF;

    public void setId(String id) {
        this.id = id;
    }
    public void setDhEmi(LocalDateTime dhEmi) {
        this.dhEmi = dhEmi;
    }
    public void setnNF(Integer nNF) {
        this.nNF = nNF;
    }
    public void setcUF(Integer cUF) {
        this.cUF = cUF;
    }
    public void setEmitCnpj(String emitCnpj) {
        this.emitCnpj = emitCnpj;
    }
    public void setxFant(String xFant) {
        this.xFant = xFant;
    }
    public void setDestCnpj(String destCnpj) {
        this.destCnpj = destCnpj;
    }
    public void setxNome(String xNome) {
        this.xNome = xNome;
    }
    public void setvTotTrib(BigDecimal vTotTrib) {
        this.vTotTrib = vTotTrib;
    }
    public void setvNF(BigDecimal vNF) {
        this.vNF = vNF;
    }

    public Invoice convert(){
        Invoice invoice = new Invoice();
        invoice.setId(this.id);
        invoice.setDhEmi(this.dhEmi);
        invoice.setnNF(this.nNF);
        invoice.setcUF(this.cUF);
        invoice.setEmitCnpj(this.emitCnpj);
        invoice.setxFant(this.xFant);
        invoice.setDestCnpj(this.destCnpj);
        invoice.setxNome(this.xNome);
        invoice.setvTotTrib(this.vTotTrib);
        invoice.setvNF(this.vNF);
        return invoice; 
    }
}
