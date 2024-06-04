package com.capitole.visibility.infra.adapters.out;

import com.capitole.visibility.domain.agregate.SizeBasicInformation;
import com.capitole.visibility.domain.exception.SizeException;
import com.capitole.visibility.domain.ports.SizeRepository;
import com.capitole.visibility.domain.vo.IsSpecialSize;
import com.capitole.visibility.domain.vo.ProductId;
import com.capitole.visibility.domain.vo.SizeBackSoon;
import com.capitole.visibility.domain.vo.SizeId;
import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import reactor.core.publisher.Mono;

public class SizeRepositoryCSV implements SizeRepository {

    private final Logger LOGGER = Logger.getLogger(SizeRepositoryCSV.class.getName());

    @Override
    public CompletableFuture<Set<SizeBasicInformation>> findAll() {
        return Mono.using(() -> new CSVReader(new FileReader(
                                  new File(this.getClass().getClassLoader().getResource("data/size.csv").toURI()))),
                          this::processAndCreateProduct,
                          file -> {
                              try {
                                  file.close();
                              } catch (IOException e) {
                                  throw new RuntimeException(e);
                              }
                          })
                   .onErrorResume(error -> Mono.error(SizeException.sizeRepositoryError()))
                   .toFuture();

    }

    public Mono<Set<SizeBasicInformation>> processAndCreateProduct(CSVReader csvReader) {
        return Mono.fromCallable(csvReader::readAll)
                   .doOnError(error -> LOGGER.info(String.format("Error reading size file: %s", error.getMessage())))
                   .map(list -> list.stream()
                                    .map(line -> new SizeBasicInformation(
                                            new SizeId(Integer.parseInt(line[0].strip())),
                                            new ProductId(Integer.parseInt(line[1].strip())),
                                            new SizeBackSoon(Boolean.parseBoolean(line[2].strip())),
                                            new IsSpecialSize(
                                                    Boolean.parseBoolean(line[3].strip())))
                                        )
                                    .collect(Collectors.toSet())
                       );
    }
}
