package com.example.cryptoapp.service;

import com.example.cryptoapp.model.BTC;
import com.example.cryptoapp.repository.BTCrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BTCservice {


    private BTCrepo btcrepo;

    @Autowired
    public BTCservice(BTCrepo btcrepo) {
        this.btcrepo = btcrepo;
    }

    public BTC add(BTC btc) {
        return btcrepo.save(btc);
    }

    public Double findMaxPrice() {
        List<BTC> listBtc = btcrepo.findAll();
        return listBtc.stream().mapToDouble(BTC::getLprice).max().getAsDouble();
    }

    public Double findMinPrice() {
        List<BTC> listBtc = btcrepo.findAll();
        return listBtc.stream().mapToDouble(BTC::getLprice).min().getAsDouble();
    }

    public List<BTC> findLimitAll(Integer size, Integer page) {
        List<BTC> btcList = btcrepo.findAll();
        btcList = btcList.stream().sorted(Comparator.comparing(BTC::getLprice)).collect(Collectors.toList());
        return btcList.stream().skip(size * page).limit(size).collect(Collectors.toList());
    }

}
