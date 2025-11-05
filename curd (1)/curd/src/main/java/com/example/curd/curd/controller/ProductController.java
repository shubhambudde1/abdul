package com.example.curd.curd.controller;

import com.example.curd.curd.model.Product;
import com.example.curd.curd.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public String viewHomePage(Model model) {
        model.addAttribute("products", service.getAll());
        return "index";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        model.addAttribute("product", new Product());
        return "new_product";
    }

    @PostMapping
    public String save(@ModelAttribute("product") Product product) {
        service.save(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", service.getById(id));
        return "edit_product";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Product product) {
        product.setId(id);
        service.save(product);
        return "redirect:/products";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/products";
    }
}

