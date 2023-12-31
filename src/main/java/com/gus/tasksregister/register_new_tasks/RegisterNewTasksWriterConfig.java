package com.gus.tasksregister.register_new_tasks;

import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class RegisterNewTasksWriterConfig {

    @Bean
    public JdbcBatchItemWriter<Task> registerNewTasksWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Task>()
                .dataSource(dataSource)
                .sql("INSERT INTO task (id, description) VALUES (nextval('sq_task'), :description)")
                .beanMapped()
                .build();
    }
}
