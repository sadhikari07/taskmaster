package com.suadh.code401taskmaster.taskmasterapp.appUser;

import com.suadh.code401taskmaster.taskmasterapp.taskmaster.Taskmaster;
import com.suadh.code401taskmaster.taskmasterapp.taskmaster.TaskmasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class AppUserController {

    @Autowired
    TaskmasterRepository taskmasterRepository;

    @GetMapping("/users/{name}/tasks")
    public List<Taskmaster> getTasksForOnePerson(@PathVariable String name) {
        List<Taskmaster> taskList = taskmasterRepository.findByAssignee(name);
        return taskList;
    }

    @PutMapping("/tasks/{id}/assign/{assignee}")
    public List updateTasks(@PathVariable UUID id, @PathVariable String assignee){
        Taskmaster task = taskmasterRepository.findById(id).get();
        task.setAssignee(assignee);
        task.setStatus("Assigned");
        taskmasterRepository.save(task);
        return tasksAssignedToUser(assignee);
    }

    //Helper method to get task list for one particular user
    public List tasksAssignedToUser(String assignee){
        List<Taskmaster> taskList = taskmasterRepository.findByAssignee(assignee);
        return taskList;
    }

}
