package com.example.cryptoapp.service;

import com.example.cryptoapp.model.ETH;
import com.example.cryptoapp.repository.ETHrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ETHservice {

    @Autowired
    private ETHrepo ethrepo;

    public ETH add(ETH eth) {
        return ethrepo.save(eth);
    }

    public Double findMaxPrice() {
        List<ETH> listEth = ethrepo.findAll();
        return listEth.stream().mapToDouble(ETH::getLprice).max().getAsDouble();
    }

    public Double findMinPrice() {
        List<ETH> listEth = ethrepo.findAll();
        return listEth.stream().mapToDouble(ETH::getLprice).min().getAsDouble();
    }

    public List<ETH> findLimitAll(Integer size, Integer page) {
        List<ETH> listEth = ethrepo.findAll();
        listEth = listEth.stream().sorted(Comparator.comparing(ETH::getLprice)).collect(Collectors.toList());
        return listEth.stream().skip(size * page).limit(size).collect(Collectors.toList());
    }

}
