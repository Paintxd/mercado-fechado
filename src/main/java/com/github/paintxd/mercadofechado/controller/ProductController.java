package com.github.paintxd.mercadofechado.controller;

import com.github.paintxd.mercadofechado.model.Product;
import com.github.paintxd.mercadofechado.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;

@Named(value = "productB")
@SessionScoped
public class ProductController {
    private Iterable<Product> productList;
    private Product product = new Product();

    @Autowired
    private ProductRepository productRepository;

    public ProductController() {}

    public void saveProduct() {
        productRepository.save(product);
        product = new Product();
    }

    public void updateProduct(Long id) {
        product = productRepository.findById(id).orElse(new Product());
    }

    public void deleteProduct(Long id) {
        var productDelete = productRepository.findById(id).orElseThrow();
        productRepository.delete(productDelete);
    }

    public void clearForm() {
        product = new Product();
    }

    public Iterable<Product> getProductList() {
        return this.productRepository.findAll();
    }

    public void setProductList(Iterable<Product> productList) {
        this.productList = productList;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
