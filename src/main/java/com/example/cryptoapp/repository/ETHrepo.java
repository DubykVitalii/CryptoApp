package com.example.cryptoapp.repository;

import com.example.cryptoapp.model.ETH;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
@Repository
public interface ETHrepo extends MongoRepository<ETH, BigInteger> {
}
