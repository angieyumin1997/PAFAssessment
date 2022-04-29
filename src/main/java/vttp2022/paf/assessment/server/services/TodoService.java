package vttp2022.paf.assessment.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vttp2022.paf.assessment.server.models.User;
import vttp2022.paf.assessment.server.repositories.TaskRepository;

// TODO: fill in this class according to the assessment tasks

@Service
public class TodoService {

    @Autowired
    private UserService userService;

    @Autowired
    private TaskRepository taskRepo;

    @Transactional
    public Boolean upsertTask(String username, User user){
        Boolean status = null;

        if(!userService.findUserByUsername(username).isEmpty()){
            // user exist
            status = taskRepo.insertTasks(username, user.getTasks());
        }else{
            // user doesn't exist
            userService.insertUser(user);
            status = taskRepo.insertTasks(username, user.getTasks());
        }

        return status;
    }

}
