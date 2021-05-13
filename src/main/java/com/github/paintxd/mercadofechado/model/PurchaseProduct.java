package com.github.paintxd.mercadofechado.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "purchase_product")
public class PurchaseProduct implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_purchase")
    private Purchase purchase;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_product")
    private Product product;

    private Long productAmount;

    private Double price;

    public PurchaseProduct() {

    }

    public PurchaseProduct(Product product, Long productAmount, Purchase purchase) {
        this.purchase = purchase;
        this.product = product;
        this.productAmount = productAmount;
        this.price = product.getPrice() * productAmount;
    }

    public Long getId() {
        return id;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(Long productAmount) {
        this.productAmount = productAmount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "PurchaseProduct{" +
                ", product=" + product +
                ", productAmount=" + productAmount +
                ", purchase=" + purchase +
                ", price=" + price +
                '}';
    }
}
