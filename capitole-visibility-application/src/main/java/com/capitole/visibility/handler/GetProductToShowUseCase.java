package com.capitole.visibility.handler;

import com.capitole.visibility.domain.ports.ProductRepository;
import com.capitole.visibility.domain.ports.SizeRepository;
import com.capitole.visibility.domain.ports.StockRepository;

import java.util.logging.Logger;

public class GetProductToShowUseCase {

    private final ProductRepository productRepository;
    private final SizeRepository sizeRepository;
    private final StockRepository stockRepository;

    public GetProductToShowUseCase(final ProductRepository productRepository,
                                   final SizeRepository sizeRepository,
                                   final StockRepository stockRepository){

        this.productRepository = productRepository;
        this.sizeRepository = sizeRepository;
        this.stockRepository = stockRepository;
    }

    Logger LOGGER = Logger.getLogger(GetProductToShowUseCase.class.getName());

}
