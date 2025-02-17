package org.example.orderlist.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.orderlist.domain.purchase.Purchase;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserRequestDto {
    private String name;
    private String email;
    private List<Purchase> purchases;
}
