package com.todo.demo.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("todos")
public class Todo {
    private String desc;
    private String place;
    private String dueDate;
    private boolean done = false;

    public Todo(String desc, String place, String dueDate) {
        this.desc = desc;
        this.place = place;
        this.dueDate = dueDate;
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
