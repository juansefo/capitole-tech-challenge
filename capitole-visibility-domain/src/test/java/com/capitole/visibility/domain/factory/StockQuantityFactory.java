package com.capitole.visibility.domain.factory;

import com.capitole.visibility.domain.vo.StockQuantity;

public class StockQuantityFactory {

    public static StockQuantity buildStockQuantityDefault(){
        return StockQuantity.getDefault();
    }

}
