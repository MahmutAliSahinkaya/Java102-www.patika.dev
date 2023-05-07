package com.rentACarPortal.Model;

import com.rentACarPortal.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Vehicle {
    private int id;
    private String vehicleType;
    private String brand;
    private int stock;
    private String model;
    private int modelYear;
    private boolean automatic;

    private Company company;

    public Vehicle(int id, String vehicleType, String brand, int stock, String model, int modelYear, boolean automatic) {
        this.id = id;
        this.vehicleType = vehicleType;
        this.brand = brand;
        this.stock = stock;
        this.model = model;
        this.modelYear = modelYear;
        this.automatic = automatic;
    }

    public static int isVehicleTypeAdded(String vehicleType, int companyId) {
        String query = "SELECT id FROM vehicle WHERE vehicleType = ? AND company_id = ?";
        int id = 0;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, vehicleType);
            pr.setInt(2, companyId);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public static boolean add(String vehicle_type, int stock, int company_id, int brand, String model, int modelYear, boolean automatic) {
        String query = "INSERT INTO vehicle (vehicleType, stock, company_id, brand, model, minibar, automatic) VALUES (?,?,?,?,?,?,?)";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, vehicle_type);
            pr.setInt(2, stock);
            pr.setInt(3, company_id);
            pr.setInt(4, brand);
            pr.setString(5, model);
            pr.setInt(6, modelYear);
            pr.setBoolean(7, automatic);

            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getCompanyIdByVehicleId(int id) {
        String query = "SELECT course_id from vehicle WHERE id = " + id;
        int result = 0;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            result = rs.getInt("id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public int getId() {
        return id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public boolean isAutomatic() {
        return automatic;
    }

    public void setAutomatic(boolean automatic) {
        this.automatic = automatic;
    }
}

