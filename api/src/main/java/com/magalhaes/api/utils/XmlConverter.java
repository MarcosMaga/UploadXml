package com.magalhaes.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.magalhaes.api.dtos.InvoiceDto;
import com.magalhaes.api.models.Invoice;

public class XmlConverter {
    public static Invoice convertXmlToInvoice(MultipartFile file) throws IOException{
        InputStream inputStream = file.getInputStream();
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JavaTimeModule());
        xmlMapper.configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false);
        NfeProc nfeProc = xmlMapper.readValue(inputStream, NfeProc.class);
        InfNFe infNFe = nfeProc.nFe.infNFe;
        
        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setId(infNFe.Id);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        invoiceDto.setDhEmi(LocalDateTime.parse(infNFe.ide.dhEmi, formatter));

        invoiceDto.setnNF(infNFe.ide.nNF);
        invoiceDto.setcUF(infNFe.ide.cUF);
        invoiceDto.setEmitCnpj(infNFe.emit.CNPJ);
        invoiceDto.setxFant(infNFe.emit.xFant);
        invoiceDto.setDestCnpj(infNFe.dest.CNPJ);
        invoiceDto.setxNome(infNFe.dest.xNome);
        invoiceDto.setvTotTrib(infNFe.total.ICMSTot.vTotTrib);
        invoiceDto.setvNF(infNFe.total.ICMSTot.vNF);
        
        return invoiceDto.convert();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JacksonXmlRootElement(localName = "nfeProc")
    static class NfeProc {
        @JacksonXmlProperty(localName = "NFe")
        NFe nFe;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class NFe {
        @JacksonXmlProperty(localName = "infNFe")
        InfNFe infNFe;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class InfNFe {
        @JacksonXmlProperty(localName = "Id")
        String Id;

        @JacksonXmlProperty(localName = "ide")
        Ide ide;

        @JacksonXmlProperty(localName = "emit")
        Emit emit;

        @JacksonXmlProperty(localName = "dest")
        Dest dest;

        @JacksonXmlProperty(localName = "total")
        Total total;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Ide {
        @JacksonXmlProperty(localName = "dhEmi")
        CharSequence dhEmi;

        @JacksonXmlProperty(localName = "nNF")
        Integer nNF;

        @JacksonXmlProperty(localName = "cUF")
        Integer cUF;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Emit {
        @JacksonXmlProperty(localName = "CNPJ")
        String CNPJ;

        @JacksonXmlProperty(localName = "xFant")
        String xFant;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Dest {
        @JacksonXmlProperty(localName = "CNPJ")
        String CNPJ;

        @JacksonXmlProperty(localName = "xNome")
        String xNome;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Total {
        @JacksonXmlProperty(localName = "ICMSTot")
        ICMSTot ICMSTot;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class ICMSTot {
        @JacksonXmlProperty(localName = "vTotTrib")
        BigDecimal vTotTrib;

        @JacksonXmlProperty(localName = "vNF")
        BigDecimal vNF;
    }
}
