package org.example.orderlist.domain.user;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    @Aggregation(pipeline = {
            "{ '$match': { 'purchases': { '$not': { '$elemMatch': { 'quantity': { '$gt': ?0 } } } } } }"
    })
    List<User> findUserWithPurchasesUnderMax(int maxQuantity);
}
