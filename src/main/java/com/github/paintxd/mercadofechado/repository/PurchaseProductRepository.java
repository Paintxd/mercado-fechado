package com.github.paintxd.mercadofechado.repository;

import com.github.paintxd.mercadofechado.model.PurchaseProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseProductRepository extends CrudRepository<PurchaseProduct, Long> {
}
