package com.thuannluit.demotest;

import androidx.annotation.NonNull;

public class Employee {
    private String id, title, name;

    public Employee() {
    }

    public Employee(String id, String title, String name) {
        this.id = id;
        this.title = title;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
        return id + " - " + title + " - " + name;
    }
}
