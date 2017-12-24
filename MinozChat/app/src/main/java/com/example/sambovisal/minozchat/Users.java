package com.example.sambovisal.minozchat;

/**
 * Created by sambo visal on 05/10/2017.
 */

public class Users {

    public String user;
    public String image;
    public String status;
    public String thump_image;

    public Users(){

    }

    public Users(String name, String image, String status,String thump_image) {
        this.user = name;
        this.image = image;
        this.status = status;
        this.thump_image = thump_image;
    }

    public String getName() {
        return user;
    }

    public void setName(String name) {
        this.user = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getThum_image(){return this.thump_image;}
    public void setThum_image(String thump_image){this.thump_image = thump_image;}
}
