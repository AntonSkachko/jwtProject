package org.bsuir.jwtproject.repository;

import org.bsuir.jwtproject.model.Example;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExampleRepository extends JpaRepository<Example, Long> {
}
