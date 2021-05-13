package com.github.paintxd.mercadofechado.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "purchase_status")
public class PurchaseStatus implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ActualPurchaseState actualState;

    private LocalDateTime statusUpdate;

    public PurchaseStatus() {

    }

    public PurchaseStatus(ActualPurchaseState actualState, LocalDateTime statusUpdate) {
        this.actualState = actualState;
        this.statusUpdate = statusUpdate;
    }

    public Long getId() {
        return id;
    }

    public ActualPurchaseState getActualState() {
        return actualState;
    }

    public void setActualState(ActualPurchaseState actualState) {
        this.actualState = actualState;
    }

    public LocalDateTime getStatusUpdate() {
        return statusUpdate;
    }

    public void setStatusUpdate(LocalDateTime statusUpdate) {
        this.statusUpdate = statusUpdate;
    }

    @Override
    public String toString() {
        return "PurchaseStatus{" +
                "id=" + id +
                ", actualState=" + actualState +
                ", statusUpdate=" + statusUpdate +
                '}';
    }
}
