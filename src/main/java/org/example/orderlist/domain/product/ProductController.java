package org.example.orderlist.domain.product;

import lombok.RequiredArgsConstructor;
import org.example.orderlist.domain.file.CsvService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/products/")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CsvService csvService;

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto productRequestDto) {
        ProductResponseDto response = productService.createProduct(productRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable String id) {
        ProductResponseDto response = productService.getProduct(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @PostMapping("/describe")
        public ResponseEntity<ProductDescripResponseDto> addProductDescription(@RequestBody ProductDescripRequestDto productDescripRequestDto) {
        ProductDescripResponseDto response = productService.addProductDescription(productDescripRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadCsvFile(@RequestParam("file") MultipartFile file) {
        try {
            csvService.uploadProductCsv(file);
            return ResponseEntity.ok("CSV 파일 업로드 완료");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
