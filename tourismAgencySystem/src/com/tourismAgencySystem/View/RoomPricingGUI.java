package com.tourismAgencySystem.View;



import com.tourismAgencySystem.Helper.Config;
import com.tourismAgencySystem.Helper.DBConnector;
import com.tourismAgencySystem.Helper.Helper;
import com.tourismAgencySystem.Model.Hotel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class RoomPricingGUI extends JFrame{
    private JPanel wrapper;
    private JTextField fld_ultra;
    private JTextField fld_all;
    private JTextField fld_breakfast;
    private JTextField fld_full;
    private JTextField fld_half;
    private JTextField fld_only_bed;
    private JTextField fld_with_alcohol;
    private JButton btn_save;
    private JLabel lbl_ultra;
    private JLabel lbl_all;
    private JLabel lbl_breakfast;
    private JLabel lbl_full;
    private JLabel lbl_half;
    private JLabel lbl_only_bed;
    private JLabel lbl_with_alchol;
    private Hotel hotel;

    public RoomPricingGUI(String hotelName){
        this.hotel = Hotel.getHotelByName(hotelName);
        add(wrapper);
        setSize(750,500);
        int x = Helper.screenCenterPoint("x",getSize());
        int y = Helper.screenCenterPoint("y",getSize());
        setLocation(x,y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        loadLblText();

        btn_save.addActionListener(e -> {
            int j = 0;
            String pension_type;
            int price;
            int hotel_id = hotel.getId();
            int pension_price[] = new int[Config.PENSION_TYPES.length];

            pension_price[j++] = Integer.parseInt(fld_ultra.getText());
            pension_price[j++] = Integer.parseInt(fld_all.getText());
            pension_price[j++] = Integer.parseInt(fld_breakfast.getText());
            pension_price[j++] = Integer.parseInt(fld_full.getText());
            pension_price[j++] = Integer.parseInt(fld_half.getText());
            pension_price[j++] = Integer.parseInt(fld_only_bed.getText());
            pension_price[j++] = Integer.parseInt(fld_with_alcohol.getText());
            if (setRoomPrice(hotel_id,pension_price)){
                Helper.showMsg("done");
                dispose();
            }else{
                Helper.showMsg("error");
            }
        });
    }

    private void loadLblText(){
        int i = 0;
        lbl_ultra.setText(Config.PENSION_TYPES[i++] + ":");
        lbl_all.setText(Config.PENSION_TYPES[i++] + ":");
        lbl_breakfast.setText(Config.PENSION_TYPES[i++] + ":");
        lbl_full.setText(Config.PENSION_TYPES[i++] + ":");
        lbl_half.setText(Config.PENSION_TYPES[i++] + ":");
        lbl_only_bed.setText(Config.PENSION_TYPES[i++] + ":");
        lbl_with_alchol.setText(Config.PENSION_TYPES[i++] + ":");
    }

    private boolean setRoomPrice(int hotel_id, int[] pension_price){
        String query = "INSERT INTO public.room_price (pension_type,hotel_id,price) VALUES (?,?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            for (int i = 0; i < Config.PENSION_TYPES.length; i++) {
                pr.setObject(1,Config.PENSION_TYPES[i], Types.OTHER);
                pr.setInt(2,hotel_id);
                pr.setInt(3,pension_price[i]);
                pr.addBatch();
            }
            pr.executeBatch();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
}
