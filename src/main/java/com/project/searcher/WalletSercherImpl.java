package com.project.searcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.project.dao.WalletRepository;
import com.project.model.Stock;
import com.project.model.User;
import com.project.model.Wallet;

@Service
public class WalletSercherImpl implements WalletSearcher {

    @Autowired
    private WalletRepository walletRepository;

    @Override
    public List<Wallet> findAll() {
        return walletRepository.findAll();
    }

    @Override
    public Wallet findOne(Integer id) {
        return walletRepository.findOne(id);
    }

    @Override
    public Wallet findByStockId(Integer id) {
        return walletRepository.findByStockId(id);
    }

    @Override
    public Wallet findOneByUserAndStock(User user, Stock stock) {
        return walletRepository.findOneByUserAndStock(user, stock);
    }
}
