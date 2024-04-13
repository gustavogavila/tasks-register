package com.gus.tasksregister.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TASK_GENERATOR")
    @SequenceGenerator(name = "SQ_TASK_GENERATOR", allocationSize = 1, sequenceName = "sq_task")
    private Long id;
    private String description;
    private String title;
    @ManyToMany
    @JoinTable(name = "task_category",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;
}
