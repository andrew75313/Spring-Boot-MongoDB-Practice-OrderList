package org.example.orderlist.domain.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductDescriptionRepository productDescriptionRepository;

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

    public ProductDescripResponseDto addProductDescription(ProductDescripRequestDto productDescripRequestDto) {
        Product foundProduct = productRepository.findById(productDescripRequestDto.getProductId()).orElseThrow(
                () -> new IllegalArgumentException("Product not found")
        );

        ProductDescription productDescription = ProductDescription.builder()
                .productId(foundProduct.getId())
                .description(productDescripRequestDto.getDescription())
                .build();

        productDescriptionRepository.save(productDescription);

        ProductDescripResponseDto productDescripResponseDto = new ProductDescripResponseDto();
        productDescripResponseDto.setProductId(foundProduct.getId());
        productDescripResponseDto.setProductName(foundProduct.getName());
        productDescripResponseDto.setDescription(productDescription.getDescription());

        return productDescripResponseDto;
    }
}
