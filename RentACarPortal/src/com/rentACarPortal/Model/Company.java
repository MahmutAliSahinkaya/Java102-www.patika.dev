package com.rentACarPortal.Model;

import com.rentACarPortal.Helper.DBConnector;

import java.sql.*;
import java.util.ArrayList;

public class Company {
    private int id;
    private String name;
    private String address;
    private String city;
    private String email;
    private String phoneNumber;
    private Array serviceSpec;

    public Company() {
    }

    public Company(int id, String name, String address, String email, String phoneNumber, String city, Array serviceSpec) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.serviceSpec = serviceSpec;
    }


    public static Company getCompanyByName(String companyName){
        Company obj = null;
        String query = "SELECT * FROM company WHERE name = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,companyName);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                obj =  new Company(rs.getInt("id"),rs.getString("name"),rs.getString("address"),
                        rs.getString("email"),rs.getString("phoneNumber"),
                        rs.getString("city"), rs.getArray("serviceSpec"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    public static Company getFetch(int id){
        Company obj = null;
        String query = "SELECT * FROM company WHERE id = "+id;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()){
                obj =  new Company(rs.getInt("id"),rs.getString("name"),rs.getString("address"),
                        rs.getString("email"),rs.getString("phoneNumber"),
                        rs.getString("city"), rs.getArray("serviceSpec"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    public static boolean add(String name, String address, String email, String phone_num, ArrayList<String> serviceSpec, String city) {
        String sql = "INSERT INTO company ( name,  address,  email,  phoneNumber, city, serviceSpec) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            Array array = DBConnector.getInstance().createArrayOf("VARCHAR", serviceSpec.toArray());
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(sql);
            pr.setString(1, name);
            pr.setString(2, address);
            pr.setString(3, email);
            pr.setString(4, phone_num);
            pr.setString(5, city);
            pr.setArray(6, array);

            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static ArrayList<Company> getList() {
        ArrayList<Company> companyList = new ArrayList<>();
        Company obj;
        String sql = "SELECT * FROM company";
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                obj = new Company(rs.getInt("id"), rs.getString("name"), rs.getString("address"), rs.getString("email"),
                        rs.getString("phoneNumber"), rs.getString("city"), rs.getArray("serviceSpec"));
                companyList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return companyList;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Array getServiceSpec() {
        return serviceSpec;
    }

    public void setServiceSpec(Array serviceSpec) {
        this.serviceSpec = serviceSpec;
    }
}

