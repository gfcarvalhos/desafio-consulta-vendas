package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.projections.SaleReportProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(value = "SELECT obj.id AS id, obj.amount AS amount, obj.date AS date, obj.seller.name AS sellerName " +
            "FROM Sale obj JOIN obj.seller " +
            "WHERE obj.date BETWEEN :min_date AND :max_date " +
            "AND UPPER(obj.seller.name) LIKE CONCAT('%', UPPER(:name), '%')")
    Page<SaleReportProjection> getReport(LocalDate max_date, LocalDate min_date, String name, Pageable pageable);
}
