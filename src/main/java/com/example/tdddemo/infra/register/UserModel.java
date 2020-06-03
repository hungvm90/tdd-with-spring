package com.example.tdddemo.infra.register;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity(name = "users")
public class UserModel {
    @Id
    @GeneratedValue
    private int id;
    private String email;
    private String mobile;
    private String password;
    private Date createdAt;
    private Date updatedAt;
}
