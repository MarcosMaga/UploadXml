package com.magalhaes.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.magalhaes.api.dtos.InvoiceResponse;
import com.magalhaes.api.models.Invoice;
import com.magalhaes.api.repositories.InvoiceRepository;
import com.magalhaes.api.utils.XmlConverter;

@Service
public class InvoiceService {
    private static final Logger logger = LoggerFactory.getLogger(InvoiceService.class);

    @Autowired 
    private InvoiceRepository invoiceRepository;

    @Autowired
    private BinaryXmlService binaryXmlService;

    public Page<Invoice> getInvoices(Pageable pageable){
        return invoiceRepository.findAll(pageable);
    }

    public InvoiceResponse processXml(MultipartFile[] files){
        InvoiceResponse response = new InvoiceResponse();
        response.setTotal(files.length);
        int success=0;
        
        for(MultipartFile file: files){
            try{
                Invoice invoice = XmlConverter.convertXmlToInvoice(file);
                
                try{
                    invoiceRepository.save(invoice);
                    binaryXmlService.createBinaryXml(file, invoice);
                    success++;
                    response.addInvoiceStatus(file.getOriginalFilename(), true, null);
                }catch(DataIntegrityViolationException e){
                    logger.info("SQL Exception: " + e.getMessage());
                    response.addInvoiceStatus(file.getOriginalFilename(), false, "Duplicated XML");
                }catch(Exception e){
                    logger.error("SQL Error: " + e.getMessage());
                    response.addInvoiceStatus(file.getOriginalFilename(), false, e.getMessage());
                }
            }catch(Exception e){
                logger.error("XML Invalid: " + e.getMessage());
                response.addInvoiceStatus(file.getOriginalFilename(), false, "XML invalid");
            }
        }
        response.setSuccess(success);
        return response;
    }
}
