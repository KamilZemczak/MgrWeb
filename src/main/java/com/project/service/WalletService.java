package com.project.service;

import com.project.model.Stock;
import com.project.model.User;
import com.project.model.Wallet;

public interface WalletService {

    void save(Wallet wallet);

    void update(Wallet wallet);

    void roundValue();

    void deleteOrUpdate(Wallet wallet, Integer amountToSell);

    void createOrUpdate(Integer id, Integer amount, User user, Stock stock);
}
