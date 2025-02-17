package org.example.orderlist.domain.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        Product product = Product.builder()
                .name(productRequestDto.getName())
                .price(productRequestDto.getPrice())
                .build();

        productRepository.save(product);

        ProductResponseDto productResponseDto = new ProductResponseDto();

        productResponseDto.setId(product.getId());
        productResponseDto.setName(product.getName());
        productResponseDto.setPrice(product.getPrice());

        return productResponseDto;
    }
}
