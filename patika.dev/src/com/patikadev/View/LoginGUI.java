package com.patikadev.View;


import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Model.Educator;
import com.patikadev.Model.Operator;
import com.patikadev.Model.Student;
import com.patikadev.Model.User;

import javax.swing.*;

public class LoginGUI extends JFrame{
    private JPanel wrapper;
    private JPanel wtop;
    private JPanel wbuttom;
    private JTextField fld_user_uname;
    private JButton btn_login;
    private JPasswordField fld_user_pass;
    private JButton btn_signin;

    public LoginGUI(){
        add(wrapper);
        setSize(400,400);
        setLocation(Helper.screenCenterPoint("x",getSize()),Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        btn_login.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_user_uname) || Helper.isFieldEmpty(fld_user_pass)){
                Helper.showMsg("fill");
            }else {
                User user = User.getFetch(fld_user_uname.getText(),fld_user_pass.getText());
                if (user == null) {
                    Helper.showMsg("Kullanıcı Bulunamadı!");
                }else {
                    switch (user.getType()){
                        case "operator":
                            OperatorGUI operatorGUI = new OperatorGUI((Operator) user);
                            break;
                        case "educator":
                            EducatorGUI educatorGUI = new EducatorGUI((Educator) user);
                            break;
                        case "student":
                            StudentGUI studentGUI = new StudentGUI((Student) user);
                            break;
                        default:
                            Helper.showMsg("Geçersiz kullanıcı türü");
                            break;
                    }
                    dispose();
                }
            }
        });
        btn_signin.addActionListener(e -> {
            SignInGui signInGui = new SignInGui();
            dispose();
        });
    }

}
