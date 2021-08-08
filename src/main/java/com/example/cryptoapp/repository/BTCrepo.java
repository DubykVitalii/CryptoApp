package com.example.cryptoapp.repository;

import com.example.cryptoapp.model.BTC;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface BTCrepo extends MongoRepository<BTC, BigInteger> {

}
