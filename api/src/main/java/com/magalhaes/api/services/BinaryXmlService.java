package com.magalhaes.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.magalhaes.api.models.BinaryXml;
import com.magalhaes.api.models.Invoice;
import com.magalhaes.api.repositories.BinaryXmlRepository;

@Service
public class BinaryXmlService {

    @Autowired
    private BinaryXmlRepository binaryXmlRepository;

    public BinaryXml createBinaryXml(MultipartFile file, Invoice invoice) throws Exception{
        BinaryXml binaryXml = new BinaryXml();
        binaryXml.setInvoice(invoice);
        binaryXml.setFileXml(file.getBytes());
        binaryXmlRepository.save(binaryXml);
         return binaryXml;
    }
    
}
