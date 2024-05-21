package org.bsuir.jwtproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.bsuir.jwtproject.model.Product;
import org.bsuir.jwtproject.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Продукты")
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping
    @Operation(summary = "Получение продукта")
    public List<Product> example() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение продукта по id")
    public Product exampleById(@PathVariable Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Добавление продукта")
    public Product addExample(@RequestBody Product product) {
        return productRepository.save(product);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Удаление примера")
    public void deleteExample(@PathVariable Long id) {
        productRepository.deleteById(id);
    }
}
