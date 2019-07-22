package com.suadh.code401taskmaster.taskmasterapp.taskmaster;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.suadh.code401taskmaster.taskmasterapp.config.S3Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;

@RestController
public class TaskmasterController {

        @Autowired
        S3Client s3Client;

        @Autowired
        TaskmasterRepository taskmasterRepository;


        //Things we need for sending email to admin.
        static final String FROM = "sudeep_sudeep07@yahoo.com";

        // Replace recipient@example.com with a "To" address. If your account
        // is still in the sandbox, this address must be verified.
        static final String TO = "sudeep_sudeep07@yahoo.com";

        // The configuration set to use for this email. If you do not want to use a
        // configuration set, comment the following variable and the
        // .withConfigurationSetName(CONFIGSET); argument below.
        static final String CONFIGSET = "ConfigSet";

        // The subject line for the email.
        static final String SUBJECT = "Task status";

        // The HTML body for the email.
        static final String HTMLBODY = "<h1>Task Complete</h1>";

        // The email body for recipients with non-HTML email clients.
        static final String TEXTBODY = "This email was sent to notify you that the task is complete. ";

        public void sendEmail(){
                try {
                        AmazonSimpleEmailService client =
                                AmazonSimpleEmailServiceClientBuilder.standard()
                                        // Replace US_WEST_2 with the AWS Region you're using for
                                        // Amazon SES.
                                        .withRegion(Regions.US_WEST_2).build();
                        SendEmailRequest request = new SendEmailRequest()
                                .withDestination(
                                        new Destination().withToAddresses(TO))
                                .withMessage(new Message()
                                        .withBody(new Body()
                                                .withHtml(new Content()
                                                        .withCharset("UTF-8").withData(HTMLBODY))
                                                .withText(new Content()
                                                        .withCharset("UTF-8").withData(TEXTBODY)))
                                        .withSubject(new Content()
                                                .withCharset("UTF-8").withData(SUBJECT)))
                                .withSource(FROM);
                                // Comment or remove the next line if you are not using a
                                // configuration set
//                                .withConfigurationSetName(CONFIGSET);
                        client.sendEmail(request);
                        System.out.println("Email sent!");
                } catch (Exception ex) {
                        System.out.println("The email was not sent. Error message: "
                                + ex.getMessage());
                }
        }



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

                        AmazonSNSClient snsClient = new AmazonSNSClient();
                        String message = "Task has been assigned for you";
                        String phoneNumber = "+15809196943";
                        Map<String, MessageAttributeValue> smsAttributes =
                                new HashMap<String, MessageAttributeValue>();
                        //<set SMS attributes>
                        sendSMSMessage(snsClient, message, phoneNumber, smsAttributes);
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

                        AmazonSNSClient snsClient = new AmazonSNSClient();
                        String message = "Task has been assigned for you";
                        String phoneNumber = "+15809196943";
                        Map<String, MessageAttributeValue> smsAttributes =
                                new HashMap<String, MessageAttributeValue>();
                        //<set SMS attributes>
                        sendSMSMessage(snsClient, message, phoneNumber, smsAttributes);
                }

                else if(task.getStatus().equals("Assigned")){
                        task.setStatus("Accepted");
                }

                else if(task.getStatus().equals("Accepted")){
                        task.setStatus("Finished");
                        sendEmail();

                }
                taskmasterRepository.save(task);
                return allTasks();
        }

        public static void sendSMSMessage(AmazonSNSClient snsClient, String message,
                                          String phoneNumber, Map<String, MessageAttributeValue> smsAttributes) {

                PublishResult result = snsClient.publish(new PublishRequest()
                        .withMessage(message)
                        .withPhoneNumber(phoneNumber)
                        .withMessageAttributes(smsAttributes));
                System.out.println(result); // Prints the message ID.
        }


        @CrossOrigin
        @PostMapping("/tasks/{id}/images")
        public RedirectView uploadFile(
                @PathVariable UUID id,
                @RequestPart(value = "file") MultipartFile file
        ){
                System.out.println(file);
                String pic = this.s3Client.uploadFile(file).get(0);
                String resizedPic = this.s3Client.uploadFile(file).get(1);
                Taskmaster task = taskmasterRepository.findById(id).get();
                task.setPic(pic);
                task.setPicResized(resizedPic);
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


//https://docs.aws.amazon.com/sns/latest/dg/sms_publish-to-phone.html
//https://docs.aws.amazon.com/sns/latest/dg/sms_publish-to-phone.html#sms_publish_console
//https://docs.aws.amazon.com/ses/latest/DeveloperGuide/send-using-sdk-java.html
//https://docs.aws.amazon.com/ses/latest/DeveloperGuide/send-using-sdk-java.html
//https://mvnrepository.com/artifact/com.amazonaws/aws-java-sdk-ses/1.11.595

