package com.example.ashleyhwang.todolist;

import java.io.Serializable;



public class Model implements Serializable {

    private String itemName;

    public Model(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }


    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

}
