package com.sample.annotation;

public class Product {
    @JsonField("skuId")
    private String skuId;
    @JsonField("skuDesc")
    private  String skuDesc;
    @JsonField("price")
    private Integer price;


    public Product(String skuId, String skuDesc, Integer price) {
        this.skuId = skuId;
        this.skuDesc = skuDesc;
        this.price = price;
    }

    public String getSkuId() {
        return skuId;
    }

    public String getSkuDesc() {
        return skuDesc;
    }

    public Integer getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "skuId='" + skuId + '\'' +
                ", skuDesc='" + skuDesc + '\'' +
                ", price=" + price +
                '}';
    }
}
