package com.capitole.visibility.infra.adapters.out;

import com.capitole.visibility.domain.agregate.ProductBasicInformation;
import com.capitole.visibility.domain.exception.ProductException;
import com.capitole.visibility.domain.ports.ProductRepository;
import com.capitole.visibility.domain.vo.ProductId;
import com.capitole.visibility.domain.vo.ProductSequence;
import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import reactor.core.publisher.Mono;

public class ProductRepositoryCSV implements ProductRepository {

    private final Logger LOGGER = Logger.getLogger(ProductRepositoryCSV.class.getName());

    @Override
    public CompletableFuture<Set<ProductBasicInformation>> findAll() {
        return Mono.using(() -> new CSVReader(new FileReader(
                                  new File(
                                          this.getClass().getClassLoader().getResource("data/product.csv").toURI()))),
                          this::processAndCreateProduct,
                          file -> {
                              try {
                                  file.close();
                              } catch (IOException e) {
                                  throw new RuntimeException(e);
                              }
                          })
                   .onErrorResume(error -> Mono.error(ProductException.productRepositoryError()))
                   .toFuture();

    }

    public Mono<Set<ProductBasicInformation>> processAndCreateProduct(CSVReader csvReader) {
        return Mono.fromCallable(csvReader::readAll)
                   .doOnError(error -> LOGGER.info(String.format("Error reading product file: %s", error.getMessage())))
                   .map(list -> list.stream()
                                    .map(line -> new ProductBasicInformation(
                                            new ProductId(Integer.parseInt(line[0].strip())),
                                            new ProductSequence(Integer.parseInt(line[1].strip()))))
                                    .collect(Collectors.toSet())
                       );
    }
}
