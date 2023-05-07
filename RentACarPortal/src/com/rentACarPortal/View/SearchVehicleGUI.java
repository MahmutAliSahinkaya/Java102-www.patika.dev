package com.rentACarPortal.View;


import com.rentACarPortal.Helper.Config;
import com.rentACarPortal.Helper.DBConnector;
import com.rentACarPortal.Helper.Helper;
import com.rentACarPortal.Model.Company;
import com.rentACarPortal.Model.User;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SearchVehicleGUI extends  JFrame{
    private JPanel wrapper;
    private JTextField fld_location;
    private JButton btn_search;
    private JTextField fld_rental_date;
    private JTextField fld_return_date;
    private JButton btn_back;
    private String rentalDateText = "";
    private String returnDateText = "";
    private boolean statusRental = true;
    private boolean statusReturn = true;
    private User user;

    private ArrayList<Integer> companyIdList;
    private ArrayList<Integer> vehicleIdList;

    public SearchVehicleGUI(User user){
        this.user = user;
        add(wrapper);
        setSize(700,500);
        setLocation(Helper.screenCenterPoint("x",getSize()),Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);


        fld_rental_date.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                rentalDateText = String.valueOf(rentalDateText);
                if (statusRental){
                    try {
                        rentalDateText = rentalDateText.substring(0, rentalDateText.length() -1);
                    }catch (StringIndexOutOfBoundsException exception){
                        exception.getStackTrace();
                    }

                    statusRental = false;
                }else{
                    int length = fld_rental_date.getText().length();
                    if (length == 7 || length == 4){
                        rentalDateText = fld_rental_date.getText();
                        rentalDateText += "-";
                        fld_rental_date.setText(rentalDateText);

                    }
                }

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
                    statusRental = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        fld_return_date.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (statusReturn){
                    try {
                        returnDateText = returnDateText.substring(0, returnDateText.length() -1);
                    }catch (StringIndexOutOfBoundsException exception){
                        exception.getStackTrace();
                    }
                    statusReturn = false;
                }else{
                    int length = fld_return_date.getText().length();
                    if (length == 7 || length == 4){
                        returnDateText = fld_return_date.getText();
                        returnDateText += "-";
                        fld_return_date.setText(returnDateText);
                    }
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
                    statusReturn = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        btn_search.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_location) || Helper.isFieldEmpty(fld_rental_date) || Helper.isFieldEmpty(fld_return_date)){
                Helper.showMsg("fill");
            }else {
                String location = fld_location.getText();
                Date rentalDate = Date.valueOf(fld_rental_date.getText());
                Date returnDate = Date.valueOf(fld_return_date.getText());
                search(location,rentalDate,returnDate);
            }


        });
        btn_back.addActionListener(e -> {
            RezListGUI rezListGUI = new RezListGUI(user);
            dispose();
        });
    }

    private void search(String location,Date rentalDate,Date returnDate){
        ArrayList<Integer> companies = searchCompany(searchQuery(location,rentalDate,returnDate));
        for (int companyId:companies) {
            if (!isVehicleAvailable(companyId)){
                Helper.showMsg("İstenilen tipte uygun araç bulunamadı.");
            }else{
                ArrayList<Date> dates = getRentalDateOutDate(companyId);
                if (!isDateAvailable(dates,rentalDate,returnDate)){
                    Helper.showMsg("Girilen tarihlerde uygun aracı olan firma bulunamadı.");
                }else{
                    ArrayList<Integer> vehicles = getVehicleIdList();
                    Company company = Company.getFetch(companyId);
                    dispose();
                    SearchOutputGUI searchOutputGUI = new SearchOutputGUI(user,company,vehicles,rentalDate,returnDate);
                }
            }

        }

    }
    private ArrayList<Integer> searchCompany(String query){
        ArrayList<Integer> searchCompanyList = new ArrayList<>();
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                searchCompanyList.add(rs.getInt("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return searchCompanyList;
    }

    private boolean isVehicleAvailable(int company_id){
        int stock;
        boolean status = false;
        String query = "SELECT id,stock FROM public.vehicle WHERE company_id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,company_id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                stock = rs.getInt("stock");
                if (stock>0){
                    status = true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    }

    private ArrayList<Date> getRentalDateOutDate(int company_id){
        String query = "SELECT rental_date,return_date FROM public.rez WHERE company_id = ?";
        ArrayList<Date> dates = new ArrayList<>();
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,company_id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                Date rental_date = rs.getDate("rental_date");
                Date return_date = rs.getDate("return_date");
                dates.add(rental_date);
                dates.add(return_date);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dates;
    }

    private boolean isDateAvailable(ArrayList<Date> dates,Date rentalDate,Date returnDate){
        try {
            Date rental_date = dates.get(0);
            Date return_date = dates.get(1);
            return return_date.before(rentalDate);
        }catch (IndexOutOfBoundsException e){
            return true;
        }
    }

    private String searchQuery(String location,Date rentalDate,Date returnDate){
        String query = "SELECT id,name FROM public.company WHERE name ILIKE '%{{name}}%' OR city ILIKE '%{{city}}%'";
        query = query.replace("{{name}}",location);
        query = query.replace("{{city}}",location);
        return query;
    }


    private ArrayList<Integer> getVehicleIdList(){
        return vehicleIdList;
    }


}
