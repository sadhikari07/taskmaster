# Lambda

## Overview
AWS Lambda allows writing code that is triggered in the cloud, without thinking about maintaining servers. 
We’ll use it today to automatically run some processing on image files after they’re uploaded in TaskMaster.

## Feature Tasks
- A user should be able to upload an image at any size, and have both the original size and a thumbnail size associated with the task in question.
- When an image is uploaded to your S3 bucket, it should trigger a Lambda function. (That Lambda function may be written in any language.)
- That function should create a 50x50 pixel thumbnail version of that image, and save it to another S3 bucket.
- Your server should return both the full-size and the thumbnail-size URLs for each image associated with a task.

## Description of how to use lambda:


## Link to the deployed application:
- API: http://taskmaster-dev-cname.us-east-2.elasticbeanstalk.com/tasks
- Deployed Front-end:http://taskmasterfrontend.s3-website-us-east-1.amazonaws.com/ 


## Routes:
- GET request to /tasks and receive JSON data representing all of the tasks
- POST request to /tasks with body parameters for title and description to add a new task.
- PUT request to /tasks/{id}/state to advance the status of that task
- GET request to /users/{name}/tasks and receive JSON data representing all of the tasks assigned to that user.
- POST request to /tasks with body parameters for title, description, and assignee to add a new task.
- PUT request to /tasks/{id}/assign/{assignee} to assign a particular user to a task.
- POST request to /tasks/{id}/images to save an image to a particular task with that id.
- GET request to /tasks/{id} to display one particular task with the image assosiated with it.


## Issues faced:
- Having to reacreate lambda function.

Resources:
- https://github.com/codefellows/seattle-java-401d4/tree/master/class-26
- https://github.com/codefellows/seattle-java-401d4/tree/master/class-29/demo
