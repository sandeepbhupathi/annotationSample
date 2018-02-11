package com.sample.annotation;

public class AnnotationDemo {
    public static void main(String[] args) throws JsonSerializeException {
        Product product = new Product("123456", "Test SKu for using", 150);
        JsonSerializer serializer = new JsonSerializer();
        System.out.println(serializer.serialize(product));
    }
}
