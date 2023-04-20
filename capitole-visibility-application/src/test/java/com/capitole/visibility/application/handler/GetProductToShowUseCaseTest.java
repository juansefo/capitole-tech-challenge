package com.capitole.visibility.application.handler;

import com.capitole.visibility.application.util.ProductInformationFactory;
import com.capitole.visibility.application.util.SizeInformationFactory;
import com.capitole.visibility.application.util.StockInformationFactory;
import com.capitole.visibility.domain.entity.Product;
import com.capitole.visibility.domain.exception.ProductException;
import com.capitole.visibility.domain.exception.SizeException;
import com.capitole.visibility.domain.exception.StockException;
import com.capitole.visibility.domain.ports.ProductRepository;
import com.capitole.visibility.domain.ports.SizeRepository;
import com.capitole.visibility.domain.ports.StockRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import reactor.test.StepVerifier;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GetProductToShowUseCaseTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private SizeRepository sizeRepository;
    @Mock
    private StockRepository stockRepository;

    private ObjectMapper objectMapper;

    private GetProductToShowUseCase target;

    @BeforeAll
    public void setup() {
        objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        MockitoAnnotations.openMocks(this);
        target = new GetProductToShowUseCase(productRepository, sizeRepository, stockRepository);
    }

    @Test
    public void productsNotFound(){
        Mockito.when(productRepository.findAll())
               .thenReturn(CompletableFuture.completedFuture(Set.of()));

        Mockito.when(sizeRepository.findAll())
               .thenReturn(CompletableFuture.completedFuture(
                       SizeInformationFactory.buildSizeBasicInformation(objectMapper)));

        Mockito.when(stockRepository.findAll())
               .thenReturn(CompletableFuture.completedFuture(StockInformationFactory.buildStockBasicInformation(objectMapper)));

        var response = target.execute();

        StepVerifier.create(response).expectError(ProductException.class).verify();

    }

    @Test
    public void sizeNotFound(){
        Mockito.when(productRepository.findAll())
               .thenReturn(CompletableFuture.completedFuture(
                       ProductInformationFactory.buildProductBasicInformation(objectMapper)));

        Mockito.when(sizeRepository.findAll())
               .thenReturn(CompletableFuture.completedFuture(Set.of()));

        Mockito.when(stockRepository.findAll())
               .thenReturn(CompletableFuture.completedFuture(StockInformationFactory.buildStockBasicInformation(objectMapper)));

        var response = target.execute();

        StepVerifier.create(response).expectError(SizeException.class).verify();

    }


    @Test
    public void stockNotFound(){
        Mockito.when(productRepository.findAll())
               .thenReturn(CompletableFuture.completedFuture(
                       ProductInformationFactory.buildProductBasicInformation(objectMapper)));

        Mockito.when(sizeRepository.findAll())
               .thenReturn(CompletableFuture.completedFuture(
                       SizeInformationFactory.buildSizeBasicInformation(objectMapper)));

        Mockito.when(stockRepository.findAll())
               .thenReturn(CompletableFuture.completedFuture(List.of()));

        var response = target.execute();

        StepVerifier.create(response).expectError(StockException.class).verify();

    }

    @Test
    public void foundAllInformation(){
        Mockito.when(productRepository.findAll())
               .thenReturn(CompletableFuture.completedFuture(
                       ProductInformationFactory.buildProductBasicInformation(objectMapper)));

        Mockito.when(sizeRepository.findAll())
               .thenReturn(CompletableFuture.completedFuture(
                       SizeInformationFactory.buildSizeBasicInformation(objectMapper)));

        Mockito.when(stockRepository.findAll())
               .thenReturn(CompletableFuture.completedFuture(StockInformationFactory.buildStockBasicInformation(objectMapper)));

        var response = target.execute();

        StepVerifier.create(response)
                    .expectNextMatches(GetProductToShowUseCaseTest::extracted)
                    .verifyComplete();

    }

    private static boolean extracted(Set<Product> set) {
        var iterator =set.iterator();
        return set.size() == 3 && iterator.next().productId().value() == 5
                && iterator.next().productId().value() == 1
                && iterator.next().productId().value() == 3 ;
    }
}
