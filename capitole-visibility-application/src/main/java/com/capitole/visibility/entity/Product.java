package com.capitole.visibility.entity;

import com.capitole.visibility.vo.ProductId;
import com.capitole.visibility.vo.ProductSequence;

import java.util.Set;

public record Product(ProductId productId,
                      ProductSequence productSequence,
                      Set<Size> size)  implements Comparable<Product>{

    @Override
    public int compareTo(Product o) {
        return Integer.compare(this.productSequence.value(), o.productSequence.value());
    }
}
