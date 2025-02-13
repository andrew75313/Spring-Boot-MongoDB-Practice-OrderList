package org.example.orderlist.domain.purchase;

import java.util.List;
import java.util.stream.Collectors;

public class PurchaseListMapper {
    public static List<PurchaseDto> toPurchaseDtoList(List<Purchase> purchases) {
        if (purchases == null) return null;

        return purchases.stream().map(purchase -> {
            PurchaseDto dto = new PurchaseDto();
            dto.setProductId(purchase.getProductId());
            dto.setQuantity(purchase.getQuantity());
            return dto;
        }).collect(Collectors.toList());
    }
}
