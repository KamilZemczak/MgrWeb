package com.project.searcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.project.dao.StockRepository;
import com.project.model.Stock;

@Service
public class StockSercherImpl implements StockSearcher {

    @Autowired
    private StockRepository stockRepository;

    @Override
    public List<Stock> findAll() {
        return stockRepository.findAll();
    }

    @Override
    public Stock findOne(int id) {
        return stockRepository.findOne(id);
    }
}
