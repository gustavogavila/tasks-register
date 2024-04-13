package com.gus.tasksregister.register_new_tasks;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import static java.util.Objects.isNull;

@NoArgsConstructor
@Getter
@Setter
public class TaskBatchItem {

    private String description;
    private String title;
    private String categories_ids;
    private String problems;

    public void addProblem(@NonNull String problemDescription) {
        if (isNull(problems) || problems.isBlank()) {
            problems = "#".concat(problemDescription);
            return;
        }
        problems = problems.concat("#").concat(problemDescription.trim());
    }
}
