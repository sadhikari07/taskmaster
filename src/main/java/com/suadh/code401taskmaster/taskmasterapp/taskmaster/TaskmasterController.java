package com.suadh.code401taskmaster.taskmasterapp.taskmaster;

import com.suadh.code401taskmaster.taskmasterapp.config.S3Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
public class TaskmasterController {

        @Autowired
        S3Client s3Client;

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


        @CrossOrigin
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


        @CrossOrigin
        @PostMapping("/tasks/{id}/images")
        public RedirectView uploadFile(
                @PathVariable UUID id,
                @RequestPart(value = "file") MultipartFile file
        ){
                System.out.println(file);
                String pic = this.s3Client.uploadFile(file);
                Taskmaster task = taskmasterRepository.findById(id).get();
                task.setPic(pic);
                taskmasterRepository.save(task);
                return new  RedirectView("http://taskmasterfrontend.s3-website-us-east-1.amazonaws.com");
        }


        @CrossOrigin
        @GetMapping("/tasks/{id}")
        public Taskmaster getTasksByID(@PathVariable UUID id) {
                Taskmaster task = taskmasterRepository.findById(id).get();
                return task;
        }


        //Helper method to get all tasks
        public List allTasks(){
                List<Taskmaster> taskList = (List)taskmasterRepository.findAll();
                return taskList;
        }
}
