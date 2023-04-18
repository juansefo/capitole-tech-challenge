package com.capitole.visibility.domain.ports;

import com.capitole.visibility.domain.agregate.SizeBasicInformation;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface SizeRepository {

    CompletableFuture<Set<SizeBasicInformation>> findAll();
}
