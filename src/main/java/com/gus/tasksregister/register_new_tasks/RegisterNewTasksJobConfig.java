package com.gus.tasksregister.register_new_tasks;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class RegisterNewTasksJobConfig {

    @Bean
    public Job registerNewTasksJob(JobRepository jobRepository, Step readTasks) {
        return new JobBuilder("REGISTER_NEW_TASKS_JOB", jobRepository)
                .start(readTasks)
                .build();
    }

//    @Bean
//    public Step simpleStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
//        return new StepBuilder("SIMPLE_STEP", jobRepository)
//                .tasklet(((contribution, chunkContext) -> {
//                    System.out.println("Teste de task simples");
//                    return RepeatStatus.FINISHED;
//                }), transactionManager).build();
//    }

    @Bean
    public Step readTasks(JobRepository jobRepository,
                          PlatformTransactionManager transactionManager,
                          RegisterNewTasksReader registerNewTasksReader,
                          JdbcBatchItemWriter<Task> registerNewTasksWriter
                          ) {
        return new StepBuilder("READ_TASKS_STEP", jobRepository)
                .<Task, Task>chunk(10, transactionManager)
                .reader(registerNewTasksReader)
                .writer(registerNewTasksWriter)
                .build();
    }
}
