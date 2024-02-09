package com.magalhaes.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.magalhaes.api.models.BinaryXml;

@Repository
public interface BinaryXmlRepository extends JpaRepository<BinaryXml, Long> {
    
}
