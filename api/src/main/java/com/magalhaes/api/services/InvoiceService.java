package com.magalhaes.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.magalhaes.api.models.Invoice;
import com.magalhaes.api.repositories.InvoiceRepository;
import com.magalhaes.api.utils.XmlConverter;

@Service
public class InvoiceService {

    @Autowired 
    private InvoiceRepository invoiceRepository;

    @Autowired
    private BinaryXmlService binaryXmlService;

    public Page<Invoice> getInvoices(Pageable pageable){
        return invoiceRepository.findAll(pageable);
    }

    public void processXml(MultipartFile[] files){
        for(MultipartFile file: files){
            try{
                Invoice invoice = XmlConverter.convertXmlToInvoice(file);
                invoiceRepository.save(invoice);
                binaryXmlService.createBinaryXml(file, invoice);
            }catch(Exception e){
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
            }
        }
    }
}
