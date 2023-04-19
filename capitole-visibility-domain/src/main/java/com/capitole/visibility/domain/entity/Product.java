package com.capitole.visibility.domain.entity;

import com.capitole.visibility.domain.agregate.ProductBasicInformation;
import com.capitole.visibility.domain.vo.ProductId;
import com.capitole.visibility.domain.vo.ProductSequence;

import java.util.Set;

public record Product(ProductId productId,
                      ProductSequence productSequence,
                      Set<Size> size)  implements Comparable<Product>{

    public static Product buildByProductBasicInformationAndSize(ProductBasicInformation productBasicInformation,
                                                                 Set<Size> size){
        return new Product(
                productBasicInformation.id(),
                productBasicInformation.productSequence(),
                size
        );
    }

    public boolean specialSizeCondition(){
        return size.stream()
                   .noneMatch(s -> s.isSpecialSize().value()) || stockOfNoSpecialSizeProduct();
    }

    private boolean stockOfNoSpecialSizeProduct(){
        return size.stream()
                .filter(s -> !s.isSpecialSize().value())
                .anyMatch(Size::inStock);
    }

    @Override
    public int compareTo(Product o) {
        return Integer.compare(this.productSequence.value(), o.productSequence.value());
    }

}
