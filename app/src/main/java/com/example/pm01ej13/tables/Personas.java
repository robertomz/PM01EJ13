package com.example.pm01ej13.tables;

import java.io.Serializable;

public class Personas implements Serializable {
    private Integer id;
    private String fname;
    private String lname;
    private String age;
    private String mail;
    private String locate;

    public Personas(Integer id, String fname, String lname, String age, String mail, String locate) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.age = age;
        this.mail = mail;
        this.locate = locate;
    }

    public Personas() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getLocate() {
        return locate;
    }

    public void setLocate(String locate) {
        this.locate = locate;
    }
}



