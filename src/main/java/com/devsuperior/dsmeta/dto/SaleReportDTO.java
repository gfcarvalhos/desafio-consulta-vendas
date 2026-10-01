package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.entities.Seller;
import com.devsuperior.dsmeta.projections.SaleReportProjection;

import java.time.LocalDate;

public class SaleReportDTO extends SaleMinDTO {

    private String sellerName;

    public SaleReportDTO(Long id, Double amount, LocalDate date, String sellerName) {
        super(id, amount, date);
        this.sellerName = sellerName;
    }

    public SaleReportDTO(SaleReportProjection projection) {
        super(projection.getId(), projection.getAmount(), projection.getDate());
        sellerName = projection.getSellerName();
    }

    public String getSellerName() {
        return sellerName;
    }
}
