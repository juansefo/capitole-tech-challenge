package com.capitole.visibility.agregate;

import com.capitole.visibility.vo.SizeId;
import com.capitole.visibility.vo.StockQuantity;

public record Stock(SizeId id,
                    StockQuantity stockQuantity) {

}
