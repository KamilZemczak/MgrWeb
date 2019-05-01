package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import com.project.dao.WalletRepository;
import com.project.model.Wallet;
import com.project.model.Stock;
import com.project.model.User;
import com.project.searcher.WalletSearcher;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private WalletSearcher walletSearcher;

    @Override
    public void save(Wallet wallet) {
        walletRepository.save(wallet);
    }

    @Override
    public void update(Wallet wallet) {
        walletRepository.save(wallet);
    }

    @Override
    public void roundValue() {
        for (Wallet wallet : userService.getCurrentUser().getUserWallet()) {
            BigDecimal valueToUpdate = wallet.getStock().getPrice().multiply(new BigDecimal(wallet.getAmount()));
            wallet.setValue(valueToUpdate.setScale(2, BigDecimal.ROUND_HALF_UP));
            update(wallet);
        }
    }

    @Override
    public void deleteOrUpdate(Wallet wallet, Integer amountToSell) {
        if (wallet.getAmount().intValue() == amountToSell.intValue()) {
            walletRepository.delete(wallet.getId());
        } else {
            wallet.setAmount(wallet.getAmount() - amountToSell);
            walletRepository.save(wallet);
        }
    }

    @Override
    public void createOrUpdate(Integer id, Integer amount, User user, Stock stock) {
        Wallet wallet = walletSearcher.findOneByUserAndStock(user, stock);
        if (wallet == null) {
            Wallet newWallet = create(amount, user, stock);
            save(newWallet);
        } else {
            update(wallet, amount, stock);
        }
    }

    private Wallet create(Integer amount, User user, Stock stock) {
        Wallet wallet = new Wallet();
        wallet.setAmount(amount);
        wallet.setUser(user);
        wallet.setStock(stock);
        wallet.setValue(stock.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(amount)));
        return wallet;
    }

    private void update(Wallet userWallet, Integer amount, Stock stock) {
        userWallet.setAmount(userWallet.getAmount() + amount);
        BigDecimal valueToUpdate = userWallet.getValue().add(stock.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(amount)));
        userWallet.setValue(valueToUpdate);
        update(userWallet);
    }
}
