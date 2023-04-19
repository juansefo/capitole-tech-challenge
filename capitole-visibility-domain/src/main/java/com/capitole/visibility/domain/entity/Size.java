package com.capitole.visibility.domain.entity;

import com.capitole.visibility.domain.agregate.SizeBasicInformation;
import com.capitole.visibility.domain.vo.ProductId;
import com.capitole.visibility.domain.vo.SizeBackSoon;
import com.capitole.visibility.domain.vo.SizeId;
import com.capitole.visibility.domain.vo.IsSpecialSize;
import com.capitole.visibility.domain.vo.StockQuantity;

import java.util.Set;
import java.util.stream.Collectors;

public record Size(SizeId id,
                   ProductId productId,
                   SizeBackSoon sizeBackSoon,
                   IsSpecialSize isSpecialSize,
                   StockQuantity stockQuantity) {

    public boolean inStock(){
        return sizeBackSoon().value() || stockQuantity().value() > 0;
    }

    public static Size buildSize(SizeBasicInformation sizeBasicInformation, StockQuantity stockQuantity){
        return new Size(sizeBasicInformation.id(),
                        sizeBasicInformation.productId(),
                        sizeBasicInformation.sizeBackSoon(),
                        sizeBasicInformation.isSpecialSize(),
                        stockQuantity);
    }

    public static Set<Size> getByProductIdOrDefault(Set<Size> setSize, ProductId productId){
        return setSize.stream()
                      .filter(size -> size.productId.equals(productId))
                .collect(Collectors.toSet());
    }
}
