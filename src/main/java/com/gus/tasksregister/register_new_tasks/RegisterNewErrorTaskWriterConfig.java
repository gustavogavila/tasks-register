package com.gus.tasksregister.register_new_tasks;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.util.UUID;

@Configuration
public class RegisterNewErrorTaskWriterConfig {

    @Bean
    public FlatFileItemWriter<TaskBatchItem> registerNewErrorTaskWriter() {
        String errorsFileName = "report_error_".concat(UUID.randomUUID().toString());
        String path = "report_errors/".concat(errorsFileName).concat(".csv");
        return new FlatFileItemWriterBuilder<TaskBatchItem>()
                .name("registerNewTasksWriter")
                .resource(new FileSystemResource(path))
                .delimited()
                .names("description", "problems")
                .build();
    }
}
