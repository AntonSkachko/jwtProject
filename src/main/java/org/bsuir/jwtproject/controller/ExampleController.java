package org.bsuir.jwtproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.bsuir.jwtproject.model.Example;
import org.bsuir.jwtproject.repository.ExampleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/example")
@RequiredArgsConstructor
@Tag(name = "Пример")
public class ExampleController {

    private final ExampleRepository exampleRepository;

    @GetMapping
    @Operation(summary = "Получение примера")
    public List<Example> example() {
        return exampleRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение прмера по id")
    public Example exampleById(@PathVariable Long id) {
        return exampleRepository.findById(id).orElse(null);
    }

    @PostMapping
    @Operation(summary = "Добавление в пример")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public Example addExample(@RequestBody Example example) {
        return exampleRepository.save(example);
    }

    @DeleteMapping
    @Operation(summary = "Удаление примера")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteExample(Long id) {
        exampleRepository.deleteById(id);
    }
}