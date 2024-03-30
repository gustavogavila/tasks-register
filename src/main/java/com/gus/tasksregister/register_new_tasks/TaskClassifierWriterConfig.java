package com.gus.tasksregister.register_new_tasks;

import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.util.Objects.nonNull;

@Configuration
public class TaskClassifierWriterConfig {

    @Bean
    public ClassifierCompositeItemWriter<TaskBatchItem> taskClassifierWriter(JdbcBatchItemWriter<TaskBatchItem> registerNewTasksWriter,
                                                                             FlatFileItemWriter<TaskBatchItem> registerNewErrorTaskWriter) {

        return new ClassifierCompositeItemWriterBuilder<TaskBatchItem>().classifier(classifiable -> {
                    if (nonNull(classifiable.getProblems())
                            && !classifiable.getProblems().isBlank()
                            && !classifiable.getProblems().isEmpty()) {
                        return registerNewErrorTaskWriter;
                    }
                    return registerNewTasksWriter;
                }).build();
    }
}
