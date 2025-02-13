package org.example.orderlist.domain.purchase;

import lombok.Getter;

@Getter
public class Purchase {

    private String productId;  // 구입한 제품의 ID
    private int quantity;      // 구입한 갯수

    public Purchase(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
