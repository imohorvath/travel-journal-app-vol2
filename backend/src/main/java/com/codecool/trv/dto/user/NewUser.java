package com.codecool.trv.dto.user;

public class NewUser {

    private final String nickName;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;

    public NewUser(String nickName, String firstName, String lastName, String email, String password) {
        this.nickName = nickName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
}
