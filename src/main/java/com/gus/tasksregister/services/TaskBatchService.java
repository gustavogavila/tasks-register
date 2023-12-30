package com.gus.tasksregister.services;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskBatchService {

    private final JobLauncher jobLauncher;
    private final Job registerNewTasksJob;

    public void startProcess(MultipartFile file) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        // TODO Passar arquivo recebido para o job
        jobLauncher.run(registerNewTasksJob, new JobParametersBuilder()
                .addString("jobUUID", UUID.randomUUID().toString())
                .toJobParameters()
        );
        // TODO Configurar job e passar arquivo para ele (inicialmente salvando no sistema de arquivos) e utilizando o FlatFileItemReader
        // TODO Passar arquivo para o job diretamente (sem salvar no sistema de arquivos) e utilizando um ItemReader customizado
    }
}
