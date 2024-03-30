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
        boolean exists = taskRepository.existsByDescription(item.getDescription());
        if (item.getDescription() == null) {
            taskBatchItem.addProblem("A descrição é obrigatória");
        }
        if (exists) {
            taskBatchItem.addProblem("A descrição já existe no sistema");
        }
        taskBatchItem.setDescription(item.getDescription());
        return taskBatchItem;
    }
}
