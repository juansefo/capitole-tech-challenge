package org.visibility.domain.entity;

import com.capitole.visibility.agregate.Stock;
import com.capitole.visibility.vo.SizeBackSoon;
import com.capitole.visibility.vo.SizeId;
import com.capitole.visibility.vo.SizeSpecial;

public record Size(SizeId id,
                   SizeBackSoon sizeBackSoon,
                   SizeSpecial sizeSpecial,
                   Stock stock) {

}
