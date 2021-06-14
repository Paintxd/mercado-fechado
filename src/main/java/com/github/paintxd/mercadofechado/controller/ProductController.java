package com.github.paintxd.mercadofechado.controller;

import com.github.paintxd.mercadofechado.controller.dto.ProductDto;
import com.github.paintxd.mercadofechado.model.Product;
import com.github.paintxd.mercadofechado.repository.ProductRepository;

public class ProductController {
    private Iterable<Product> productList;
    private Product product = new Product();
    private ProductDto productDto = new ProductDto();
    ProductRepository productRepository;

    public ProductController() {}

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void saveProduct() {
        productRepository.save(productDto.parse());
    }

    public void updateProduct(Long id) throws Exception {
        var product = productRepository.findById(id).orElseThrow();
        productRepository.save(productDto.update(product));
    }

    public void deleteProduct(Long id) {
        var product = productRepository.findById(id).orElseThrow();
        productRepository.delete(product);
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

    public ProductDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductDto productDto) {
        this.productDto = productDto;
    }
}
