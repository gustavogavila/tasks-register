package com.gus.tasksregister.register_new_tasks;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.*;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterNewTasksReader implements ItemReader<Task> {

    private final FlatFileItemReader<Task> reader;

    public RegisterNewTasksReader() {
        reader = new FlatFileItemReaderBuilder<Task>()
                .name("registerNewTasksReader")
                .delimited()
                .names("description")
                .targetType(Task.class)
                .build();
    }

    public void setResource(Resource resource) {
        reader.setResource(resource);
        reader.open(new ExecutionContext());
    }

    @Override
    public Task read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        try {
            return reader.read();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao ler o CSV", e);
        }
    }
}
