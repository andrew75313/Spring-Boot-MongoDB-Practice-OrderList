package org.example.orderlist.domain.purchase;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseDto {
    private String productId;
    private String productName;
    private int quantity;
    private Long subTotal;
}
