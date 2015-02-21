package com.example.mouhannad.drawer;

public class Actors {

    private String description;
    private String price;

    private String name;

    private String image;

    public Actors() {
        // TODO Auto-generated constructor stub
    }

    public Actors(String name, String description, String image,String price
    ) {
        super();
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;


    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDob() {
        return price;
    }

    public void setDob(String price) {
        this.price= price;
    }



    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
