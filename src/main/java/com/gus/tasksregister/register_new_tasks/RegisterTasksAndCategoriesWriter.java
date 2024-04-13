package com.gus.tasksregister.register_new_tasks;

import com.gus.tasksregister.model.TaskRequest;
import com.gus.tasksregister.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
public class RegisterTasksAndCategoriesWriter implements ItemWriter<TaskBatchItem> {

    private final TaskService taskService;

    @Override
    public void write(Chunk<? extends TaskBatchItem> taskBatchItems) throws Exception {
        taskBatchItems.getItems().forEach(item -> {
           TaskRequest taskRequest = itemToTaskRequest(item);
            taskService.createTask(taskRequest);
        });
    }

    private TaskRequest itemToTaskRequest(TaskBatchItem item) {
        Set<Long> categoriesIds = Arrays.stream(item.getCategories_ids().split("-"))
                .filter(ci -> nonNull(ci) && !ci.isBlank())
                .map(Long::parseLong)
                .collect(Collectors.toSet());
        return new TaskRequest(item.getDescription(), item.getTitle(), categoriesIds);
    }
}
