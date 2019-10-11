package com.thuannluit.models;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Employees {
    private String title;
    private ArrayList<Employee> listEmployee = null;

    public Employees(String title) {
        this.title = title;
    }

    public Employees() {
        this.title = title;
        this.listEmployee = new ArrayList<Employee>();
    }

    public boolean isDuplicate(Employee e) {
        for (Employee e1 : listEmployee) {
            if (e1.getName().trim().equalsIgnoreCase(e.getName().trim()))
                return true;
        }
        return false;
    }

    public ArrayList<Employee> getListEmployee() {

        return this.listEmployee;
    }

    public int size() {
        return listEmployee.size();
    }

    public Employee get(int i) {
        return listEmployee.get(i);
    }

    @NonNull
    @Override
    public String toString() {
        return this.title;
    }
}
