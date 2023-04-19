package com.capitole.visibility.application.util;

import com.capitole.visibility.domain.agregate.ProductBasicInformation;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Set;

public class ProductInformationFactory {

    public static Set<ProductBasicInformation> buildProductBasicInformation(final ObjectMapper objectMapper){
        try {
            URL url = SizeInformationFactory.class.getClassLoader().getResource("data/Product.json");
            File file = new File(url.toURI());
            return objectMapper.readValue(file, new TypeReference<>(){});
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
