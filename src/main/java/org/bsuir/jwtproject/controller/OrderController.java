package org.bsuir.jwtproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.bsuir.jwtproject.model.Order;
import org.bsuir.jwtproject.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Заказы")
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderRepository orderRepository;

    @GetMapping
    @Operation(description = "Получение заказов")
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(description = "Получение заказа по id")
    public Order getOrderById(@PathVariable Long id) {
        return orderRepository.getReferenceById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Создание заказа")
    public Order createOrder(@RequestBody Order order) {
        return orderRepository.save(order);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(description = "Удаление заказа")
    public void deleteOrder(@PathVariable Long id) {
        orderRepository.deleteById(id);
    }
}
