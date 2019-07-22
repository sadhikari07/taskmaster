# Lab: Event Driven Architecture

## Overview

For this assignment, we will be wiring up our Queues with live running cloud based code, creating a distributed, event driven architecture.

## Feature Tasks

### Taskmaster vX

- Send an email to an administrator when a task is completed
- Send a text to the person to whom a task is assigned (when it gets assigned)
- When a task is deleted from Dynamo, trigger a message that will fire a lambda to remove any images associated to it from S3
- Instead of having S3 run the resizer automatically on upload, evaluate the size of the image in your Java code and then send a message to a Q, that will in turn trigger the lambda resizer -- only when the image > 350k

## Approach:
The above mentioned tasks were compoleted by taking the approach listed below:

- Send an email to an administrator when a task is completed
  - Created a method sendEmail which was triggered when the status of task was changed from assigned to finished.
  ![Screen shot](https://raw.githubusercontent.com/sadhikari07/taskmaster/master/assets/sendEmail.png)
  
  - Screenshot of succesful email sent:
  ![Screen shot](https://raw.githubusercontent.com/sadhikari07/taskmaster/master/assets/emailSent.png)
  
- Send a text to the person to whom a task is assigned (when it gets assigned)
  - Created a method sendSMSMessage which was called when a task was entered with an assignee or an assignee was set at any point of time.
  ![Screen shot](https://raw.githubusercontent.com/sadhikari07/taskmaster/master/assets/sendSMS.png)
  
  - Screenshot of message on assignee's cell phone.
  ![Screen shot](https://raw.githubusercontent.com/sadhikari07/taskmaster/master/assets/sms.png)
  
- Instead of having S3 run the resizer automatically on upload, evaluate the size of the image in your Java code and then send a message to a Q, that will in turn trigger the lambda resizer -- only when the image > 350k
  - I created a method sqsSend which sent a message to a sqs queue that I created whenever an image with size greater than 350k was uploaded.
  ![Screen shot](https://raw.githubusercontent.com/sadhikari07/taskmaster/master/assets/queueForImage.png)
  
  - The queue triggered lambda funciton that resized the image.
  ![Screen shot](https://raw.githubusercontent.com/sadhikari07/taskmaster/master/assets/lambda%26sqs.png)
  
  
