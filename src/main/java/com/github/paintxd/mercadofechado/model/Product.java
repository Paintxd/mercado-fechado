package com.github.paintxd.mercadofechado.model;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = 1905122041950251207L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    @Column(length = 2000)
    private String description;

    private Double price;

    private Long stock;

    private Long discountPercentage;

    public Product() {
    }

    public Product(String name, String type, String description, Double price, Long stock, Long discountPercentage) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.discountPercentage = discountPercentage;
    }

    public Long getId() {
        return id;
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

    public void buyProduct(Long purchaseAmount) throws Exception {
        if (purchaseAmount > this.stock)
            throw new Exception();
        this.stock -= stock;
    }

    public Long getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Long discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Double getPriceDiscount() {
        return discountPercentage > 0
                ? price - (price * discountPercentage / 100)
                : price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(type, product.type) && Objects.equals(description, product.description) && Objects.equals(price, product.price) && Objects.equals(stock, product.stock) && Objects.equals(discountPercentage, product.discountPercentage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, description, price, stock, discountPercentage);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", discountPercentage=" + discountPercentage +
                '}';
    }
}
