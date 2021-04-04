package com.example.android.healthcareapp.Models;

public class User {
    public String firstName,lastName,email,birthdate;
    public User(){

    }

    public User(String firstname,String lastname, String email,String birthdate){
        this.firstName = firstname;
        this.lastName = lastname;
        this.email = email;
        this.birthdate = birthdate;
    }
}
