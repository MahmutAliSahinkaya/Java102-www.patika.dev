package com.tourismAgencySystem.Model;

import com.tourismAgencySystem.Helper.DBConnector;

import java.sql.*;
import java.util.ArrayList;

public class Hotel {
    private int id;
    private String name;
    private String address;
    private String city;
    private String region;
    private String email;
    private String phoneNumber;
    private String star;
    private String boardingHouse;
    private Array serviceSpec;

    public Hotel() {
    }

    public Hotel(int id, String name, String address, String email, String phoneNumber, String boardingHouse, String city, String region, String star, Array serviceSpec) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.region = region;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.star = star;
        this.boardingHouse = boardingHouse;
        this.serviceSpec = serviceSpec;
    }


    public static Hotel getHotelByName(String hotelName){
        Hotel obj = null;
        String query = "SELECT * FROM hotel WHERE name = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,hotelName);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                obj =  new Hotel(rs.getInt("id"),rs.getString("name"),rs.getString("address"),
                        rs.getString("email"),rs.getString("phoneNumber"),rs.getString("boardingHouse"),
                        rs.getString("city"),rs.getString("region"), rs.getString("star"), rs.getArray("serviceSpec"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    public static Hotel getFetch(int id){
        Hotel obj = null;
        String query = "SELECT * FROM hotel WHERE id = "+id;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()){
                obj =  new Hotel(rs.getInt("id"),rs.getString("name"),rs.getString("address"),
                        rs.getString("email"),rs.getString("phoneNumber"),rs.getString("boardingHouse"),
                        rs.getString("city"),rs.getString("region"), rs.getString("star"), rs.getArray("serviceSpec"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    public static boolean add(String name, String address, String email, String phone_num, String boarding_house, ArrayList<String> serviceSpec, String city, String region, String star) {
        String sql = "INSERT INTO hotel ( name,  address,  email,  phoneNumber,  boardingHouse, city ,  region,  star,  serviceSpec) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            Array array = DBConnector.getInstance().createArrayOf("VARCHAR", serviceSpec.toArray());
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(sql);
            pr.setString(1, name);
            pr.setString(2, address);
            pr.setString(3, email);
            pr.setString(4, phone_num);
            pr.setString(5, boarding_house);
            pr.setString(6, city);
            pr.setString(7, region);
            pr.setString(8, star);
            pr.setArray(9, array);

            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static ArrayList<Hotel> getList() {
        ArrayList<Hotel> hotelList = new ArrayList<>();
        Hotel obj;
        String sql = "SELECT * FROM hotel";
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                obj = new Hotel(rs.getInt("id"), rs.getString("name"), rs.getString("address"), rs.getString("email"),
                        rs.getString("phoneNumber"), rs.getString("boardingHouse"),
                        rs.getString("city"), rs.getString("region"), rs.getString("star"),
                        rs.getArray("serviceSpec"));
                hotelList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotelList;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getBoardingHouse() {
        return boardingHouse;
    }

    public void setBoardingHouse(String boardingHouse) {
        this.boardingHouse = boardingHouse;
    }

    public Array getServiceSpec() {
        return serviceSpec;
    }

    public void setServiceSpec(Array serviceSpec) {
        this.serviceSpec = serviceSpec;
    }
}
