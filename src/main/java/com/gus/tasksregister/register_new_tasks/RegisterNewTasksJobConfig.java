package com.gus.tasksregister.register_new_tasks;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class RegisterNewTasksJobConfig {

    @Bean
    public Job registerNewTasksJob(JobRepository jobRepository, Step readTasks) {
        return new JobBuilder("REGISTER_NEW_TASKS_JOB", jobRepository)
                .preventRestart()
                .incrementer(new RunIdIncrementer())
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
                          ItemProcessor<TaskBatchItem, TaskBatchItem> registerNewTasksProcessor,
                          ClassifierCompositeItemWriter<TaskBatchItem> taskClassifierWriter,
                          FlatFileItemWriter<TaskBatchItem> registerNewErrorTaskWriter
                          ) {
        return new StepBuilder("READ_TASKS_STEP", jobRepository)
                .<TaskBatchItem, TaskBatchItem>chunk(10, transactionManager)
                .reader(registerNewTasksReader)
                .processor(registerNewTasksProcessor)
                .writer(taskClassifierWriter)
                .stream(registerNewErrorTaskWriter)
                .build();
    }
}
