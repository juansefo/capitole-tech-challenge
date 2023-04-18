package com.capitole.visibility.domain.ports;

import com.capitole.visibility.domain.agregate.Stock;

import java.util.List;

public interface StockRepository {

    List<Stock> findAll();

}
