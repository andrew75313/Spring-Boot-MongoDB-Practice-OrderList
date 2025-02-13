package org.example.orderlist.domain.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products") // MongoDB의 "products" 컬렉션과 매핑
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    private String id;      // MongoDB의 기본 _id 필드 (ObjectId)

    private String name;    // 상품명
}