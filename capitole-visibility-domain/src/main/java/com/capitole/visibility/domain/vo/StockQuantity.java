package com.capitole.visibility.domain.vo;

public record StockQuantity(int value) {

    public StockQuantity add(StockQuantity stockQuantity){
        return new StockQuantity(this.value + stockQuantity.value);
    }

    public static StockQuantity getDefault(){
        return new StockQuantity(0);
    }
}
