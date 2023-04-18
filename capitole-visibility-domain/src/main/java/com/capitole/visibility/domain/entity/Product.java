package com.capitole.visibility.domain.entity;

import com.capitole.visibility.domain.vo.ProductId;
import com.capitole.visibility.domain.vo.ProductSequence;

import java.util.Set;
import java.util.stream.Stream;

public record Product(ProductId productId,
                      ProductSequence productSequence,
                      Set<Size> size)  implements Comparable<Product>{

    public boolean specialSize(){
        return size.stream()
                   .anyMatch(s -> s.sizeSpecial().value());
    }

    public boolean stockOfNoSpecialSizeProduct(){
        return size.stream()
                .filter(s -> !s.sizeSpecial().value())
                .anyMatch(Size::inStock);
    }

    private boolean inStock(){
        return size.stream()
                   .anyMatch(Size::inStock);
    }
    @Override
    public int compareTo(Product o) {
        return Integer.compare(this.productSequence.value(), o.productSequence.value());
    }

}
