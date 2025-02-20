package org.example.orderlist.domain.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {
    private String id;
    private String name;
    private String description;
    private Long price;
}
