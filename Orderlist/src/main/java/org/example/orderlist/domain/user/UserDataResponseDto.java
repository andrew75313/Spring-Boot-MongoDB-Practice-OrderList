package org.example.orderlist.domain.user;

import lombok.Builder;
import lombok.Getter;
import org.example.orderlist.domain.purchase.PurchaseDto;

import java.util.List;

@Getter
@Builder
public class UserDataResponseDto {
    private String id;
    private String name;
    private String email;
    private List<PurchaseDto> purchases;
    private Long totalPrice;
}
