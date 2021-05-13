package com.github.paintxd.mercadofechado.repository;

import com.github.paintxd.mercadofechado.model.PurchaseStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseStatusRepository extends CrudRepository<PurchaseStatus, Long> {
}
