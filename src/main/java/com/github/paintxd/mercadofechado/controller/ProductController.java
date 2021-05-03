package com.github.paintxd.mercadofechado.controller;

import com.github.paintxd.mercadofechado.controller.dto.ProductDto;
import com.github.paintxd.mercadofechado.model.Product;
import com.github.paintxd.mercadofechado.repository.ProductRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class ProductController {
    private static final String REDIRECT_HOME = "redirect:/product";
    private static final String LOAD_HOME = "productCrud";
    private static final String FORM_ACTION = "action";
    ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping()
    public String productsHome(Model model) {
        model.addAttribute("productList", productRepository.findAll());
        model.addAttribute("product", new ProductDto());
        model.addAttribute(FORM_ACTION, "save");

        return LOAD_HOME;
    }

    @GetMapping("/id/{id}")
    public String getProductById(@PathVariable("id") Long id, @Param("action") String action, Model model) {
        var product = productRepository.findById(id).orElse(new Product());

        String formAction = action != null ? "update/id/" + id : "save";

        model.addAttribute("productList", productRepository.findAll());
        model.addAttribute("product", new ProductDto(product));
        model.addAttribute(FORM_ACTION, formAction);

        return LOAD_HOME;
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") @Validated ProductDto productDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("product", productDto);
            model.addAttribute(FORM_ACTION, "save");
            return LOAD_HOME;
        }
        productRepository.save(productDto.parse());

        return REDIRECT_HOME;
    }

    @PostMapping("/update/id/{id}")
    public String updateProduct(@PathVariable("id") Long id, @ModelAttribute("product") @Validated ProductDto productDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("product", productDto);
            model.addAttribute(FORM_ACTION, "/update/id/" + id);
            return LOAD_HOME;
        }
        var product = productRepository.findById(id).orElseThrow();
        productRepository.save(productDto.update(product));

        return REDIRECT_HOME;
    }

    @GetMapping("/delete/id/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        var product = productRepository.findById(id).orElseThrow();
        productRepository.delete(product);

        return REDIRECT_HOME;
    }
}
