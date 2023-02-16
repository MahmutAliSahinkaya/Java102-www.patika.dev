package com.tourismAgencySystem.View;



import com.tourismAgencySystem.Helper.Config;
import com.tourismAgencySystem.Helper.Helper;
import com.tourismAgencySystem.Model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LoginGUI extends JFrame{
    private JPanel wrapper;
    private JTextField fld_uname_email;
    private JTextField fld_pass;
    private JButton btn_login;
    private JButton btn_signin;

    public LoginGUI(){
        add(wrapper);
        setSize(750,500);
        setLocation(Helper.screenCenterPoint("x",getSize()),Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        fld_pass.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    login();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        btn_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        btn_signin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignInGUI signInGui = new SignInGUI();
                dispose();
            }
        });


    }
    private void login(){
        if (Helper.isFieldEmpty(fld_uname_email) || Helper.isFieldEmpty(fld_pass)){
            Helper.showMsg("fill");
        }else{
            User user = User.getFetch(fld_uname_email.getText(),fld_pass.getText());
            if (user == null) {
                Helper.showMsg("Kullanıcı Bulunamadı");
            }else{
                switch (user.getType()) {
                    case "operator" -> {
                        //rezervasyon listesi ondan sonra oda arama
                        RezListGUI rezListGUI = new RezListGUI(user);
                        dispose();
                    }
                    case "admin" -> {
                        HotelGUI hotelGUI = new HotelGUI(user);
                        dispose();
                    }
                }

            }
        }
    }
}
