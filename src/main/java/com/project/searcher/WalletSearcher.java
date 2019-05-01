package com.project.searcher;

import java.util.List;

import com.project.model.Stock;
import com.project.model.User;

import com.project.model.Wallet;

public interface WalletSearcher {

    List<Wallet> findAll();

    Wallet findOne(Integer id);

    Wallet findByStockId(Integer id);

    Wallet findOneByUserAndStock(User user, Stock stock);
}
