package com.project.service;

import java.util.List;

import com.project.model.Stock;
import com.project.model.StockItem;

public interface StockService {

    List<StockItem> convertResponseJSONToList(String json);

    List<Stock> getAllStocksFromList(List<StockItem> list);

    String getJSONFromHTTP();

    boolean isMultipleOfUnit(Integer amount, Integer unit);

    void update(Stock stock);

    void roundPrices();

    void roundPrice(Stock stock);

    void subtractAmount(Stock stock, Integer amount);

    void addAmount(Stock stock, Integer amountToSell);

    void createUpdateAndRoundPrcies(List<Stock> stocksFromDatabaseList, List<Stock> stocksFromWebsiteList);

}
