package org.example.orderlist.domain.user;

import lombok.Getter;
import lombok.Setter;
import org.example.orderlist.domain.purchase.PurchaseDto;

import java.util.List;

@Getter
@Setter
public class UserResponseDto {
    private String id;
    private String name;
    private String email;
    private List<PurchaseDto> purchases;
}
