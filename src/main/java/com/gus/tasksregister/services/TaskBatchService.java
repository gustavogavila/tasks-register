package com.gus.tasksregister.services;

import com.gus.tasksregister.register_new_tasks.RegisterNewTasksReader;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskBatchService {

    private final JobLauncher customJobLauncher;
    private final Job registerNewTasksJob;
    private final RegisterNewTasksReader registerNewTasksReader;

    public void startProcess(MultipartFile file) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException, IOException {
        Resource resource = new InputStreamResource(file.getInputStream());
        registerNewTasksReader.setResource(resource);

        customJobLauncher.run(registerNewTasksJob, new JobParametersBuilder()
                .addString("jobUUID", UUID.randomUUID().toString())
                .toJobParameters()
        );
    }
}
