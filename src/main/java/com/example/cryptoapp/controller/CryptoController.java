package com.example.cryptoapp.controller;

import au.com.bytecode.opencsv.CSVWriter;
import com.example.cryptoapp.model.BTC;
import com.example.cryptoapp.model.ETH;
import com.example.cryptoapp.model.XRP;
import com.example.cryptoapp.service.BTCservice;
import com.example.cryptoapp.service.ETHservice;
import com.example.cryptoapp.service.XRPservice;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/cryptocurrencies")
public class CryptoController {
    @Autowired
    private BTCservice btcservice;
    @Autowired
    private ETHservice ethservice;
    @Autowired
    private XRPservice xrpservice;

    @GetMapping(
            value = "/maxprice",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> getMax(
            @RequestParam String currency_name
    ) {

        switch (currency_name.toUpperCase(Locale.ROOT)) {
            case ("BTC"):
                Double maxPriceBtc = btcservice.findMaxPrice();
                return new ResponseEntity<>("Highest price BTC/USD - " + maxPriceBtc, HttpStatus.OK);
            case ("ETH"):
                Double maxPriceEth = ethservice.findMaxPrice();
                return new ResponseEntity<>("Highest price ETH/USD - " + maxPriceEth, HttpStatus.OK);
            case ("XRP"):
                Double maxPriceXrp = xrpservice.findMaxPrice();
                return new ResponseEntity<>("Highest price XRP/USD - " + maxPriceXrp, HttpStatus.OK);
            default:
                return new ResponseEntity<>("Object not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(
            value = "/minprice",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> getMin(
            @RequestParam String currency_name
    ) {

        switch (currency_name.toUpperCase(Locale.ROOT)) {
            case ("BTC"):
                Double minPriceBtc = btcservice.findMinPrice();
                return new ResponseEntity<>("Lowest price BTC/USD - " + minPriceBtc, HttpStatus.OK);
            case ("ETH"):
                Double minPriceEth = ethservice.findMinPrice();
                return new ResponseEntity<>("Lowest price ETH/USD - " + minPriceEth, HttpStatus.OK);
            case ("XRP"):
                Double minPriceXrp = xrpservice.findMinPrice();
                return new ResponseEntity<>("Lowest price XRP/USD - " + minPriceXrp, HttpStatus.OK);
            default:
                return new ResponseEntity<>("Object not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> getListCrypto(
            @RequestParam String currency_name,
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "10", required = false) Integer size
    ) {

        switch (currency_name.toUpperCase(Locale.ROOT)) {
            case ("BTC"):
                List<BTC> btcList = btcservice.findLimitAll(size, page);
                return new ResponseEntity<>(btcList, HttpStatus.OK);
            case ("ETH"):
                List<ETH> ethList = ethservice.findLimitAll(size, page);
                return new ResponseEntity<>(ethList, HttpStatus.OK);
            case ("XRP"):
                List<XRP> xrpList = xrpservice.findLimitAll(size, page);
                return new ResponseEntity<>(xrpList, HttpStatus.OK);
            default:
                return new ResponseEntity<>("Object not found", HttpStatus.NOT_FOUND);
        }
    }


    @SneakyThrows
    @GetMapping(
            value = "/csv",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> getCSV(
            @RequestParam String currency_name
    ) throws IOException {

        Double minPrice;
        Double maxPrice;
        switch (currency_name.toUpperCase(Locale.ROOT)) {
            case ("BTC"):
                maxPrice = btcservice.findMaxPrice();
                minPrice = btcservice.findMinPrice();
                break;
            case ("ETH"):
                maxPrice = ethservice.findMaxPrice();
                minPrice = ethservice.findMinPrice();
                break;
            case ("XRP"):
                maxPrice = xrpservice.findMaxPrice();
                minPrice = xrpservice.findMinPrice();
                break;
            default:
                return new ResponseEntity<>("Object not found", HttpStatus.NOT_FOUND);
        }
        String csv = "data.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(csv));
        String[] record = ("Name:" + currency_name.toUpperCase(Locale.ROOT) + ",Max Price:" + maxPrice.toString() + ",Min Price:" + minPrice.toString()).split(",");
        writer.writeNext(record);
        writer.close();
        return new ResponseEntity<>(record, HttpStatus.OK);
    }

}
