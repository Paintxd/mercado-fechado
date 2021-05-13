package com.github.paintxd.mercadofechado.repository;

import com.github.paintxd.mercadofechado.model.Purchase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, Long> {
}
