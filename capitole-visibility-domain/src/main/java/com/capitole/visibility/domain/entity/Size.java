package com.capitole.visibility.domain.entity;

import com.capitole.visibility.domain.agregate.SizeBasicInformation;
import com.capitole.visibility.domain.agregate.Stock;
import com.capitole.visibility.domain.vo.ProductId;
import com.capitole.visibility.domain.vo.SizeBackSoon;
import com.capitole.visibility.domain.vo.SizeId;
import com.capitole.visibility.domain.vo.IsSpecialSize;

import java.util.Set;
import java.util.stream.Collectors;

public record Size(SizeId id,
                   ProductId productId,
                   SizeBackSoon sizeBackSoon,
                   IsSpecialSize isSpecialSize,
                   Stock stock) {

    public boolean inStock(){
        return sizeBackSoon().value() || stock().inStock();
    }

    public static Size buildSize(SizeBasicInformation sizeBasicInformation, Stock stock){
        return new Size(sizeBasicInformation.id(),
                        sizeBasicInformation.productId(),
                        sizeBasicInformation.sizeBackSoon(),
                        sizeBasicInformation.isSpecialSize(),
                        stock);
    }

    public Set<Size> getByProductIdOrDefault(Set<Size> setSize, ProductId productId){
        return setSize.stream()
                      .filter(size -> size.productId.equals(productId))
                .collect(Collectors.toSet());
    }
}
