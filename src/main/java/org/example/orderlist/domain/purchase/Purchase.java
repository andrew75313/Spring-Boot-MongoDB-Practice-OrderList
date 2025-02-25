package org.example.orderlist.domain.purchase;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Purchase {

    private String productId;
    private int quantity;
}
