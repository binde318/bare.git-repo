package com.binde.stock_project.dto.requestdto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockRequest {
    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "currentPrice is required")
    private BigDecimal currentPrice;

}
