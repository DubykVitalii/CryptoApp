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
@Document(collection = "eth")
public class ETH {
    @Id
    private BigInteger id;
    private Double lprice;
    private String pair;

    public ETH(Double lprice, String pair) {
        this.lprice = lprice;
        this.pair = pair;
    }
}
