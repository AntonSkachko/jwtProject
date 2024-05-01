package org.bsuir.jwtproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "example")
public class Example {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "example_id_seq")
    @SequenceGenerator(name = "example_id_seq", sequenceName = "example_id_seq", allocationSize = 1)
    private Long id;
    @NotNull
    private String name;
    private String description;
}
