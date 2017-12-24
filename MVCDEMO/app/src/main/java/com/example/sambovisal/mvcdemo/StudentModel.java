package com.example.sambovisal.mvcdemo;

/**
 * Created by sambo visal on 28/08/2017.
 */

public class StudentModel {
    private int userID;
    private String userPass;
    public StudentModel(int userID,String userPass){
        this.userID = userID;
        this.userPass = userPass;
    }
    public int getUserID(){
        return userID;
    }
    public String getUserName(){
        return userPass;
    }
    public void setUserID(int userID){
        this.userID = userID;
    }
    public void setUserPass(String userPass){
        this.userPass = userPass;
    }
}
