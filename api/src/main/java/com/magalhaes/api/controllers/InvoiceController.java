package com.magalhaes.api.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.magalhaes.api.models.Invoice;
import com.magalhaes.api.services.InvoiceService;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController{
    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadInvoice(@RequestParam("files") MultipartFile[] files) throws IOException{
        try{
            invoiceService.processXml(files);
            return ResponseEntity.ok("Files uploaded sucessfully");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload files: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Page<Invoice>> getInvoices(Pageable pageable){
        Page<Invoice> invoices = invoiceService.getInvoices(pageable);
        return ResponseEntity.ok(invoices);
    }
}