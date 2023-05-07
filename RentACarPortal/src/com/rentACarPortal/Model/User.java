package com.rentACarPortal.Model;

import com.rentACarPortal.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String uname;
    private String email;
    private String pass;
    private String type;

    public User(){}

    public User(int id, String firstName, String lastName, String uname, String email, String pass, String type) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.uname = uname;
        this.email = email;
        this.pass = pass;
        this.type = type;
    }

    public static User getFetch(String uname_email, String pass){
        User obj = null;
        String query = "SELECT * FROM user WHERE uname=? AND pass=?";
        if (uname_email.contains("@")){
            query = "SELECT * FROM user WHERE email=? AND pass=?";
        }
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,uname_email);
            pr.setString(2,pass);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                obj = new User(rs.getInt("id"),rs.getString("firstName"),
                        rs.getString("lastName"),rs.getString("uname"),
                        rs.getString("email"),rs.getString("pass"),rs.getString("type"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return obj;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

