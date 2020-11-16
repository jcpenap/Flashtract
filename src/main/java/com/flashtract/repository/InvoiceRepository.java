package com.flashtract.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flashtract.model.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer>{
	
}
