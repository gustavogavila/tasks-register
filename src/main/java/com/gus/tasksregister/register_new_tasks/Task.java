package com.gus.tasksregister.register_new_tasks;

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
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TASK_GENERATOR")
    @SequenceGenerator(name = "SQ_TASK_GENERATOR", allocationSize = 1, sequenceName = "sq_task")
    private Long id;
    private String description;
}
