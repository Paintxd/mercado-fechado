package com.github.paintxd.mercadofechado.controller.dto;

import com.github.paintxd.mercadofechado.model.Product;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class ProductDto {
    @NotEmpty(message = "Obrigatorio")
    private String name;
    @NotEmpty(message = "Obrigatorio")
    private String type;
    @NotEmpty(message = "Obrigatorio")
    private String description;
    @Min(value = 0)
    private Double price;
    @Min(value = 0)
    private Long stock;
    @Min(value = 0)
    private Long discountPercentage;

    public ProductDto() { }

    public ProductDto(Product product) {
        this.name = product.getName();
        this.type = product.getType();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.stock = product.getStock();
        this.discountPercentage = product.getDiscountPercentage();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Long getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Long discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Product parse() {
        return new Product(name, type, description, price, stock, discountPercentage);
    }

    public Product update(Product product) {
        product.setName(name);
        product.setDescription(description);
        product.setDiscountPercentage(discountPercentage);
        product.setPrice(price);
        product.setStock(stock);
        product.setType(type);
        
        return product;
    }
}
