# Task Manager

## Overview
There are too many tasks on TaskMaster! 
Users are struggling to view only the tasks relevant to them. 
Today, you’ll allow users to assign tasks, and to view the tasks assigned to a user.

## Setup
Continue working in your taskmaster repo. Use as many DynamoDB features as you can, to make your queries as efficient as possible.

## Feature Tasks
 - A user should be able to make a GET request to /tasks and receive JSON data representing all of the tasks.
Each task should have a title, description, assignee, and status, all of which are strings, as well as an id.
 - A user should be able to make a GET request to /users/{name}/tasks and receive JSON data representing all of the tasks assigned to that user.
This should work (i.e. return an empty array) if the requested username does not have any assigned tasks.
 - A user should be able to make a POST request to /tasks with body parameters for title, description, and assignee to add a new task.
A task should start with a status of Available if there is no assignee, and Assigned if there is an assignee.
The response to that request should contain the complete saved data for that task.
It should not matter whether or not that assignee is already in the database.
 - A user should be able to make a PUT request to /tasks/{id}/state to advance the status of that task.
Tasks should advance from Available -> Assigned -> Accepted -> Finished.
 - A user should be able to make a PUT request to /tasks/{id}/assign/{assignee} to assign a particular user to a task.
Changing the assignee should set the task’s state to Assigned.
This should work whether or not that assignee name is already in the database.
 - A user should be able to access this application on the Internet.
 The application should be deployed to EC2, with the database on DynamoDB.
You should also use DynamoDB for your local application testing; in other words, you should connect to your production database, even in your development environment. (This is generally a bad practice, and we’ll see how to work differently soon.)

## Description of the taskMaster application:
The app uses postman to create, update and get tasks. Above mentioned feature tasks are implemented on the app.

## Link to your deployed application:
taskmaster-dev-cname.us-east-2.elasticbeanstalk.com

## Routes:
- GET request to /tasks and receive JSON data representing all of the tasks
- POST request to /tasks with body parameters for title and description to add a new task.
- PUT request to /tasks/{id}/state to advance the status of that task
- GET request to /users/{name}/tasks and receive JSON data representing all of the tasks assigned to that user.
- POST request to /tasks with body parameters for title, description, and assignee to add a new task.
- PUT request to /tasks/{id}/assign/{assignee} to assign a particular user to a task.


## Issues faced:
- Resource not found error due to not specifying the region.
- 504 bad gateway error during deployment

Resources:
https://github.com/codefellows/seattle-java-401d4/tree/master/class-26
