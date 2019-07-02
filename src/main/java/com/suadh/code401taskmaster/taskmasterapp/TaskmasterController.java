package com.suadh.code401taskmaster.taskmasterapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
        public List postTasks(@RequestParam String title, @RequestParam String description, @RequestParam String status) {
                Taskmaster task = new Taskmaster(title, description, status);
                taskmasterRepository.save(task);
                List<Taskmaster> taskList = (List)taskmasterRepository.findAll();
                return taskList;
        }

        @GetMapping("/tasks")
        public List<Taskmaster> getTasks() {
                List<Taskmaster> taskList = (List)taskmasterRepository.findAll();
                return taskList;
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
                else {
                        task.setStatus("Finished");
                }
                taskmasterRepository.save(task);
                List<Taskmaster> taskList = (List)taskmasterRepository.findAll();
                return taskList;
        }

}
