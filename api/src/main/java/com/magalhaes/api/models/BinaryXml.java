package com.magalhaes.api.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "binary_xml")
public class BinaryXml implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "file_xml")
    private byte[] fileXml;

    
    public void setFileXml(byte[] fileXml) {
        this.fileXml = fileXml;
    }
    
    @OneToOne
    @JoinColumn(name = "invoice_id", referencedColumnName = "id", unique = true)
    @JsonIgnore
    private Invoice invoice;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
    
    public byte[] getFileXml() {
        return fileXml;
    }
}
