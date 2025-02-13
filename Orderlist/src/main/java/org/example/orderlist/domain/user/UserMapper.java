package org.example.orderlist.domain.user;

import org.example.orderlist.domain.purchase.PurchaseListMapper;

public class UserMapper {
    public static UserResponseDto toDto(User user) {
        if (user == null) return null;

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setName(user.getName());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setPurchases(PurchaseListMapper.toPurchaseDtoList(user.getPurchases()));

        return userResponseDto;
    }
}
