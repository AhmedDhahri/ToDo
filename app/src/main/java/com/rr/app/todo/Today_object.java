package com.rr.app.todo;

public class Today_object {
    Task_object task;
    int index;
    Today_object(int index, Task_object obj){
        task = obj;
        this.index = index;
    }
}
