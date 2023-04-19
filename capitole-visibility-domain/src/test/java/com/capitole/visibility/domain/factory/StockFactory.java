package com.capitole.visibility.domain.factory;

import com.capitole.visibility.domain.agregate.StockBasicInformation;
import com.capitole.visibility.domain.vo.SizeId;
import com.capitole.visibility.domain.vo.StockQuantity;

public class StockFactory {

    public static StockBasicInformation buildStock(){
        return new StockBasicInformation(new SizeId(1),
                                         new StockQuantity(1));
    }

    public static StockBasicInformation buildNoStock(){
        return new StockBasicInformation(new SizeId(1),
                                         new StockQuantity(1));
    }
}
