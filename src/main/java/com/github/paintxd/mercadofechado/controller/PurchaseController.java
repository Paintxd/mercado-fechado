package com.github.paintxd.mercadofechado.controller;

import com.github.paintxd.mercadofechado.service.RabbitService;
import com.github.paintxd.mercadofechado.model.*;
import com.github.paintxd.mercadofechado.repository.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named(value = "purchaseB")
@RequestScoped
public class PurchaseController implements Serializable {
    private Iterable<Purchase> purchases;
    private Map<Product, Long> productAmount = new HashMap<>();

    private Long amount = 0L;

    private String cartProducts = "Carrinho: " + productAmount.size() + " Itens";

    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private PurchaseProductRepository purchaseProductRepository;
    @Autowired
    private PurchaseStatusRepository purchaseStatusRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RabbitService rabbitService;

    public PurchaseController() {
    }

    public String purchase(Long userId) {
        var user = userRepository.findById(userId).orElseThrow();

        var purchaseStatus = purchaseStatusRepository.save(new PurchaseStatus(ActualPurchaseState.PENDING_PAYMENT, LocalDateTime.now()));

        var purchase = new Purchase(user, purchaseStatus);

        var products = productAmount.keySet();
        List<PurchaseProduct> purchaseProducts = products.parallelStream()
                .map(product -> {
                    var buyAmount = this.productAmount.get(product);
                    try {
                        product.buyProduct(buyAmount);
                        productRepository.save(product);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    var purchaseProduct = new PurchaseProduct(product, buyAmount, purchase);

                    purchase.setPrice(purchaseProduct.getPrice());
                    return purchaseProduct;
                })
                .collect(Collectors.toList());
        purchaseRepository.save(purchase);
        this.purchaseProductRepository.saveAll(purchaseProducts);

        productAmount.clear();
        cartProducts = "Carrinho: " + productAmount.size() + " Itens";
        return "/index.xhtml?faces-redirect=true";
//        rabbitService.send(new PurchaseMessageDto(purchase.getId(), purchaseStatus.getId(), user.getId()));
    }

    public void addProductCart(Product product) {
        if (amount < 1) return;

        if (product.getStock() < amount || productAmount.get(product) != null && product.getStock() < (productAmount.get(product) + amount))
            return;

        System.out.println("Product add to cart amount: " + amount);
        productAmount.put(product, amount);
        amount = 0L;
        cartProducts = "Carrinho: " + productAmount.size() + " Itens";
    }

    public Map<Product, Long> getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(Map<Product, Long> productAmount) {
        this.productAmount = productAmount;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(String cartProducts) {
        this.cartProducts = cartProducts;
    }

    public Iterable<Purchase> getPurchases() {
        return purchaseRepository.findAll();
    }

    public void setPurchases(Iterable<Purchase> purchases) {
        this.purchases = purchases;
    }
}
