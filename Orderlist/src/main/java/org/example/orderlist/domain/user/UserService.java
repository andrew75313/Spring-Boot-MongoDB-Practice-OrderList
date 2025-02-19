package org.example.orderlist.domain.user;

import lombok.RequiredArgsConstructor;
import org.example.orderlist.domain.product.Product;
import org.example.orderlist.domain.product.ProductRepository;
import org.example.orderlist.domain.purchase.Purchase;
import org.example.orderlist.domain.purchase.PurchaseDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public UserResponseDto createUser(UserRequestDto userRequestDto) {

        List<Purchase> purchases = userRequestDto.getPurchases().stream()
                .map(dto -> Purchase.builder()
                        .productId(dto.getProductId())
                        .quantity(dto.getQuantity())
                        .build()
                )
                .toList();

        User user = User.builder()
                .name(userRequestDto.getName())
                .email(userRequestDto.getEmail())
                .purchases(purchases)
                .build();

        User savedUser = userRepository.save(user);

        return UserResponseDto.builder()
                .id(savedUser.getId())
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .purchases(purchases)
                .build();
    }

    public UserDataResponseDto getUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("찾을 수 없음"));

        return this.toUserDataResponseDto(user);
    }

    public List<UserDataResponseDto> getUserByMaxQuantity(int maxQuantity) {
        List<User> foundUserList = userRepository.findUserWithPurchasesUnderMax(maxQuantity);
        List<UserDataResponseDto> responseDtoList = new ArrayList<>();

        for(User user : foundUserList) {
            UserDataResponseDto userDataResponseDto = this.toUserDataResponseDto(user);

            responseDtoList.add(userDataResponseDto);
        }

        return responseDtoList;
    }

    private PurchaseDto toPurchaseDto(Purchase purchase) {
        PurchaseDto purchaseDto = new PurchaseDto();

        String productName = productRepository.findById(purchase.getProductId())
                .map(Product::getName)
                .orElse("NOT FOUND");

        Long productPrice = productRepository.findById(purchase.getProductId())
                .map(Product::getPrice)
                .orElse(0L);

        Long subTotal = purchase.getQuantity() * productPrice;

        purchaseDto.setProductId(purchase.getProductId());
        purchaseDto.setProductName(productName);
        purchaseDto.setQuantity(purchase.getQuantity());
        purchaseDto.setSubTotal(subTotal);

        return purchaseDto;
    }

    private UserDataResponseDto toUserDataResponseDto(User user) {
        List<PurchaseDto> purchaseDtoList = new ArrayList<>();

        Long totalPrice = 0L;

        for(Purchase purchase : user.getPurchases()) {
            PurchaseDto purchaseDto = this.toPurchaseDto(purchase);

            totalPrice += purchaseDto.getSubTotal();
            purchaseDtoList.add(purchaseDto);
        }

        return UserDataResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .purchases(purchaseDtoList)
                .totalPrice(totalPrice)
                .build();
    }
}
