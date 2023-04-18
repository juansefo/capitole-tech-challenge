package com.capitole.visibility.application.handler;

import com.capitole.visibility.domain.agregate.ProductBasicInformation;
import com.capitole.visibility.domain.agregate.SizeBasicInformation;
import com.capitole.visibility.domain.agregate.Stock;
import com.capitole.visibility.domain.entity.Product;
import com.capitole.visibility.domain.entity.Size;
import com.capitole.visibility.domain.ports.ProductRepository;
import com.capitole.visibility.domain.ports.SizeRepository;
import com.capitole.visibility.domain.ports.StockRepository;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.function.Tuple3;

public class GetProductToShowUseCase {

    private final Logger LOGGER = Logger.getLogger(GetProductToShowUseCase.class.getName());

    private final ProductRepository productRepository;
    private final SizeRepository sizeRepository;
    private final StockRepository stockRepository;

    public GetProductToShowUseCase(final ProductRepository productRepository,
                                   final SizeRepository sizeRepository,
                                   final StockRepository stockRepository) {

        this.productRepository = productRepository;
        this.sizeRepository = sizeRepository;
        this.stockRepository = stockRepository;
    }

    public Mono<Set<Product>> execute() {
        return Mono.zip(getProducts(), getSize(), getStock())
                   .map(this::buildProductEntity)
                   .map(products -> products.filter(
                           Product::specialSizeCondition).collect(
                           Collectors.toSet()));
    }

    private Stream<Product> buildProductEntity(
            Tuple3<Set<ProductBasicInformation>, Set<SizeBasicInformation>, List<Stock>> tuple) {
        var size = buildSize(tuple.getT2(), tuple.getT3());
        return tuple.getT1()
                    .stream()
                    .map(product -> Product.buildByProductBasicInformationAndSize(product, size));
    }

    private Set<Size> buildSize(Set<SizeBasicInformation> sizeSet, List<Stock> stockList) {
        return sizeSet.stream()
                      .map(sizeBasicInformation ->
                                   Size.buildSize(sizeBasicInformation,
                                                  Stock.accumulateOrDefault(
                                                          sizeBasicInformation.id(),
                                                          stockList)))
                      .collect(Collectors.toSet());
    }

    private Mono<Set<ProductBasicInformation>> getProducts() {
        return Mono.fromFuture(productRepository.findAll()).publishOn(Schedulers.boundedElastic())
                .doOnSuccess(l-> LOGGER.info(String.format("%s products were found", l.size())))
                .doOnError(error -> LOGGER.info(String.format("Error getting products: %s",
                                                              error.getMessage())));
    }

    private Mono<Set<SizeBasicInformation>> getSize() {
        return Mono.fromFuture(sizeRepository.findAll()).publishOn(Schedulers.boundedElastic())
                   .doOnSuccess(l-> LOGGER.info(String.format("%s size records were found", l.size())))
                   .doOnError(error -> LOGGER.info(String.format("Error getting size records: %s",
                                                                 error.getMessage())));

    }

    private Mono<List<Stock>> getStock() {
        return Mono.fromFuture(stockRepository.findAll()).publishOn(Schedulers.boundedElastic())
                   .doOnSuccess(l-> LOGGER.info(String.format("%s stock records were found", l.size())))
                   .doOnError(error -> LOGGER.info(String.format("Error getting stock records: %s",
                                                                 error.getMessage())));
    }
}
