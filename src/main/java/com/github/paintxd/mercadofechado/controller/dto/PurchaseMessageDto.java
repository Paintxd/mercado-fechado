package com.github.paintxd.mercadofechado.controller.dto;

public class PurchaseMessageDto {

    private Long purchaseId;

    private Long purchaseStatudId;

    private Long buyerId;

    public PurchaseMessageDto(Long purchaseId, Long purchaseStatudId, Long buyerId) {
        this.purchaseId = purchaseId;
        this.purchaseStatudId = purchaseStatudId;
        this.buyerId = buyerId;
    }

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Long getPurchaseStatudId() {
        return purchaseStatudId;
    }

    public void setPurchaseStatudId(Long purchaseStatudId) {
        this.purchaseStatudId = purchaseStatudId;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }
}
