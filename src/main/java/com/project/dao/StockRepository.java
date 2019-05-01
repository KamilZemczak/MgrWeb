package com.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.model.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

    Stock findByCode(String code);
}
