package com.github.paintxd.mercadofechado.controller;

import com.github.paintxd.mercadofechado.controller.dto.PurchaseDto;
import com.github.paintxd.mercadofechado.controller.dto.PurchaseMessageDto;
import com.github.paintxd.mercadofechado.messaging.RabbitService;
import com.github.paintxd.mercadofechado.model.*;
import com.github.paintxd.mercadofechado.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/purchases")
public class PurchaseController {
    private static final String REDIRECT_HOME = "redirect:/product";
    private static final String LOAD_HOME = "purchaseCrud.jsp";
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

    @GetMapping()
    public String purchasesHome(Model model) {
        model.addAttribute("productsList", productRepository.findAll());
        model.addAttribute("purchasesList", purchaseRepository.findAll());
        model.addAttribute("purchase", new PurchaseDto());

        return LOAD_HOME;
    }

    @PostMapping("/purchase/userId/{userId}")
    public String purchase(@PathVariable("userId") Long userId, @ModelAttribute("purchase") @Validated PurchaseDto purchaseDto, Model model) {
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

        return REDIRECT_HOME;
    }
}
