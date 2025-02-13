package org.example.orderlist.domain.user;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.orderlist.domain.purchase.Purchase;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String id;         // MongoDB의 기본 _id 필드 (ObjectId)

    private String name;       // 사용자 이름
    private String email;      // 이메일
    private List<Purchase> purchases; // 사용자가 구입한 제품 목록
}
