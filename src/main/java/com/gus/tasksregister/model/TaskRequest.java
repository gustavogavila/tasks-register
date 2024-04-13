package com.gus.tasksregister.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest {
    private String description;
    private String title;
    private Set<Long> categoriesIds;
}
