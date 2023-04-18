package com.capitole.visibility.domain.agregate;

import com.capitole.visibility.domain.vo.SizeId;
import com.capitole.visibility.domain.vo.StockQuantity;

import java.util.List;

public record Stock(SizeId id,
                    StockQuantity stockQuantity) {

    public boolean inStock() {
        return stockQuantity.value() > 0;
    }

    public static Stock accumulateOrDefault(SizeId sizeId, List<Stock> list) {
        return list.stream()
                   .filter(s -> s.id.equals(sizeId))
                   .reduce((stock, stock2) ->
                                   new Stock(stock.id,
                                             stock.stockQuantity().add(stock2.stockQuantity)))
                   .orElse(new Stock(sizeId, StockQuantity.getDefault()));
    }
}
