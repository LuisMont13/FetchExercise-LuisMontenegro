package com.example.fetch;

public class Item {

    int id;
    int listId;
    String name;



    public Item(int id, int listId, String name){
        this.id = id;
        this.listId = listId;
        this.name = name;
    }

    public Item(){
    }


    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public int getListId() {

        return listId;
    }

    public void setListId(int listId) {

        this.listId = listId;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

}
