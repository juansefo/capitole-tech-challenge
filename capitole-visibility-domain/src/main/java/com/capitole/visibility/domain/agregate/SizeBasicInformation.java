package com.capitole.visibility.domain.agregate;

import com.capitole.visibility.domain.vo.ProductId;
import com.capitole.visibility.domain.vo.SizeBackSoon;
import com.capitole.visibility.domain.vo.SizeId;
import com.capitole.visibility.domain.vo.IsSpecialSize;

public record SizeBasicInformation(SizeId id,
                                   ProductId productId,
                                   SizeBackSoon sizeBackSoon,
                                   IsSpecialSize isSpecialSize) {

}
