package com.rentACarPortal.View;


import com.rentACarPortal.Helper.Config;
import com.rentACarPortal.Helper.Helper;
import com.rentACarPortal.Model.Company;
import com.rentACarPortal.Model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class CompanyAddGUI extends JFrame {
    private JPanel wrapper;
    private JButton btn_logout;
    private JTextField fld_phone;
    private JTextField fld_city;
    private JCheckBox chkBx_vehicleService;
    private JTextArea fld_adres;
    private JTextField fld_email;
    private JTextField fld_name;
    private JButton btn_add;
    private JLabel lbl_welcome;
    private Company company;
    private final ArrayList<String> serviceSpecList = new ArrayList<>();

    public CompanyAddGUI(User user) {
        add(wrapper);
        setSize(700, 600);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        fld_adres.setWrapStyleWord(true);
        fld_adres.setLineWrap(true);
        lbl_welcome.setText("Hoşgeldiniz, " + user.getFirstName() + " " + user.getLastName());


        chkBx_vehicleService.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    serviceSpecList.add(chkBx_vehicleService.getText());
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    serviceSpecList.remove(chkBx_vehicleService.getText());
                }
            }
        });
        btn_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = fld_name.getText();
                String address = fld_adres.getText();
                String city = fld_city.getText();
                String email = fld_email.getText();
                String phone = fld_phone.getText();
                System.out.println(serviceSpecList);

                if (Helper.isFieldEmpty(fld_name) || Helper.isFieldEmpty(fld_email) || Helper.isFieldEmpty(fld_city) || Helper.isFieldEmpty(fld_phone)) {
                    Helper.showMsg("fill");
                } else {
                    if (Company.add(name, address, email, phone, serviceSpecList, city)) {
                        Helper.showMsg("Firma Başarıyla Eklendi");
                    } else {
                        Helper.showMsg("error");
                    }
                }
            }
        });
        btn_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CompanyGUI companyGUI = new CompanyGUI(user);
                dispose();
            }
        });
    }

}
