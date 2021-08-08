package com.example.cryptoapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "btc")
public class BTC {

    @Id
    private BigInteger id;
    private Double lprice;
    private String nameCryptoPair;

    public BTC(Double lprice, String nameCryptoPair) {
        this.lprice = lprice;
        this.nameCryptoPair = nameCryptoPair;
    }
}
