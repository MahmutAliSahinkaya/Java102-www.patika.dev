package com.tourismAgencySystem.View;


import com.tourismAgencySystem.Helper.Config;
import com.tourismAgencySystem.Helper.Helper;
import com.tourismAgencySystem.Model.Hotel;
import com.tourismAgencySystem.Model.Room;

import javax.swing.*;

public class RoomAddGUI extends JFrame {
    private JPanel wrapper;
    private JComboBox cmbx_type;
    private JTextField fld_bed;
    private JTextField fld_stock;
    private JCheckBox chk_tv;
    private JCheckBox chk_minibar;
    private JCheckBox chk_safe;
    private JButton btn_add;
    private JLabel lbl_hotel_name;
    private Hotel hotel;
    private final String[] roomTypeList = {"Single", "Double", "Suit"};


    public RoomAddGUI(String hotelName) {//Sadece otel adı da alabalir.yada olmayablir.
        this.hotel = Hotel.getHotelByName(hotelName);
        add(wrapper);
        setSize(750, 500);
        int x = Helper.screenCenterPoint("x", getSize());
        int y = Helper.screenCenterPoint("y", getSize());
        setLocation(x, y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        loadRoomTypeCombo();
        lbl_hotel_name.setText(hotelName);
        btn_add.addActionListener(e -> {
            String room_type = cmbx_type.getSelectedItem().toString();
            int stock = Integer.parseInt(fld_stock.getText());
            int hotel_id = hotel.getId();
            int bed_num = Integer.parseInt(fld_bed.getText());
            boolean tv = chk_tv.isSelected();
            boolean minibar = chk_minibar.isSelected();
            boolean safe = chk_safe.isSelected();

            if (Room.isRoomTypeAdded(room_type, hotel_id) == 0) {
                if (Room.add(room_type, stock, hotel_id, bed_num, tv, minibar, safe)) {
                    Helper.showMsg("done");
                } else {
                    Helper.showMsg("error");
                }
            } else {
                Helper.showMsg("Bu otele ait " + room_type + " tipinde oda daha önce eklenmiştir.");
            }
        });
    }

    public void loadRoomTypeCombo() {
        cmbx_type.removeAllItems();
        for (String s : roomTypeList) {
            cmbx_type.addItem(s);
        }
    }
}
