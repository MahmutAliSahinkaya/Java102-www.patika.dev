package com.rentACarPortal.View;



import com.rentACarPortal.Helper.Config;
import com.rentACarPortal.Helper.DBConnector;
import com.rentACarPortal.Helper.Helper;
import com.rentACarPortal.Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RezCompleteGUI extends JFrame{
    private JPanel wrapper;
    private JTextField fld_info_name;
    private JTextField fld_rez_note;
    private JTextField fld_info_phone;
    private JTextField fld_info_mail;
    private JButton btn_save;
    private ArrayList<JTextField> nameTexfieldList = new ArrayList<>();
    private ArrayList<JTextField> nationTextFieldList = new ArrayList<>();
    private ArrayList<JTextField> TCTextFieldList = new ArrayList<>();
    private ArrayList<JPanel> panelList = new ArrayList<>();





    public RezCompleteGUI(User user, int company_id, int vehicle_id, String vehicle_type, Date rentalDate, Date returnDate) {

        add(wrapper);
        setSize(1000,600);
        setLocation(Helper.screenCenterPoint("x",getSize()),Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        GridLayout myLayout = new GridLayout(5,2);
        setLayout(new FlowLayout());

        fld_info_mail.setPreferredSize(new Dimension(250,25));
        fld_info_name.setPreferredSize(new Dimension(250,25));
        fld_info_phone.setPreferredSize(new Dimension(250,25));
        fld_rez_note.setPreferredSize(new Dimension(250,25));

        setVisible(true);



        btn_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Boolean status = false;
                String contactName = fld_info_name.getText();
                String contactReznote = fld_rez_note.getText();
                String contactPhone = fld_info_phone.getText();
                String contactEmail = fld_info_mail.getText();

                if (Boolean.TRUE.equals(status)){
                    updateStock(vehicle_id);
                    Helper.showMsg("Rezervasyon Tamamlandı.");
                    dispose();
                    RezListGUI rezListGUI = new RezListGUI(user);
                }else {
                    Helper.showMsg("Bir hata oluştu.");
                }
            }
        });
    }

    public boolean addRez(String contactName,String contactReznote, String contactPhone, String contactEmail, int company_id, int vehicle_id,Date rentalDate, Date returnDate){
        String query = "INSERT INTO public.rez " + "(contact_name,contact_phone,contact_email,rez_note," +
                "company_id,vehicle_id,rental_date,return_date)" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,contactName);
            pr.setString(2,contactPhone);
            pr.setString(3,contactEmail);
            pr.setString(4,contactReznote);
            pr.setInt(5,company_id);
            pr.setInt(6,vehicle_id);
            pr.setDate(7,rentalDate);
            pr.setDate(8,returnDate);

            return pr.executeUpdate() != -1;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getStockSizeOfVehicle(int vehicle_id){
        int stock = -1;
        String query = "SELECT stock FROM public.vehicle WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,vehicle_id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                stock = rs.getInt("stock");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return stock;
    }

    public boolean updateStock(int vehicle_id){
        int stock = getStockSizeOfVehicle(vehicle_id);
        if (stock == -1) {
            return false;
        }
        int newStock = stock - 1;
        String query = "UPDATE public.vehicle SET stock = ? WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,newStock);
            pr.setInt(2,vehicle_id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
