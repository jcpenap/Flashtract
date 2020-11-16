package com.flashtract.service;

import java.util.List;

import com.flashtract.dto.InvoiceDto;
import com.flashtract.exception.CustomException;

public interface InvoiceService {
	public List<InvoiceDto> findAll() throws CustomException;
	public InvoiceDto save(InvoiceDto dto) throws CustomException;
}
