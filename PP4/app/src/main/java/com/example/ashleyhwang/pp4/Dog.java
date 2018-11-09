package com.example.ashleyhwang.pp4;

import java.io.Serializable;

public class Dog implements Serializable{

    private int id;
    private String name;
    private String img_link;
    private String weight;
    private String height;
    private String life_span;
    private String bred_for;
    private String breed_group;

    public Dog( ){
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setImg_link(String img_link) {
        this.img_link = img_link;
    }

    public String getImg_link() {
        return img_link;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
    public String getWeight(){return weight;}

    public void setHeight(String height){this.height= height;}
    public String getHeight(){return height;}

    public void setLife_span(String life_span){this.life_span=life_span;}
    public String getLife_span(){return life_span;}

    public void setBred_for(String bred_for) {
        this.bred_for = bred_for;
    }
    public String getBred_for(){return bred_for;}

    public void setBreed_group(String breed_group) {
        this.breed_group = breed_group;
    }
    public String getBreed_group(){return breed_group;}

    public String toString(){
        String msg = getName()+"1234";
        return msg;
    }
}
