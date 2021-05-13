package com.github.paintxd.mercadofechado.controller.dto;

import java.util.HashMap;
import java.util.Map;

public class PurchaseDto {

    private Long userId;

    private Map<Long, Long> productIdAmount = new HashMap<>();

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Map<Long, Long> getProductIdAmount() {
        return productIdAmount;
    }

    public void setProductIdAmount(Map<Long, Long> productIdAmount) {
        this.productIdAmount = productIdAmount;
    }
}
