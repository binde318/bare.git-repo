package com.binde.stock_project.controller;


import com.binde.stock_project.dto.requestdto.StockRequest;
import com.binde.stock_project.dto.response.ApiResponse;
import com.binde.stock_project.dto.response.StockResponse;
import com.binde.stock_project.repository.StockRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class StockControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StockRepository stockRepository;

    @BeforeEach
    public void setup() {
        stockRepository.deleteAll();
    }

    @Test
    public void testCreateStock() throws Exception {
        StockRequest request = new StockRequest();
        request.setName("Stock1");
        request.setCurrentPrice(BigDecimal.valueOf(100.0));

        mockMvc.perform(post("/api/stocks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value(true))
                .andExpect(jsonPath("$.data.name").value("Stock1"))
                .andExpect(jsonPath("$.data.currentPrice").value(100.0));
    }

    @Test
    public void testGetStockById() throws Exception {
        // First, create a stock
        StockRequest createRequest = new StockRequest();
        createRequest.setName("Stock2");
        createRequest.setCurrentPrice(BigDecimal.valueOf(120.0));

        String jsonResponse = mockMvc.perform(post("/api/stocks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andReturn().getResponse().getContentAsString();

        ApiResponse<StockResponse> apiResponse = objectMapper.readValue(jsonResponse, new TypeReference<>() {});
        StockResponse createdStock = apiResponse.getData();
        // Then, get the stock by ID
        mockMvc.perform(get("/api/stocks/{id}", createdStock.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(true))
                .andExpect(jsonPath("$.data.name").value("Stock2"))
                .andExpect(jsonPath("$.data.currentPrice").value(120.0));
    }

    @Test
    public void testUpdateStock() throws Exception {
        // First, create a stock
        StockRequest createRequest = new StockRequest();
        createRequest.setName("Stock3");
        createRequest.setCurrentPrice(BigDecimal.valueOf(140.0));

        String jsonResponse = mockMvc.perform(post("/api/stocks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andReturn().getResponse().getContentAsString();

        ApiResponse<StockResponse> apiResponse = objectMapper.readValue(jsonResponse, new TypeReference<>() {});
        StockResponse createdStock = apiResponse.getData();

        // Then, update the stock
        StockRequest updateRequest = new StockRequest();
        updateRequest.setName("UpdatedStock");
        updateRequest.setCurrentPrice(BigDecimal.valueOf(200.0));

        mockMvc.perform(put("/api/stocks/{id}", createdStock.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(true))
                .andExpect(jsonPath("$.data.name").value("UpdatedStock"))
                .andExpect(jsonPath("$.data.currentPrice").value(200.0));
    }


    @Test
    public void testGetAllStocks() throws Exception {
        // Create some stocks
        StockRequest request1 = new StockRequest();
        request1.setName("Stock4");
        request1.setCurrentPrice(BigDecimal.valueOf(180.0));

        StockRequest request2 = new StockRequest();
        request2.setName("Stock5");
        request2.setCurrentPrice(BigDecimal.valueOf(190.0));

        mockMvc.perform(post("/api/stocks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request1)));

        mockMvc.perform(post("/api/stocks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request2)));

        // Get all stocks
        mockMvc.perform(get("/api/stocks")
                        .param("pageNo", "0")
                        .param("pageSize", "10")
                        .param("sortBy", "id")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(true))
                .andExpect(jsonPath("$.data.content[0].name").value("Stock4"))
                .andExpect(jsonPath("$.data.content[0].currentPrice").value(180.0))
                .andExpect(jsonPath("$.data.content[1].name").value("Stock5"))
                .andExpect(jsonPath("$.data.content[1].currentPrice").value(190.0));
    }
}

