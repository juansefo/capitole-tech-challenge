package com.capitole.visibility.domain.entity;

import com.capitole.visibility.domain.agregate.Stock;
import com.capitole.visibility.domain.vo.SizeBackSoon;
import com.capitole.visibility.domain.vo.SizeId;
import com.capitole.visibility.domain.vo.SizeSpecial;

public record Size(SizeId id,
                   SizeBackSoon sizeBackSoon,
                   SizeSpecial sizeSpecial,
                   Stock stock) {

    public boolean inStock(){
        return sizeBackSoon().value() || stock().inStock();
    }

}
