package com.example.sambovisal.loginsignupdatabase;

/**
 * Created by sambo visal on 08/10/2017.
 */

public class LogSigForm {


    public String name,pass,email;

    public LogSigForm(){

    }
    public LogSigForm(String name, String pass, String email){
        this.name = name;
        this.pass = pass;
        this.email = email;

    }
    public String getName() {
        return name;
    }
    public String getPass() {
        return pass;
    }

    public String getEmail() {
        return email;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
