package com.example.cryptoapp.util;

import com.example.cryptoapp.model.BTC;
import com.example.cryptoapp.model.ETH;
import com.example.cryptoapp.model.XRP;
import com.example.cryptoapp.service.BTCservice;
import com.example.cryptoapp.service.ETHservice;
import com.example.cryptoapp.service.XRPservice;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class CronScheluder {

    private BTCservice btcservice;
    private ETHservice ethservice;
    private XRPservice xrpservice;

    @Autowired
    public CronScheluder(BTCservice btcservice, ETHservice ethservice, XRPservice xrpservice) {
        this.btcservice = btcservice;
        this.ethservice = ethservice;
        this.xrpservice = xrpservice;
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void getPriceCryptoEveryTenSecond() throws IOException {
        var stringURLBtc = "https://cex.io/api/last_price/BTC/USD/";
        var stringURLEth = "https://cex.io/api/last_price/ETH/USD/";
        var stringURLXrp = "https://cex.io/api/last_price/XRP/USD/";
        URL urlBtc = new URL(stringURLBtc);
        URL urlEth = new URL(stringURLEth);
        URL urlXrp = new URL(stringURLXrp);
        HttpURLConnection requestBtc = (HttpURLConnection) urlBtc.openConnection();
        HttpURLConnection requestEth = (HttpURLConnection) urlEth.openConnection();
        HttpURLConnection requestXrp = (HttpURLConnection) urlXrp.openConnection();
        requestBtc.connect();
        requestEth.connect();
        requestXrp.connect();
        JsonParser jp = new JsonParser();
        JsonElement rootBtc = jp.parse(new InputStreamReader((InputStream) requestBtc.getContent()));
        JsonElement rootEth = jp.parse(new InputStreamReader((InputStream) requestEth.getContent()));
        JsonElement rootXrp = jp.parse(new InputStreamReader((InputStream) requestXrp.getContent()));
        var rootobjbtc = rootBtc.getAsJsonObject();
        var rootobjeth = rootEth.getAsJsonObject();
        var rootobjxrp = rootXrp.getAsJsonObject();
        Double priceBtc = rootobjbtc.get("lprice").getAsDouble();
        String nameCryptoPairBtc = rootobjbtc.get("curr2").getAsString();
        Double priceEth = rootobjeth.get("lprice").getAsDouble();
        String nameCryptoPairEth = rootobjeth.get("curr2").getAsString();
        Double priceXrp = rootobjxrp.get("lprice").getAsDouble();
        String nameCryptoPairXrp = rootobjxrp.get("curr2").getAsString();
        btcservice.add(new BTC(priceBtc, nameCryptoPairBtc));
        ethservice.add(new ETH(priceEth, nameCryptoPairEth));
        xrpservice.add(new XRP(priceXrp, nameCryptoPairXrp));
    }
}
