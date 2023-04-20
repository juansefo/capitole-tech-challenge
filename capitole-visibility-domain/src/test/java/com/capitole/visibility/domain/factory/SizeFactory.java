package com.capitole.visibility.domain.factory;

import com.capitole.visibility.domain.entity.Size;
import com.capitole.visibility.domain.vo.IsSpecialSize;
import com.capitole.visibility.domain.vo.ProductId;
import com.capitole.visibility.domain.vo.SizeBackSoon;
import com.capitole.visibility.domain.vo.SizeId;
import com.capitole.visibility.domain.vo.StockQuantity;

import java.util.Set;

public class SizeFactory {

    public static Set<Size> noStockNoSpecial(int productId) {
        return Set.of(noSpecialSizeNoBackSoonWithOutStock(productId));
    }

    public static Set<Size> stockOneSpecialOneStock(int productId) {
        return Set.of(specialSizeBackSoonWithStock(productId),
                      noSpecialSizeNoBackSoonWithStock(productId),
                      specialSizeBackSoonWithOutStock(productId));
    }

    public static Size specialSizeBackSoonWithStock(int productId) {
        return new Size(new SizeId(1),
                        new ProductId(productId),
                        new SizeBackSoon(true),
                        new IsSpecialSize(true),
                        new StockQuantity(1)
        );
    }

    private static Size specialSizeBackSoonWithOutStock(int productId) {
        return new Size(new SizeId(2),
                        new ProductId(productId),
                        new SizeBackSoon(true),
                        new IsSpecialSize(true),
                        new StockQuantity(0)
        );
    }

    private static Size noSpecialSizeNoBackSoonWithStock(int productId) {
        return new Size(new SizeId(3),
                        new ProductId(productId),
                        new SizeBackSoon(false),
                        new IsSpecialSize(false),
                        new StockQuantity(1)
        );
    }

    public static Size noSpecialSizeNoBackSoonWithOutStock(int productId) {
        return new Size(new SizeId(4),
                        new ProductId(productId),
                        new SizeBackSoon(false),
                        new IsSpecialSize(true),
                        new StockQuantity(0)
        );
    }

}
