package com.binde.stock_project.service;

import com.binde.stock_project.dto.requestdto.StockRequest;
import com.binde.stock_project.dto.response.StockResponse;
import com.binde.stock_project.model.Stock;
import org.springframework.data.domain.Page;

public interface StockService {
    StockResponse createStock(StockRequest request);
    Page<Stock> getListOfStocks(int pageNo, int pageSize, String sortBy);
    StockResponse getStockById(Long stockId);
    StockResponse updateStock(Long stockId, StockRequest request);
}
