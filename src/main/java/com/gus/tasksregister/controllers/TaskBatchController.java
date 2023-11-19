package com.gus.tasksregister.controllers;

import com.gus.tasksregister.services.TaskBatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("tasks")
@RequiredArgsConstructor
public class TaskBatchController {

    private final TaskBatchService taskBatchService;

    @PostMapping("batch")
    public ResponseEntity<String> createManyTasks(@RequestParam("file")MultipartFile file) {
        try {
            taskBatchService.startProcess(file);
            return ResponseEntity.status(HttpStatus.OK).body("Process started successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Error when starting the process");
        }
    }
}
