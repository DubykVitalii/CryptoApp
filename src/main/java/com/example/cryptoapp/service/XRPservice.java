package com.example.cryptoapp.service;


import com.example.cryptoapp.model.XRP;
import com.example.cryptoapp.repository.XRPrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class XRPservice {
    @Autowired
    private XRPrepo xrprepo;

    public XRP add(XRP xrp) {
        return xrprepo.save(xrp);
    }

    public Double findMaxPrice() {
        List<XRP> listXrp = xrprepo.findAll();
        return listXrp.stream().mapToDouble(XRP::getLprice).max().getAsDouble();
    }

    public Double findMinPrice() {
        List<XRP> listXrp = xrprepo.findAll();
        return listXrp.stream().mapToDouble(XRP::getLprice).min().getAsDouble();
    }

    public List<XRP> findLimitAll(Integer size, Integer page) {
        List<XRP> listXrp = xrprepo.findAll();
        listXrp = listXrp.stream().sorted(Comparator.comparing(XRP::getLprice)).collect(Collectors.toList());
        return listXrp.stream().skip(size * page).limit(size).collect(Collectors.toList());
    }
}
