package com.gus.tasksregister.register_new_tasks;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.*;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterNewTasksReader implements ItemReader<TaskBatchItem> {

    private final FlatFileItemReader<TaskBatchItem> reader;

    public RegisterNewTasksReader() {
        reader = new FlatFileItemReaderBuilder<TaskBatchItem>()
                .name("registerNewTasksReader")
                .delimited()
                .names("description", "title", "categories_ids")
                .linesToSkip(1)
                .targetType(TaskBatchItem.class)
                .build();
    }

    public void setResource(Resource resource) {
        reader.setResource(resource);
        reader.open(new ExecutionContext());
    }

    @Override
    public TaskBatchItem read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        try {
            return reader.read();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao ler o CSV", e);
        }
    }
}
