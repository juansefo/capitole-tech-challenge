package com.capitole.visibility.domain.entity;

import com.capitole.visibility.domain.agregate.SizeBasicInformation;
import com.capitole.visibility.domain.agregate.StockBasicInformation;
import com.capitole.visibility.domain.factory.SizeFactory;
import com.capitole.visibility.domain.vo.IsSpecialSize;
import com.capitole.visibility.domain.vo.ProductId;
import com.capitole.visibility.domain.vo.SizeBackSoon;
import com.capitole.visibility.domain.vo.SizeId;
import com.capitole.visibility.domain.vo.StockQuantity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SizeTest {

    @Test
    public void sizeInStock() {

        var size = SizeFactory.specialSizeBackSoonWithStock(1);

        Assertions.assertTrue(size.inStock());
    }

    @Test
    public void sizeNoStock() {

        var size = SizeFactory.noSpecialSizeNoBackSoonWithOutStock(1);

        Assertions.assertFalse(size.inStock());
    }


    @Test
    public void buildSizeFromStockInformationAndProductId() {

        var sizeBasicInformation = new SizeBasicInformation(new SizeId(1),
                                            new ProductId(1),
                                            new SizeBackSoon(true),
                                            new IsSpecialSize(false));

        var size = Size.buildSize(sizeBasicInformation,new StockQuantity(0));
        Assertions.assertFalse(size.isSpecialSize().value());
        Assertions.assertTrue(size.sizeBackSoon().value());
        Assertions.assertEquals(1,size.productId().value());
        Assertions.assertEquals(1,size.id().value());

    }


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
