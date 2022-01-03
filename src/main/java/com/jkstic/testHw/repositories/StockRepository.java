package com.jkstic.testHw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jkstic.testHw.models.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

}
