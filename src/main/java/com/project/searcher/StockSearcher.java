package com.project.searcher;

import java.util.List;

import com.project.model.Stock;

public interface StockSearcher {

    List<Stock> findAll();

    Stock findOne(int id);

}
