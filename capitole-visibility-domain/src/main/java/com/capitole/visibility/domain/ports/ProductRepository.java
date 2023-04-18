package com.capitole.visibility.domain.ports;

import com.capitole.visibility.domain.agregate.ProductBasicInformation;
import java.util.List;

public interface ProductRepository {

    List<ProductBasicInformation> findAllProduct();
}
