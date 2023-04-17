package org.visibility.domain.agregate;

import com.capitole.visibility.vo.SizeId;
import com.capitole.visibility.vo.StockQuantity;

public record Stock(SizeId id,
                    StockQuantity stockQuantity) {

}
