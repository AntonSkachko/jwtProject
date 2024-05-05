package org.bsuir.jwtproject.repository;

import org.bsuir.jwtproject.model.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExampleRepository extends JpaRepository<Example, Long> {
}
