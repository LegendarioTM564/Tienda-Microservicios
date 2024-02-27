package com.microservice.sales.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class SaleDTO {


    private Long id_sale;
    private LocalDateTime create_at;
    private Long id_cart;
    private double total_amount;
    private List<CartItemDTO> listItems;

}
