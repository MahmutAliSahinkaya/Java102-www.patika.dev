package com.rentACarPortal.View;


import com.rentACarPortal.Helper.Config;
import com.rentACarPortal.Helper.DBConnector;
import com.rentACarPortal.Helper.Helper;
import com.rentACarPortal.Model.Company;
import com.rentACarPortal.Model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class SearchOutputGUI extends JFrame {
    private JPanel wrapper;
    private JTextField fld_address;
    private JTextField fld_phone;
    private JTextField fld_city;
    private JTextField fld_rentalDate;
    private JTextField fld_returnDate;
    private JButton btn_rez;

    private JLabel lbl_vehicle_service;
    private JLabel lbl_brand;
    private JLabel lbl_model;
    private JLabel lbl_automatic;
    private JLabel lbl_modelYear;
    private JLabel lbl_vehicleType;
    private JLabel lbl_companyName;
    private JLabel lbl_rezDates;
    private JButton btn_back;

    private int vehicle_id;

    public SearchOutputGUI(User user, Company company, ArrayList<Integer> vehicles, Date rentalDate, Date returnDate) {
        add(wrapper);
        setSize(1090, 500);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);
        fld_address.setEditable(false);
        fld_rentalDate.setEditable(false);
        fld_returnDate.setEditable(false);
        fld_city.setEditable(false);
        fld_phone.setEditable(false);

        int company_id = company.getId();
        int rezDate = calculateRezDate(rentalDate, returnDate);
        String vehicle_type = getVehicleType(company.getId());

        lbl_companyName.setText(company.getName());
        fld_address.setText(company.getAddress());
        fld_rentalDate.setText(String.valueOf(rentalDate));
        fld_returnDate.setText(String.valueOf(returnDate));
        fld_city.setText(company.getCity());
        fld_phone.setText(company.getPhoneNumber());

        lbl_vehicleType.setText(vehicle_type + " Araç");
        lbl_rezDates.setText(String.valueOf(rezDate) + " Günlük İçin");
        loadVehicleSpec(company.getServiceSpec());
        getVehicleInfo(vehicle_id);


        btn_rez.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RezCompleteGUI rezCompleteGUI = new RezCompleteGUI(user, company_id, vehicle_id, vehicle_type, rentalDate, returnDate);
            }
        });
        btn_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchVehicleGUI searchVehicleGUI = new SearchVehicleGUI(user);
                dispose();
            }
        });
    }


    private int calculateRezDate(Date rentalDate, Date returnDate) {
        String rentalDateStr = String.valueOf(rentalDate);
        String returnDateStr = String.valueOf(returnDate);
        String[] partsOfRentalDate = rentalDateStr.split("-");
        String[] partsOfReturnDate = returnDateStr.split("-");

        int yearToDay = (Integer.parseInt(partsOfReturnDate[0]) - Integer.parseInt(partsOfRentalDate[0])) * 365;
        System.out.println(yearToDay);
        int monthToDay = (Integer.parseInt(partsOfReturnDate[1]) - Integer.parseInt(partsOfRentalDate[1])) * 30;
        System.out.println(monthToDay);
        int day = (Integer.parseInt(partsOfReturnDate[2]) - Integer.parseInt(partsOfRentalDate[2]));
        System.out.println(day);
        return yearToDay + monthToDay + day;

    }

    private void getVehicleInfo(int vehicle_id) {
        String brand = "";
        String model = "";
        int modelYear = 0;
        boolean automatic = true;
        String query = "SELECT brand,model,modelYear,automatic FROM public.vehicle WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, vehicle_id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                brand = rs.getString("brand");
                model = rs.getString("model");
                modelYear = rs.getInt("modelYear");
                automatic = rs.getBoolean("automatic");
            }
            loadVehicleInfo(brand, model, modelYear, automatic);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadVehicleInfo(String brand, String model, int modelYear, boolean automatic) {
        lbl_brand.setText("Aracın Markası : " + brand);
        lbl_model.setText("Aracın Modeli : " + model);
        lbl_modelYear.setText("Aracın Model Yılı : " + modelYear);

        if (automatic) {
            lbl_automatic.setText("Araç : Otomatik vites");
        } else {
            lbl_automatic.setText("Araç : Manuel vites");
        }

    }

    private void loadVehicleSpec(Array service_spec) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] tempArr;
        try {
            tempArr = (String[]) service_spec.getArray();
            for (String s : tempArr) {
                stringBuilder.append("*").append(s).append("       ");
            }
            lbl_vehicle_service.setText(stringBuilder.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getPensionList(int company_id) {
        String query = "SELECT price FROM vehicle_price WHERE company_id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, company_id);
            ResultSet rs = pr.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    private String getVehicleType(int company_id) {

        String query = "SELECT id,vehicle_type,brand FROM public.vehicle WHERE company_id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, company_id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                if (rs.getInt("brand") >= 1 && rs.getInt("brand") <= 3) {
                    vehicle_id = rs.getInt("id");
                    return rs.getString("vehicle_type");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
