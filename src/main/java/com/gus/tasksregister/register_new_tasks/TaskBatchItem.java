package com.gus.tasksregister.register_new_tasks;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static java.util.Objects.isNull;

@NoArgsConstructor
@Getter
@Setter
public class TaskBatchItem {

    private String description;
    private String problems;

    public void addProblem(String problemDescription) {
        if (isNull(problems) || problems.isBlank()) {
            problems = "#".concat(problemDescription);
        } else {
            problems = problems.concat(";").concat(problemDescription);
        }
    }
}
