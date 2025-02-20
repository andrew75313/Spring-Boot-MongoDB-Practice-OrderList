package org.example.orderlist.domain.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDescripResponseDto {
    private String productId;
    private String productName;
    private String description;
}
