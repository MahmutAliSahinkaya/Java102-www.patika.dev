package com.rentACarPortal.View;


import com.rentACarPortal.Helper.Config;
import com.rentACarPortal.Helper.Helper;
import com.rentACarPortal.Model.Company;
import com.rentACarPortal.Model.Vehicle;


import javax.swing.*;

public class VehicleAddGUI extends JFrame {
    private JPanel wrapper;
    private JComboBox cmbx_type;
    private JTextField fld_brand;
    private JTextField fld_stock;
    private JCheckBox chk_model;
    private JCheckBox chk_modelYear;
    private JCheckBox chk_automatic;
    private JButton btn_add;
    private JLabel lbl_company_name;
    private Company company;
    private final String[] vehicleTypeList = {"Binek", "Arazi", "Ticari"};


    public VehicleAddGUI(String companyName) {
        this.company = Company.getCompanyByName(companyName);
        add(wrapper);
        setSize(750, 500);
        int x = Helper.screenCenterPoint("x", getSize());
        int y = Helper.screenCenterPoint("y", getSize());
        setLocation(x, y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        loadVehicleTypeCombo();
        lbl_company_name.setText(companyName);
        btn_add.addActionListener(e -> {
            String vehicle_type = cmbx_type.getSelectedItem().toString();
            int stock = Integer.parseInt(fld_stock.getText());
            int company_id = company.getId();
            String brand = fld_brand.getText();
            String model = chk_model.getText();
            int modelYear = Integer.parseInt(chk_modelYear.getText());
            boolean automatic = chk_automatic.isSelected();

            if (Vehicle.isVehicleTypeAdded(vehicle_type, company_id) == 0) {
                if (Vehicle.add(vehicle_type, stock, company_id, Integer.parseInt(brand), model, modelYear, automatic)) {
                    Helper.showMsg("done");
                } else {
                    Helper.showMsg("error");
                }
            } else {
                Helper.showMsg("Bu firmaya ait " + vehicle_type + " tipinde araç daha önce eklenmiştir.");
            }
        });
    }

    public void loadVehicleTypeCombo() {
        cmbx_type.removeAllItems();
        for (String s : vehicleTypeList) {
            cmbx_type.addItem(s);
        }
    }
}
