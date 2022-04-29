package vttp2022.paf.assessment.server.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp2022.paf.assessment.server.models.Task;

// TODO: fill in this class according to the assessment tasks
@Repository
public class TaskRepository implements Queries{

    @Autowired
    private JdbcTemplate template;

    public boolean insertTask(String username, Task task) {
        int count = template.update(SQL_INSERT_TASK,
            task.getDescription(),
            task.getPriority(),
            task.getDueDate(),
            username);
        return 1 == count;
    }

    public boolean insertTasks(String username, List<Task> tasks) {
        for (Task t: tasks)
            if (!insertTask(username, t))
                return false;
        return true;
    }


}
