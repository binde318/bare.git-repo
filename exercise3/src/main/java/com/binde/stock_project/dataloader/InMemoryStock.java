package com.binde.stock_project.dataloader;

import com.binde.stock_project.model.Stock;
import com.binde.stock_project.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InMemoryStock implements CommandLineRunner {
    private final StockRepository stockRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Stock> stockList = new ArrayList<>();
        stockList.add(new Stock(1L, "Stock1", new BigDecimal("100.00"), new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())));
        stockList.add(new Stock(2L, "Stock2", new BigDecimal("200.00"), new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())));
        stockList.add(new Stock(3L, "Stock3", new BigDecimal("300.00"), new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())));
        stockList.add(new Stock(4L, "Stock4", new BigDecimal("400.00"), new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())));
        stockList.add(new Stock(5L, "Stock5", new BigDecimal("500.00"), new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())));
        stockList.add(new Stock(6L, "Stock6", new BigDecimal("600.00"), new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())));
        stockList.add(new Stock(7L, "Stock7", new BigDecimal("700.00"), new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())));
        stockList.add(new Stock(8L, "Stock8", new BigDecimal("800.00"), new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())));
        stockList.add(new Stock(9L,"Stock9",new BigDecimal("1000.00"),new Timestamp(System.currentTimeMillis()),
               new Timestamp(System.currentTimeMillis())));
        stockList.add(new Stock(10L,"Stock10",new BigDecimal("11000.00"), new Timestamp(System.currentTimeMillis()),
               new Timestamp(System.currentTimeMillis())));
        stockList.add(new Stock(11L,"Stock11",new BigDecimal("11000.00"), new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())));
        stockList.add(new Stock(12L,"Stock12",new BigDecimal("12000.00"), new Timestamp(System.currentTimeMillis()),
               new Timestamp(System.currentTimeMillis())));
        stockList.add(new Stock(13L,"Stock13",new BigDecimal("13000.00"), new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())));
        stockList.add(new Stock(14L,"Stock14",new BigDecimal("14000.00"), new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())));
        stockRepository.saveAll(stockList);

    }
}
