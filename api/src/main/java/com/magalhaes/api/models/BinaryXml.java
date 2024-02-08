package com.magalhaes.api.models;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "binary_xml")
public class BinaryXml implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_xml", unique = true)
    private String idXml;

    @Lob
    @Column(name = "file_xml", columnDefinition = "BYTEA")
    private byte[] fileXml;

    @OneToMany(mappedBy = "binaryDataXml", cascade = CascadeType.ALL)
    private List<Invoice> invoices;
}
