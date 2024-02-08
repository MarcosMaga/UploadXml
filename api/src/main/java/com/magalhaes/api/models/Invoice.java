package com.magalhaes.api.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "xfant")
    private String xFant;

    @Column(name = "xnome")
    private String xNome;

    @Column(name = "vtottrib")
    private BigDecimal vTotTrib;

    @Column(name = "vnf")
    private BigDecimal vNF;

    @ManyToOne
    @JoinColumn(name = "id_xml", referencedColumnName = "id_xml", insertable = false, updatable = false)
    private BinaryXml binaryDataXml;

}