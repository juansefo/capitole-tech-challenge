package com.capitole.visibility.domain.entity;

import com.capitole.visibility.domain.vo.StockQuantity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StockBasicInformationQuantityTest {

    @Test
    public void isStock() {
        var stockOne = new StockQuantity(1);
        var stockTwo = new StockQuantity(2);

        Assertions.assertEquals(3, stockOne.add(stockTwo).value());
    }

    @Test
    public void defaultValidation() {

        var stockOne = StockQuantity.getDefault();

        Assertions.assertEquals(0, stockOne.value());
    }
}
