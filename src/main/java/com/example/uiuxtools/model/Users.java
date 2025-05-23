package com.example.uiuxtools.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class Users {

    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment for primary key
    @Column(name = "UserId", nullable = false)
    private Integer userId;

    @Column(name = "Email", nullable = false, length = 100)
    private String email;

    @Column(name = "Password", nullable = false, length = 20)
    private String password;

    @Column(name = "Lastname", nullable = false, length = 60)
    private String lastname;

    @Column(name = "Firstname", nullable = false, length = 50)
    private String firstname;

    @Column(name = "TypeOfUser", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role typeOfUser;

    // Getters and Setters

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Role getTypeOfUser() {
        return typeOfUser;
    }

    public void setTypeOfUser(Role typeOfUser) {
        this.typeOfUser = typeOfUser;
    }

    //Constructor
    public Users() {
    }

    public Users(Integer userId, String email, String password, String lastname, String firstname, Role typeOfUser) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
        this.typeOfUser = typeOfUser;
    }
}
