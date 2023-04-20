package com.capitole.visibility.infra.adapters.out;

import com.capitole.visibility.domain.agregate.StockBasicInformation;
import com.capitole.visibility.domain.exception.StockException;
import com.capitole.visibility.domain.ports.StockRepository;
import com.capitole.visibility.domain.vo.SizeId;
import com.capitole.visibility.domain.vo.StockQuantity;
import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

import reactor.core.publisher.Mono;

public class StockRepositoryCSV implements StockRepository {

    private final Logger LOGGER = Logger.getLogger(StockRepositoryCSV.class.getName());

    @Override
    public CompletableFuture<List<StockBasicInformation>> findAll() {
        return Mono.using(() -> new CSVReader(new FileReader(
                                  new File(
                                          this.getClass().getClassLoader().getResource("data/stock.csv").toURI()))),
                          this::processAndCreateProduct,
                          file -> {
                              try {
                                  file.close();
                              } catch (IOException e) {
                                  throw new RuntimeException(e);
                              }
                          })
                   .onErrorResume(error -> Mono.error(StockException.stockRepositoryError()))
                   .toFuture();

    }

    public Mono<List<StockBasicInformation>> processAndCreateProduct(CSVReader csvReader) {
        return Mono.fromCallable(csvReader::readAll)
                   .doOnError(error -> LOGGER.info(String.format("Error reading stock file: %s", error.getMessage())))
                   .map(list -> list.stream()
                                    .map(line -> new StockBasicInformation(
                                            new SizeId(Integer.parseInt(line[0].strip())),
                                            new StockQuantity(Integer.parseInt(line[1].strip()))))
                                    .toList()
                       );
    }
}
