# Task Manager

## Overview
Today, you’ll start building a new application called TaskMaster. 
It’s a task-tracking application with the same basic goal as Trello: allow users to keep track of tasks to be done and their status. 
While we’ll start today with a basic feature set, we will continue building out the capabilities of this application over time.

The reason we’re starting small on this server is because the main focus for the rest of the course is not full-stack web development, but is instead to gain experience with different features of AWS. 
Everything we build will have the added task of deployment using AWS. Today, you’ll get to use DynamoDB as the database for your application. 
As we continue to build out our skill with DynamoDB, that structure of our data will be our main focus for future development on TaskMaster.

## Setup
Create a new repo called taskmaster to hold your work on this series of labs. 
Within that repo, use the Spring Initializr to set up a new web app. 
Use the directions from the Using DynamoDB With Java resource, linked above, to ensure you have the dependencies you need.

## Feature Tasks
A user should be able to make a GET request to /tasks and receive JSON data representing all of the tasks.
Each task should have a title, description, and status.
A user should be able to make a POST request to /tasks with body parameters for title and description to add a new task.
All tasks should start with a status of Available.
The response to that request should contain the complete saved data for that task.
A user should be able to make a PUT request to /tasks/{id}/state to advance the status of that task.
Tasks should advance from Available -> Assigned -> Accepted -> Finished.
A user should be able to access this application on the Internet.
The application should be deployed to EC2, with the database on DynamoDB.
You should also use DynamoDB for your local application testing; in other words, you should connect to your production database, even in your development environment. 
(This is generally a bad practice, and we’ll see how to work differently soon.)

## Description of the taskMaster application:
The app uses postman to create, update and get tasks.Above mentioned feature tasks are implemented on the app.

## Link to your deployed application:
taskmaster-dev-cname.us-east-2.elasticbeanstalk.com


## Issues faced:
- Resource not found error due to not specifying the region.
- 504 bad gateway error during deployment

Resources:
https://github.com/codefellows/seattle-java-401d4/tree/master/class-26
