package com.capitole.visibility.domain.ports;

import com.capitole.visibility.domain.agregate.ProductBasicInformation;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface ProductRepository {

    CompletableFuture<Set<ProductBasicInformation>> findAll();
}
