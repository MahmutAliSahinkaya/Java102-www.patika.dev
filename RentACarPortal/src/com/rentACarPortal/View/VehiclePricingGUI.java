package com.rentACarPortal.View;



import com.rentACarPortal.Helper.Config;
import com.rentACarPortal.Helper.DBConnector;
import com.rentACarPortal.Helper.Helper;
import com.rentACarPortal.Model.Company;


import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class VehiclePricingGUI extends JFrame{
    private JPanel wrapper;
    private JTextField fld_full;
    private JTextField fld_half;
    private JButton btn_save;
    private JLabel lbl_full;
    private JLabel lbl_half;
    private Company company;

    public VehiclePricingGUI(String companyName){
        this.company = Company.getCompanyByName(companyName);
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
            String package_type;
            int price;
            int company_id = company.getId();
            int package_price[] = new int[Config.PACKAGE_TYPES.length];

            package_price[j++] = Integer.parseInt(fld_full.getText());
            package_price[j++] = Integer.parseInt(fld_half.getText());

            if (setVehiclePrice(company_id,package_price)){
                Helper.showMsg("done");
                dispose();
            }else{
                Helper.showMsg("error");
            }
        });
    }

    private void loadLblText(){
        int i = 0;
        lbl_full.setText(Config.PACKAGE_TYPES[i++] + ":");
        lbl_half.setText(Config.PACKAGE_TYPES[i++] + ":");
    }

    private boolean setVehiclePrice(int company_id, int[] package_price){
        String query = "INSERT INTO public.vehicle_price (package_type,company_id,price) VALUES (?,?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            for (int i = 0; i < Config.PACKAGE_TYPES.length; i++) {
                pr.setObject(1,Config.PACKAGE_TYPES[i], Types.OTHER);
                pr.setInt(2,company_id);
                pr.setInt(3,package_price[i]);
                pr.addBatch();
            }
            pr.executeBatch();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
}
