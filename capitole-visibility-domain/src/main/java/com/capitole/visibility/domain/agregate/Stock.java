package com.capitole.visibility.domain.agregate;

import com.capitole.visibility.domain.vo.SizeId;
import com.capitole.visibility.domain.vo.StockQuantity;

public record Stock(SizeId id,
                    StockQuantity stockQuantity) {

    public boolean inStock(){
        return stockQuantity.value() > 0;
    }
}
