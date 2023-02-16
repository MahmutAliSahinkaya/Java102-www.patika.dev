package com.tourismAgencySystem.View;


import com.tourismAgencySystem.Helper.Config;
import com.tourismAgencySystem.Helper.DBConnector;
import com.tourismAgencySystem.Helper.Helper;
import com.tourismAgencySystem.Model.Hotel;
import com.tourismAgencySystem.Model.User;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SearchRoomGUI extends  JFrame{
    private JPanel wrapper;
    private JTextField fld_location;
    private JSpinner spn_adult;
    private JSpinner spn_children;
    private JButton btn_search;
    private JTextField fld_check_in;
    private JTextField fld_check_out;
    private JButton btn_back;
    private String checkInText = "";
    private String checkOutText = "";
    private boolean statusIn = true;
    private boolean statusOut = true;
    private User user;

    private ArrayList<Integer> hotelIdList;
    private ArrayList<Integer> roomIdList;

    public SearchRoomGUI(User user){
        this.user = user;
        add(wrapper);
        setSize(700,500);
        setLocation(Helper.screenCenterPoint("x",getSize()),Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);
        int min = 0;
        int max = 100;
        int step = 1;
        int initValue = 0;

        SpinnerModel spinnerModelAdult = new SpinnerNumberModel(initValue, min, max, step);
        SpinnerModel spinnerModelChild = new SpinnerNumberModel(initValue, min, max, step);

        spn_adult.setModel(spinnerModelAdult);
        spn_children.setModel(spinnerModelChild);

        guestNumController();


        fld_check_in.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                checkInText = String.valueOf(checkInText);
                if (statusIn){
                    try {
                        checkInText = checkInText.substring(0,checkInText.length() -1);
                    }catch (StringIndexOutOfBoundsException exception){
                        exception.getStackTrace();
                    }

                    statusIn = false;
                }else{
                    int length = fld_check_in.getText().length();
                    if (length == 7 || length == 4){
                        checkInText = fld_check_in.getText();
                        checkInText += "-";
                        fld_check_in.setText(checkInText);

                    }
                }

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
                    statusIn = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        fld_check_out.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (statusOut){
                    try {
                        checkOutText = checkOutText.substring(0,checkOutText.length() -1);
                    }catch (StringIndexOutOfBoundsException exception){
                        exception.getStackTrace();
                    }
                    statusOut = false;
                }else{
                    int length = fld_check_out.getText().length();
                    if (length == 7 || length == 4){
                        checkOutText = fld_check_out.getText();
                        checkOutText += "-";
                        fld_check_out.setText(checkOutText);
                    }
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
                    statusOut = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        btn_search.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_location) || Helper.isFieldEmpty(fld_check_in) || Helper.isFieldEmpty(fld_check_out)){
                Helper.showMsg("fill");
            }else {
                String location = fld_location.getText();
                Date checkIn = Date.valueOf(fld_check_in.getText());
                Date checkOut = Date.valueOf(fld_check_out.getText());
                int numAdult = Integer.parseInt(spn_adult.getValue().toString());
                int numChild = Integer.parseInt(spn_children.getValue().toString());
                search(location,checkIn,checkOut,numAdult,numChild);
            }


        });
        btn_back.addActionListener(e -> {
            RezListGUI rezListGUI = new RezListGUI(user);
            dispose();
        });
    }

    private void search(String location,Date checkIn,Date checkOut, int numAdult,int numChild){
        ArrayList<Integer> hotels = searchHotel(searchQuery(location,checkIn,checkOut,numAdult,numChild));
        for (int hotelId:hotels) {
            if (!isRoomAvailable(hotelId)){
                Helper.showMsg("İstenilen tipte uygun oda bulunamadı.");
            }else{
                ArrayList<Date> dates = getCheckInOutDate(hotelId);
                if (!isDateAvailable(dates,checkIn,checkOut)){
                    Helper.showMsg("Girilen tarihlerde uygun otel bulunamadı.");
                }else{
                    ArrayList<Integer> rooms = getRoomIdList();
                    Hotel hotel = Hotel.getFetch(hotelId);
                    dispose();
                    SearchOutputGUI searchOutputGUI = new SearchOutputGUI(user,hotel,rooms,checkIn,checkOut,numAdult,numChild);
                }
            }

        }

    }
    private ArrayList<Integer> searchHotel(String query){
        ArrayList<Integer> searchHotelList = new ArrayList<>();
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                searchHotelList.add(rs.getInt("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return searchHotelList;
    }

    private boolean isRoomAvailable(int hotel_id){
        int stock;
        boolean status = false;
        String query = "SELECT id,stock FROM public.room WHERE hotel_id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotel_id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                stock = rs.getInt("stock");
                if (stock>0){
                    //room id leri tutmak lazım sanki. Hangi oda tipinin uygun olduğunu bilemeyiz sonra.
                    status = true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    }

    private ArrayList<Date> getCheckInOutDate(int hotel_id){
        String query = "SELECT check_in,check_out FROM public.rez WHERE hotel_id = ?";
        ArrayList<Date> dates = new ArrayList<>();
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotel_id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                Date check_in = rs.getDate("check_in");
                Date check_out = rs.getDate("check_out");
                dates.add(check_in);
                dates.add(check_out);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dates;
    }

    private boolean isDateAvailable(ArrayList<Date> dates,Date checkIn,Date checkOut){
        try {
            Date check_in = dates.get(0);
            Date check_out = dates.get(1);
            return check_out.before(checkIn);
        }catch (IndexOutOfBoundsException e){
            return true;
        }
    }

    private String searchQuery(String location,Date checkIn,Date checkOut,int numAdult,int numChild){
        String query = "SELECT id,name FROM public.hotel WHERE name ILIKE '%{{name}}%' OR city ILIKE '%{{city}}%' OR region ILIKE '%{{region}}%'";
        query = query.replace("{{name}}",location);
        query = query.replace("{{city}}",location);
        query = query.replace("{{region}}",location);
        return query;
    }

    private void guestNumController(){
        int child;
        int adult;
        try {
            adult = (int) spn_adult.getValue();
            child = (int) spn_children.getValue();
            if (adult < 0){
                spn_adult.setValue(adult * -1);
            }
            if (child < 0){
                spn_children.setValue(child * -1);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private ArrayList<Integer> getRoomIdList(){
        return roomIdList;
    }


}
