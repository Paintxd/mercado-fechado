package com.github.paintxd.mercadofechado.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "purchases")
public class Purchase implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "buyer_id")
    private User user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "purchase")
    private List<PurchaseProduct> productList = new ArrayList<PurchaseProduct>();

    @OneToOne()
    private PurchaseStatus purchaseStatus;

    private LocalDateTime date;

    private Double price;

    public Purchase() {
    }

    public Purchase(User user, PurchaseStatus purchaseStatus) {
        this.user = user;
        this.purchaseStatus = purchaseStatus;
        this.date = LocalDateTime.now();
        this.price = 0.0;
        productList.forEach(product -> {
            this.price += product.getPrice();
        });
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<PurchaseProduct> getProductList() {
        return productList;
    }

    public void setProductList(List<PurchaseProduct> productList) {
        this.productList = productList;
    }

    public PurchaseStatus getPurchaseStatus() {
        return purchaseStatus;
    }

    public void setPurchaseStatus(PurchaseStatus purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void addProduct(PurchaseProduct product) {
        this.productList.add(product);
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", user=" + user +
                ", productList=" + productList +
                ", purchaseStatus=" + purchaseStatus +
                ", date=" + date +
                ", price=" + price +
                '}';
    }
}
