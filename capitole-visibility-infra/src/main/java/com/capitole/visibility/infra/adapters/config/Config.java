package com.capitole.visibility.infra.adapters.config;

import com.capitole.visibility.application.handler.GetProductToShowUseCase;
import com.capitole.visibility.domain.ports.ProductRepository;
import com.capitole.visibility.domain.ports.SizeRepository;
import com.capitole.visibility.domain.ports.StockRepository;
import com.capitole.visibility.infra.adapters.out.ProductRepositoryCSV;
import com.capitole.visibility.infra.adapters.out.SizeRepositoryCSV;
import com.capitole.visibility.infra.adapters.out.StockRepositoryCSV;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public ProductRepository buildProductRepository(){
        return new ProductRepositoryCSV();
    }

    @Bean
    public StockRepository buildStockRepository(){
        return new StockRepositoryCSV();
    }

    @Bean
    public SizeRepositoryCSV buildSizeRepositoryCSV(){
        return new SizeRepositoryCSV();
    }

    @Bean
    public GetProductToShowUseCase buildGetProductToShowUseCase(final ProductRepository productRepository,
                                                                final SizeRepository sizeRepository,
                                                                final StockRepository stockRepository){
        return new GetProductToShowUseCase(productRepository,sizeRepository,stockRepository);
    }
}
