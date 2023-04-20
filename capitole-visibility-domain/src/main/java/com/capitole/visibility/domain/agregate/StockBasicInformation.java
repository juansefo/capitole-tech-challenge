package com.capitole.visibility.domain.agregate;

import com.capitole.visibility.domain.vo.SizeId;
import com.capitole.visibility.domain.vo.StockQuantity;

import java.util.List;

public record StockBasicInformation(SizeId sizeId,
                                    StockQuantity stockQuantity) {

    public static StockQuantity accumulateOrDefault(SizeId sizeId, List<StockBasicInformation> list) {
        return list.stream()
                   .filter(s -> s.sizeId().equals(sizeId))
                    .map(sbi -> sbi.stockQuantity)
                   .reduce(StockQuantity::add)
                   .orElse(StockQuantity.getDefault());
    }
}
