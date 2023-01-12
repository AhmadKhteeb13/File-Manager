package com.example.filesmanegar.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@Data
@AllArgsConstructor
@Builder
@Entity
@Table(name="users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String email;

    private String password;

    private String userName;
    private boolean Is_login;
    @Transient
    private List<Long> userGrops = new ArrayList<>();
    @Transient
    private List<Long> userFiles = new ArrayList<>();

    public UserModel() {
    }

    public UserModel(String email, String password, String userName, boolean is_login, List<Long> userGrops) {
        this.email = email;
        this.password = password;
        this.userName = userName;
        Is_login = is_login;
        this.userGrops = userGrops;
    }

    public UserModel(String email, String password, String userName) {
        this.email = email;
        this.password = password;
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public boolean isIs_login() {
        return Is_login;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setIs_login(boolean is_login) {
        Is_login = is_login;
    }

}
