package com.gus.tasksregister.services;

import com.gus.tasksregister.model.Category;
import com.gus.tasksregister.model.Task;
import com.gus.tasksregister.model.TaskRequest;
import com.gus.tasksregister.register_new_tasks.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public void createTask(TaskRequest taskRequest) {
        Set<Long> categoriesIds = taskRequest.getCategoriesIds();
        if (isNull(categoriesIds)) {
            taskRepository.save(new Task(null, taskRequest.getDescription(), taskRequest.getTitle(), List.of()));
            return;
        }
        List<Category> categories = categoriesIds.stream().map(id -> {
            Category category = new Category();
            category.setId(id);
            return category;
        }).toList();
        taskRepository.save(new Task(null, taskRequest.getDescription(), taskRequest.getTitle(), categories));
    }
}
