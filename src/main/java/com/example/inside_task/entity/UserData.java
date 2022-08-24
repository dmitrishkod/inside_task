package com.example.inside_task.entity;
import lombok.Builder;

import javax.persistence.*;

@Entity
@Table(name = "user_data")
@Builder
public class UserData {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user", referencedColumnName = "id")
    private User user;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "mail")
    private String mail;

    public UserData(Integer id, User login, String name, String address, String mail) {
        this.id = id;
        this.user = login;
        this.name = name;
        this.address = address;
        this.mail = mail;
    }

    public UserData() {
    }

    public UserData(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
