package com.rentACarPortal.View;

import com.rentACarPortal.Helper.Config;
import com.rentACarPortal.Helper.DBConnector;
import com.rentACarPortal.Helper.Helper;
import com.rentACarPortal.Model.User;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class SignInGUI extends JFrame {
    private JTextField fld_uname;
    private JTextField fld_pass;
    private JTextField fld_pass_again;
    private JPanel wrapper;
    private JTextField fld_last_name;
    private JTextField fld_first_name;
    private JTextField fld_email;
    private JButton btn_signin;

    public SignInGUI(){
        add(wrapper);
        setSize(600,500);
        setLocation(Helper.screenCenterPoint("x",getSize()),Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);
        btn_signin.addActionListener(e -> {
            String email = fld_email.getText();
            String uname = fld_uname.getText();
            String first_name = fld_first_name.getText();
            String last_name = fld_last_name.getText();
            String pass = fld_pass.getText();
            String pass_again = fld_pass_again.getText();
            String type = "operator";

            if (Helper.isFieldEmpty(fld_email) || Helper.isFieldEmpty(fld_uname) || Helper.isFieldEmpty(fld_first_name) || Helper.isFieldEmpty(fld_last_name) || Helper.isFieldEmpty(fld_pass) || Helper.isFieldEmpty(fld_pass_again)){
                Helper.showMsg("fill");
            }else{
                if (isEmailValid(email) && isPasswordsMatch(pass,pass_again)){
                    if (addNewUser(first_name,last_name,uname,email,pass,type)){
                        Helper.showMsg("Kullanıcı başarıyla eklendi.");
                        LoginGUI loginGUI = new LoginGUI();
                        dispose();
                    }else {
                        Helper.showMsg("error");
                    }

                }
            }


        });
    }

    private boolean isEmailValid(String mail){
        if (mail.contains("@gmail.com") || mail.contains("@hotmail.com") || mail.contains("@icloud.com") || mail.contains("@yahoo.com") || mail.contains("@yandex.com") || mail.contains("@outlook.com")){
            return true;
        }else {
            Helper.showMsg("Lütfen geçerli bir e-posta girin.");
            return false;
        }
    }
    private boolean isPasswordsMatch(String pass, String pass_again){
        if (!(pass.equals(pass_again))){
            Helper.showMsg("Şifreler aynı değil.");
            return false;
        }
        return true;
    }

    private boolean addNewUser(String first_name, String last_name, String uname, String email, String pass,String type){
        String query = "INSERT INTO public.user (first_name,last_name,uname,email,pass,type) VALUES (?,?,?,?,?,?)";

        User findUser = User.getFetch(uname, pass);
        if (findUser != null){
            Helper.showMsg("Bu kullancı adı alınmış. Lütfen farklı bir kullanıcı giriniz.");
            return false;
        }
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,first_name);
            pr.setString(2,last_name);
            pr.setString(3,uname);
            pr.setString(4,email);
            pr.setString(5,pass);
            pr.setObject(6,type, Types.OTHER);


            return pr.executeUpdate() != -1;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;

    }

}
