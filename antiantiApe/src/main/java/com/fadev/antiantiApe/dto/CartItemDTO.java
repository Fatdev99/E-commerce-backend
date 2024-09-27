package com.fadev.antiantiApe.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemDTO {
    private Long id;
    private Integer quantity;
    private BigDecimal unitPrice;
    private ProductDTO productDTO;
}
