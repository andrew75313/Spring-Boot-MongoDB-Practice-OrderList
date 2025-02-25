package org.example.orderlist.domain.user;

import lombok.Builder;
import lombok.Getter;
import org.example.orderlist.domain.purchase.Purchase;

import java.util.List;

@Getter
@Builder
public class UserResponseDto {
    private String id;
    private String name;
    private String email;
    private List<Purchase> purchases;
}
