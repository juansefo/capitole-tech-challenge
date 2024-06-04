package com.capitole.visibility.domain.agregate;

import com.capitole.visibility.domain.vo.ProductId;
import com.capitole.visibility.domain.vo.ProductSequence;

public record ProductBasicInformation(ProductId id,
                                    ProductSequence productSequence) {

}
