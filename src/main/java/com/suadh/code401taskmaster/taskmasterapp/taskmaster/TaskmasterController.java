package com.suadh.code401taskmaster.taskmasterapp.taskmaster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
public class TaskmasterController {
        @Autowired
        TaskmasterRepository taskmasterRepository;

        @GetMapping("/")
        public String getHome(Principal p, Model m) {
            return "home";
        }

        @PostMapping("/tasks")
        public List postTasks(@RequestBody Taskmaster task) {
                if(task.getAssignee().isEmpty()){
                        task.setStatus("Available");
                }
                else{
                        task.setStatus("Assigned");
                }
                taskmasterRepository.save(task);
               return allTasks();
        }

        @GetMapping("/tasks")
        public List<Taskmaster> getTasks() {
                return allTasks();
        }


        @PutMapping("/tasks/{id}/state")
        public List updateTasks(@PathVariable UUID id){
                Taskmaster task = taskmasterRepository.findById(id).get();
                if(task.getStatus().equals("Available")){
                        task.setStatus("Assigned");
                }

                else if(task.getStatus().equals("Assigned")){
                        task.setStatus("Accepted");
                }

                else if(task.getStatus().equals("Accepted")){
                        task.setStatus("Finished");
                }
                taskmasterRepository.save(task);
                return allTasks();
        }

        //Helper method to get all tasks
        public List allTasks(){
                List<Taskmaster> taskList = (List)taskmasterRepository.findAll();
                return taskList;
        }
}
