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

    public String toString() {
        return "Пользователь: \nИмя: " + firstname +"\nФамилия: " + lastname + "\nНикнейм: " + nickname + "\n";
    }

    public String getJSON() {
        return "{\n" + "\t\"user\" : \n\t\t\t{\n\t\t\t\t\"firstname\" : \"" + firstname + "\",\n\t\t\t\t\"lastname\" : \""
                + lastname + "\",\n\t\t\t\t\"nickname\" : \"" + nickname  + "\"\n\t\t\t}\n}";
    }
}

