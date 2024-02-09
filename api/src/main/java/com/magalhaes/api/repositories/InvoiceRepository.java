package com.magalhaes.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.magalhaes.api.models.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, String> {
}
