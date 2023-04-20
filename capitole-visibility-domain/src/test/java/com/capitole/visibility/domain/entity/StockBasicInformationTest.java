package com.capitole.visibility.domain.entity;

import com.capitole.visibility.domain.agregate.StockBasicInformation;
import com.capitole.visibility.domain.vo.SizeId;
import com.capitole.visibility.domain.vo.StockQuantity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class StockBasicInformationTest {

    @Test
    public void getAccumulateOrDefaultWhenStockExistAndIsZero() {

        var list = List.of(new StockBasicInformation(new SizeId(1), new StockQuantity(0)),
                           new StockBasicInformation(new SizeId(2), new StockQuantity(1)));

        var stock = StockBasicInformation.accumulateOrDefault(new SizeId(1), list);
        Assertions.assertEquals(0, stock.value());
    }

    @Test
    public void getAccumulateOrDefaultWhenStockExistAndIsOne() {

        var list = List.of(new StockBasicInformation(new SizeId(1), new StockQuantity(1)),
                           new StockBasicInformation(new SizeId(2), new StockQuantity(1)));

        var stock = StockBasicInformation.accumulateOrDefault(new SizeId(1), list);
        Assertions.assertEquals(1, stock.value());
    }

    @Test
    public void getAccumulateOrDefaultWhenStockDoesntExist() {

        var list = List.of(new StockBasicInformation(new SizeId(1), new StockQuantity(1)),
                           new StockBasicInformation(new SizeId(2), new StockQuantity(1)));

        var stock = StockBasicInformation.accumulateOrDefault(new SizeId(3), list);
        Assertions.assertEquals(0, stock.value());
    }
}
