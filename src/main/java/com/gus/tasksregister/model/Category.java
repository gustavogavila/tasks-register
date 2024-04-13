package com.gus.tasksregister.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CATEGORY_GENERATOR")
    @SequenceGenerator(name = "SQ_CATEGORY_GENERATOR", allocationSize = 1, sequenceName = "sq_category")
    private Long id;
    private String name;
}
