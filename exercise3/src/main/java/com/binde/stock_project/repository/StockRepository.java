package com.binde.stock_project.repository;

import com.binde.stock_project.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
