package com.capitole.visibility.agregate;

import com.capitole.visibility.vo.SizeBackSoon;
import com.capitole.visibility.vo.SizeId;
import com.capitole.visibility.vo.SizeSpecial;

public record Size(SizeId id,
                   SizeBackSoon sizeBackSoon,
                   SizeSpecial sizeSpecial) {

}
