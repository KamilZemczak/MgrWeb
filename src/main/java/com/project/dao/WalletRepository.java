package com.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.model.Stock;
import com.project.model.User;
import com.project.model.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer> {

    Wallet findByStockId(Integer id);

    @Query("SELECT w from Wallet w where w.user = :user and w.stock = :stock")
    Wallet findOneByUserAndStock(@Param("user") User user, @Param("stock") Stock stock);

}
