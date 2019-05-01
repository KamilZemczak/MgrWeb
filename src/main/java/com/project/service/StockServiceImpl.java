package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.project.dao.StockRepository;
import com.project.model.Stock;
import com.project.model.StockItem;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;

    @Override
    public List<StockItem> convertResponseJSONToList(String stocksJsonFromWebsite) {
        List<StockItem> responseFromWebsite = new ArrayList<StockItem>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            responseFromWebsite = objectMapper.readValue(stocksJsonFromWebsite, new TypeReference<List<StockItem>>() {
            });
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseFromWebsite;
    }

    @Override
    public List<Stock> getAllStocksFromList(List<StockItem> responseFromHTTPList) {
        List<Stock> stocksList = new ArrayList<Stock>();
        for (StockItem stockItem : responseFromHTTPList) {
            stocksList = stockItem.getItems();
        }
        return stocksList;
    }

    @Override
    public String getJSONFromHTTP() {
        String stocksJsonFromWebsite = null;
        try {
            URL url = new URL("http://webtask.future-processing.com:8068/stocks");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            setProperties(connection);
            try (InputStream inputStream = connection.getInputStream()) {
                stocksJsonFromWebsite = streamToString(inputStream);
            }
            connection.disconnect();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return stocksJsonFromWebsite;
    }

    @Override
    public boolean isMultipleOfUnit(Integer amount, Integer unit) {
        return (amount % unit == 0);
    }

    @Override
    public void update(Stock stock) {
        stockRepository.save(stock);
    }

    @Override
    public void roundPrices() {
        for (Stock stock : stockRepository.findAll()) {
            stock.setPrice(round(stock.getPrice()));
        }
    }

    @Override
    public void roundPrice(Stock stock) {
        stock.setPrice(round(stock.getPrice()));
    }

    @Override
    public void subtractAmount(Stock stock, Integer amount) {
        stock.setAmount(stock.getAmount() - amount);
    }

    @Override
    public void addAmount(Stock stock, Integer amountToSell) {
        stock.setAmount(stock.getAmount() + amountToSell);
    }

    @Override
    public void createUpdateAndRoundPrcies(List<Stock> stocksFromDatabaseList, List<Stock> stocksFromWebsiteList) {
        createOrUpdatePrices(stocksFromDatabaseList, stocksFromWebsiteList);
        roundPrices();
    }

    private BigDecimal round(BigDecimal bigDecimal) {
        return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    private static String streamToString(InputStream inputStream) {
        String text = new Scanner(inputStream, "UTF-8").useDelimiter("\\Z").next();
        return text;
    }

    private void createOrUpdatePrices(List<Stock> stocksFromDatabaseList, List<Stock> stocksFromWebsiteList) {
        if (stocksFromDatabaseList.isEmpty()) {
            Stock stockToAdd = new Stock();
            create(stocksFromWebsiteList, stockToAdd);
        } else {
            updateStocksPrice(stocksFromWebsiteList);
        }
    }

    private void create(List<Stock> stocksFromWebsiteList, Stock stockToAdd) {
        Integer id = 1;
        Integer amount = 1000;
        for (Stock stock : stocksFromWebsiteList) {
            stockToAdd.setId(id);
            stockToAdd.setName(stock.getName());
            stockToAdd.setCode(stock.getCode());
            stockToAdd.setUnit(stock.getUnit());
            stockToAdd.setPrice(stock.getPrice());
            stockToAdd.setAmount(amount);
            stockRepository.save(stockToAdd);
            id++;
            amount += 1000;
        }
    }

    private void updateStocksPrice(List<Stock> stocksFromWebsiteList) {
        for (Stock stock : stocksFromWebsiteList) {
            Stock stockToUpdate = stockRepository.findByCode(stock.getCode());
            stockToUpdate.setPrice(stock.getPrice());
            update(stockToUpdate);
        }
    }

    private void setProperties(HttpURLConnection connection) throws IOException, ProtocolException {
        connection.setDoOutput(true);
        connection.setInstanceFollowRedirects(false);
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("charset", "utf-8");
        connection.connect();
    }
}
