package com.capitole.visibility.infra.adapters.in.controller;

import com.capitole.visibility.application.handler.GetProductToShowUseCase;
import com.capitole.visibility.infra.adapters.in.dto.ProductResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/product")
public class ProductsController {

    private final GetProductToShowUseCase getProductToShowUseCase;

    public ProductsController(final GetProductToShowUseCase getProductToShowUseCase) {
        this.getProductToShowUseCase = getProductToShowUseCase;
    }

    @GetMapping
    public Mono<ProductResponse> getEmployeeById() {
        return getProductToShowUseCase.execute()
                .map(products-> new ProductResponse(products.stream().map(p -> p.productId().value()).toList()));
    }
}
