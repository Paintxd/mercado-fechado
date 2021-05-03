package com.github.paintxd.mercadofechado.repository;

import com.github.paintxd.mercadofechado.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
