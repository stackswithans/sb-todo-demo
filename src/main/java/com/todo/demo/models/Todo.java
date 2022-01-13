package com.todo.demo.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("todos")
public class Todo {
    @Id
    private ObjectId id;
    private String desc;
    private String place;
    private String dueDate;
    private boolean done = false;

    public Todo(String desc, String place, String dueDate, boolean done) {
        this.desc = desc;
        this.place = place;
        this.dueDate = dueDate;
        this.done = done;
    }

    public String getId() {
        return id.toHexString();
    }

    public String getDesc() {
        return desc;
    }

    public String getPlace() {
        return place;
    }

    public String getDueDate() {
        return dueDate;
    }

    public boolean isDone() {
        return done;
    }
}
