package com.github.paintxd.mercadofechado.controller;

import com.github.paintxd.mercadofechado.controller.dto.PurchaseDto;
import com.github.paintxd.mercadofechado.controller.dto.PurchaseMessageDto;
import com.github.paintxd.mercadofechado.messaging.RabbitService;
import com.github.paintxd.mercadofechado.model.*;
import com.github.paintxd.mercadofechado.repository.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@ManagedBean(name = "PurchaseMB")
@RequestScoped
public class PurchaseController {
    private PurchaseDto purchaseDto = new PurchaseDto();

    PurchaseRepository purchaseRepository;
    PurchaseProductRepository purchaseProductRepository;
    PurchaseStatusRepository purchaseStatusRepository;
    ProductRepository productRepository;
    UserRepository userRepository;
    RabbitService rabbitService;

    public PurchaseController(PurchaseRepository purchaseRepository, PurchaseProductRepository purchaseProductRepository, PurchaseStatusRepository purchaseStatusRepository, ProductRepository productRepository, UserRepository userRepository, RabbitService rabbitService) {
        this.purchaseRepository = purchaseRepository;
        this.purchaseProductRepository = purchaseProductRepository;
        this.purchaseStatusRepository = purchaseStatusRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.rabbitService = rabbitService;
    }

    public void purchase(Long userId) {
        var user = userRepository.findById(userId).orElseThrow();

        var purchaseStatus = purchaseStatusRepository.save(new PurchaseStatus(ActualPurchaseState.PENDING_PAYMENT, LocalDateTime.now()));

        var purchase = purchaseRepository.save(new Purchase(user, purchaseStatus));

        var products = this.productRepository.findAllById(purchaseDto.getProductIdAmount().keySet());
        List<PurchaseProduct> purchaseProducts = StreamSupport.stream(products.spliterator(), true)
                .map(product -> {
                    var productAmount = purchaseDto.getProductIdAmount().get(product.getId());
                    return new PurchaseProduct(product, productAmount, purchase);
                })
                .collect(Collectors.toList());
        this.purchaseProductRepository.saveAll(purchaseProducts);

        rabbitService.send(new PurchaseMessageDto(purchase.getId(), purchaseStatus.getId(), user.getId()));
    }

    public void addItem(Long productId, Long amount) {
        purchaseDto.getProductIdAmount().put(productId, amount);
    }

    public void removeItem(Long productId) {
        purchaseDto.getProductIdAmount().remove(productId);
    }

    public PurchaseDto getPurchaseDto() {
        return purchaseDto;
    }

    public void setPurchaseDto(PurchaseDto purchaseDto) {
        this.purchaseDto = purchaseDto;
    }
}
