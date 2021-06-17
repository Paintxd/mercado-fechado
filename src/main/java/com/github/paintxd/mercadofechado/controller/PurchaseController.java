package com.github.paintxd.mercadofechado.controller;

import com.github.paintxd.mercadofechado.controller.dto.PurchaseDto;
import com.github.paintxd.mercadofechado.controller.dto.PurchaseMessageDto;
import com.github.paintxd.mercadofechado.service.RabbitService;
import com.github.paintxd.mercadofechado.model.*;
import com.github.paintxd.mercadofechado.repository.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@ManagedBean(name = "PurchaseMB")
@RequestScoped
public class PurchaseController {
    private PurchaseDto purchaseDto = new PurchaseDto();
    private Map<Long, Long> productIdAmount = new HashMap<>();

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

    public void purchase(Long userId) {
        var user = userRepository.findById(userId).orElseThrow();

        var purchaseStatus = purchaseStatusRepository.save(new PurchaseStatus(ActualPurchaseState.PENDING_PAYMENT, LocalDateTime.now()));

        var purchase = purchaseRepository.save(new Purchase(user, purchaseStatus));

        var products = this.productRepository.findAllById(productIdAmount.keySet());
        List<PurchaseProduct> purchaseProducts = StreamSupport.stream(products.spliterator(), true)
                .map(product -> {
                    var productAmount = productIdAmount.get(product.getId());
                    return new PurchaseProduct(product, productAmount, purchase);
                })
                .collect(Collectors.toList());
        this.purchaseProductRepository.saveAll(purchaseProducts);

        rabbitService.send(new PurchaseMessageDto(purchase.getId(), purchaseStatus.getId(), user.getId()));
    }

    public void addItem(Long productId, Long amount) {
        productIdAmount.put(productId, amount);
    }

    public void removeItem(Long productId) {
        productIdAmount.remove(productId);
    }

    public PurchaseDto getPurchaseDto() {
        return purchaseDto;
    }

    public void setPurchaseDto(PurchaseDto purchaseDto) {
        this.purchaseDto = purchaseDto;
    }
}
