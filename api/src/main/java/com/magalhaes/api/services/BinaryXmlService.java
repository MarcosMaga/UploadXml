package com.magalhaes.api.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.magalhaes.api.models.BinaryXml;
import com.magalhaes.api.models.Invoice;
import com.magalhaes.api.repositories.BinaryXmlRepository;

@Service
public class BinaryXmlService {
    private static final Logger logger = LoggerFactory.getLogger(InvoiceService.class);

    @Autowired
    private BinaryXmlRepository binaryXmlRepository;

    public BinaryXml createBinaryXml(MultipartFile file, Invoice invoice){
        BinaryXml binaryXml = new BinaryXml();

        try {
            binaryXml.setInvoice(invoice);
            binaryXml.setFileXml(file.getBytes());
            binaryXmlRepository.save(binaryXml);
            return binaryXml;
        } catch (Exception e) {
            logger.error("Error to convert XML: " + e.getMessage());
            return null;
        }
    }
    
}
