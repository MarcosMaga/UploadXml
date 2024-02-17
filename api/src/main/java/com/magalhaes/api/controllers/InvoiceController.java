package com.magalhaes.api.controllers;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.magalhaes.api.dtos.InvoiceResponse;
import com.magalhaes.api.models.Invoice;
import com.magalhaes.api.services.InvoiceService;

@RestController
@RequestMapping("/invoice")
public class InvoiceController{
    private static final Logger logger = LoggerFactory.getLogger(InvoiceService.class);

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/upload")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Object> uploadInvoice(@RequestParam("files") MultipartFile[] files) throws IOException{
        try{
            InvoiceResponse response = invoiceService.processXml(files);
            return ResponseEntity.ok(response);
        }catch(Exception e){
            logger.error("Error in '/upload': " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload files: " + e.getMessage());
        }
    }

    @GetMapping
    @CrossOrigin(origins = "*")
    public ResponseEntity<Page<Invoice>> getInvoices(Pageable pageable){
        Page<Invoice> invoices = invoiceService.getInvoices(pageable);
        return invoices.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(invoices);
    }
}