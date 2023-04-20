package com.capitole.visibility.domain.factory;

import com.capitole.visibility.domain.entity.Product;
import com.capitole.visibility.domain.vo.ProductId;
import com.capitole.visibility.domain.vo.ProductSequence;

public class ProductFactory {

    public static Product productHighPositionNoStock(){
        return new Product(new ProductId(1),
                           new ProductSequence(1),
                           SizeFactory.noStockNoSpecial(1));
    }

    public static Product productHighPositionStock(){
        return new Product(new ProductId(2),
                           new ProductSequence(1),
                           SizeFactory.noStockNoSpecial(1));
    }

}
