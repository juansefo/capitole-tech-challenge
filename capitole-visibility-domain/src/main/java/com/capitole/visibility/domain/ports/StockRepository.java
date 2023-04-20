package com.capitole.visibility.domain.ports;

import com.capitole.visibility.domain.agregate.StockBasicInformation;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface StockRepository {

    CompletableFuture<List<StockBasicInformation>> findAll();

}
