package com.suadh.code401taskmaster.taskmasterapp.taskmaster;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.UUID;

@DynamoDBTable(tableName = "taskmaster")
    public class Taskmaster {

    private UUID  taskId;
    private String description;
    private String title;
    private String status;
    private String assignee;
    private String pic;
    private String picResized;


    public Taskmaster() {

    }

    public Taskmaster(String title, String description, String assignee) {
        this.title = title;
        this.description = description;
        this.assignee = assignee;
        this.pic = null;
        this.picResized = null;
    }

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    public UUID  getTaskId() {
        return taskId;
    }

    @DynamoDBAttribute
    public String getTitle() {
        return title;
    }

    @DynamoDBAttribute
    public String getDescription() {
        return description;
    }

    @DynamoDBAttribute
    public String getStatus() {
        return status;
    }

    @DynamoDBAttribute
    public String getAssignee() {
        return assignee;
    }

    @DynamoDBAttribute
    public String getPic() {
        return pic;
    }

    @DynamoDBAttribute
    public String getPicResized() {
        return picResized;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAssignee(String assignee) { this.assignee = assignee; }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setPicResized(String picResized) {
        this.picResized = picResized;
    }

}
