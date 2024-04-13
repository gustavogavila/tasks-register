package com.gus.tasksregister.register_new_tasks;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterNewTasksProcessor implements ItemProcessor<TaskBatchItem, TaskBatchItem> {

    private final TaskRepository taskRepository;

    @Override
    public TaskBatchItem process(TaskBatchItem item) {
        TaskBatchItem taskBatchItem = new TaskBatchItem();
        boolean exists = taskRepository.existsByTitle(item.getTitle());
        if (item.getTitle() == null || item.getTitle().isBlank()) {
            taskBatchItem.addProblem("O título é obrigatório");
        }
        if (item.getDescription() == null || item.getDescription().isBlank()) {
            taskBatchItem.addProblem("A descrição é obrigatória");
        }
        if (exists) {
            taskBatchItem.addProblem("O título já existe no sistema");
        }
        taskBatchItem.setTitle(item.getTitle());
        taskBatchItem.setDescription(item.getDescription());
        taskBatchItem.setCategories_ids(item.getCategories_ids());
        return taskBatchItem;
    }
}
