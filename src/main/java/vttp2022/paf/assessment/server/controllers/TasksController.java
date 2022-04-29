package vttp2022.paf.assessment.server.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.servlet.ModelAndView;

import vttp2022.paf.assessment.server.models.Task;
import vttp2022.paf.assessment.server.models.User;
import vttp2022.paf.assessment.server.services.TodoService;

// TODO: fill in this class according to the assessment tasks

@Controller
@RequestMapping
public class TasksController {

    @Autowired
    private TodoService toDoSvc;

    @PostMapping(path = "/task")
    public ModelAndView add(@RequestBody MultiValueMap<String, String> payload) {

        String username=payload.getFirst("username");
        User user = new User();
        user.setUsername(username);

        int i = 0;
        while (true) {
            String _description = payload.getFirst("description-%s".formatted(i));
            if ((null == _description) || (0 == _description.trim().length()))
                break;
            String _priority = payload.getFirst("priority-%d".formatted(i));
            String _dueDate = payload.getFirst("dueDate-%d".formatted(i));
            Integer priority = Integer.parseInt(_priority);
            Date sqlDate = Date.valueOf(_dueDate);
            Task task = new Task();
            task.setDescription(_description);
            task.setPriority(priority);
            task.setDueDate(sqlDate);
            user.addTask(task);
            i++;
        }
        
        ModelAndView mvc = new ModelAndView();

        if(toDoSvc.upsertTask(username, user)==null){
            mvc.setViewName("error");
            mvc.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            mvc.setViewName("result");
            mvc.setStatus(HttpStatus.OK);
            mvc.addObject("username", user.getUsername());
            mvc.addObject("taskCount",i);
        }
        
        return mvc;
        
    }
    
}
