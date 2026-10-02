package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.projections.SaleReportProjection;
import com.devsuperior.dsmeta.projections.SaleSummaryProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

    public Page<SaleReportDTO> getReport(String minDate, String maxDate, String name, Pageable pageable) {
        LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

        LocalDate max_date = maxDate != null ? LocalDate.parse(maxDate) : today;
        LocalDate min_date = minDate != null ? LocalDate.parse(minDate) : today.minusYears(1L);

        Page<SaleReportProjection> result = repository.getReport(max_date, min_date, name, pageable);

        return result.map(SaleReportDTO::new);
    }

    public Page<SaleSummaryDTO> getSummary(String minDate, String maxDate, Pageable pageable) {
        LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

        LocalDate max_date = maxDate != null ? LocalDate.parse(maxDate) : today;
        LocalDate min_date = minDate != null ? LocalDate.parse(minDate) : today.minusYears(1L);

        Page<SaleSummaryProjection> result = repository.getSummary(max_date, min_date, pageable);

        return result.map(SaleSummaryDTO::new);
    }
}
