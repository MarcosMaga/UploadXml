package com.magalhaes.api.dtos;

import java.util.ArrayList;
import java.util.List;

public class InvoiceResponse {
    private int success;
    private int total;
    
    private List<InvoiceStatus> invoicesStatus = new ArrayList<>();
    
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public List<InvoiceStatus> getInvoicesStatus() {
        return invoicesStatus;
    }

    public void addInvoiceStatus(String fileName, Boolean success, String error){
        invoicesStatus.add(new InvoiceStatus(fileName, success, error));
    }

    public class InvoiceStatus{
        private String fileName;
        private Boolean success;
        private String error;

        public InvoiceStatus(String fileName, Boolean success, String error){
            this.fileName = fileName;
            this.success = success;
            this.error = error;
        }

        public String getFileName() {
            return fileName;
        }

        public Boolean getSuccess() {
            return success;
        }

        public String getError() {
            return error;
        }
    } 
}


