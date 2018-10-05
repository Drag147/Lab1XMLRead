package com.company;

public class User {

    private String firstname;
    private String lastname;
    private String nickname;

    public User()
    {
        this.firstname = "";
        this.lastname = "";
        this.nickname = "";
    }

    public User(String firstname, String lastname, String nickname)
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.nickname = nickname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNickname() {
        return nickname;
    }
}

