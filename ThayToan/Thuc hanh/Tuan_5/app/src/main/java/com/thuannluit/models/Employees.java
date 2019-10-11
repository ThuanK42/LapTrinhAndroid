package com.thuannluit.elements;

import java.util.ArrayList;

public class Employees {

    ArrayList<Employee> list = new ArrayList<>();

    public Employees() {
    }

    public Employees(ArrayList<Employee> list) {
        this.list = list;
    }

    public ArrayList<Employee> getList() {
        return list;
    }

    public void setList(ArrayList<Employee> list) {
        this.list = list;
    }
}
