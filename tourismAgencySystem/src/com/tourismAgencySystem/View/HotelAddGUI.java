package com.tourismAgencySystem.View;


import com.tourismAgencySystem.Helper.Config;
import com.tourismAgencySystem.Helper.Helper;
import com.tourismAgencySystem.Model.Hotel;
import com.tourismAgencySystem.Model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class HotelAddGUI extends JFrame {
    private JPanel wrapper;
    private JButton btn_logout;
    private JTextField fld_phone;
    private JTextField fld_city;
    private JTextField fld_region;
    private JCheckBox chkBx_Ultra;
    private JCheckBox chkBx_All;
    private JCheckBox chkBx_freeWiFi;
    private JCheckBox chkBx_pool;
    private JCheckBox chkBx_hotelConcierge;
    private JCheckBox chkBx_spa;
    private JCheckBox chkBx_fitness;
    private JCheckBox chkBx_rooService;
    private JTextArea fld_adres;
    private JComboBox cmb_star;
    private JTextField fld_email;
    private JTextField fld_name;
    private JButton btn_add;
    private JCheckBox chckBx_freePark;
    private JComboBox cmb_boardinHouse;
    private JLabel lbl_welcome;
    private Hotel hotel;
    private final ArrayList<String> serviceSpecList = new ArrayList<>();

    public HotelAddGUI(User user) {
        add(wrapper);
        setSize(700, 600);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        fld_adres.setWrapStyleWord(true);
        fld_adres.setLineWrap(true);
        loadComboStar();
        loadComboPension();
        lbl_welcome.setText("Hoşgeldiniz, " + user.getFirstName() + " " + user.getLastName());

        chckBx_freePark.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    serviceSpecList.add(chckBx_freePark.getText());
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    serviceSpecList.remove(chckBx_freePark.getText());
                }
            }
        });
        chkBx_freeWiFi.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    serviceSpecList.add(chkBx_freeWiFi.getText());
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    serviceSpecList.remove(chkBx_freeWiFi.getText());
                }
            }
        });
        chkBx_pool.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    serviceSpecList.add(chkBx_pool.getText());
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    serviceSpecList.remove(chkBx_pool.getText());
                }
            }
        });
        chkBx_hotelConcierge.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    serviceSpecList.add(chkBx_hotelConcierge.getText());
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    serviceSpecList.remove(chkBx_hotelConcierge.getText());
                }
            }
        });
        chkBx_spa.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    serviceSpecList.add(chkBx_spa.getText());
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    serviceSpecList.remove(chkBx_spa.getText());
                }
            }
        });
        chkBx_fitness.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    serviceSpecList.add(chkBx_fitness.getText());
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    serviceSpecList.remove(chkBx_fitness.getText());
                }
            }
        });
        chkBx_rooService.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    serviceSpecList.add(chkBx_rooService.getText());
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    serviceSpecList.remove(chkBx_rooService.getText());
                }
            }
        });
        btn_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = fld_name.getText();
                String star = cmb_star.getSelectedItem().toString();
                String address = fld_adres.getText();
                String city = fld_city.getText();
                String region = fld_region.getText();
                String email = fld_email.getText();
                String phone = fld_phone.getText();

                String boardingHouse = cmb_boardinHouse.getSelectedItem().toString();
                System.out.println(serviceSpecList);

                if (Helper.isFieldEmpty(fld_name) || Helper.isFieldEmpty(fld_email) || Helper.isFieldEmpty(fld_city) || Helper.isFieldEmpty(fld_phone) || Helper.isFieldEmpty(fld_region)) {
                    Helper.showMsg("fill");
                } else {
                    if (Hotel.add(name, address, email, phone, boardingHouse, serviceSpecList, city, region, star)) {
                        Helper.showMsg("Hotel Başarıyla Eklendi");
                    } else {
                        Helper.showMsg("error");
                    }
                }
            }
        });
        btn_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HotelGUI hotelGUI = new HotelGUI(user);
                dispose();
            }
        });
    }

    private void loadComboStar() {
        cmb_star.removeAllItems();
        for (String s : Config.START_LIST) {
            cmb_star.addItem(s);
        }
    }

    private void loadComboPension() {
        cmb_boardinHouse.removeAllItems();
        for (String s : Config.PENSION_TYPES) {
            cmb_boardinHouse.addItem(s);
        }
    }
}
