package com.example.cryptoapp.repository;

import com.example.cryptoapp.model.XRP;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;

@Repository
public interface XRPrepo extends MongoRepository<XRP, BigInteger> {
}
