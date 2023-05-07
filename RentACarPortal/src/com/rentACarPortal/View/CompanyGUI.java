package com.rentACarPortal.View;



import com.rentACarPortal.Helper.Config;
import com.rentACarPortal.Helper.Helper;
import com.rentACarPortal.Model.Company;
import com.rentACarPortal.Model.User;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Array;
import java.sql.SQLException;

public class CompanyGUI extends JFrame{
    private JPanel wrapper;
    private JTable tbl_company_list;
    private JButton btn_back;
    private JButton btn_vehicle_add;
    private JLabel lbl_welcome;
    private JButton btn_companyAdd;
    private JButton btn_vehicle_price;
    private DefaultTableModel mdl_company_list;
    private Object[] row_company_list;
    private User user;
    private String companyName;


    public CompanyGUI(User user){
        this.user = user;
        add(wrapper);
        setSize(1000,500);
        int x = Helper.screenCenterPoint("x",getSize());
        int y = Helper.screenCenterPoint("y",getSize());
        setLocation(x,y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        lbl_welcome.setText("Hosgeldiniz " + user.getFirstName() + " " + user.getLastName());

        //Model user list
        mdl_company_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if(column == 0){
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_user_list = {"Firma Adı" , "Adres", "E-Posta", "Telefon Numarası", "Şehir","Tesis Özellikleri"};
        mdl_company_list.setColumnIdentifiers(col_user_list);
        row_company_list = new Object[col_user_list.length];


        loadCompanyModel();
        tbl_company_list.setModel(mdl_company_list);
        tbl_company_list.getTableHeader().setReorderingAllowed(false);

        btn_companyAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CompanyAddGUI companyAddGUI = new CompanyAddGUI(user);
                dispose();
            }
        });
        btn_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginGUI loginGUI = new LoginGUI();
                dispose();
            }
        });

        tbl_company_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point point = e.getPoint();
                int selected_row = tbl_company_list.getSelectedRow();
                tbl_company_list.setRowSelectionInterval(0,selected_row);
                companyName = tbl_company_list.getValueAt(selected_row,0).toString();
                System.out.println(companyName);

            }
        });
        btn_vehicle_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (companyName != null) {
                    VehicleAddGUI vehicleAddGUI = new VehicleAddGUI(companyName);
                }else {
                    Helper.showMsg("Lütfen bir firma seçiniz.");
                }

            }
        });
        btn_vehicle_price.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (companyName != null) {
                    VehiclePricingGUI vehiclePricingGUI = new VehiclePricingGUI(companyName);

                }else {
                    Helper.showMsg("Lütfen bir firma seçiniz.");
                }
            }
        });

    }

    private void loadCompanyModel(){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_company_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for(Company obj: Company.getList()){
            i=0;
            row_company_list[i++] = obj.getName();
            row_company_list[i++] = obj.getAddress();
            row_company_list[i++] = obj.getEmail();
            row_company_list[i++] = obj.getPhoneNumber();
            row_company_list[i++] = obj.getCity();
            row_company_list[i++] = parseArray(obj.getServiceSpec());

            mdl_company_list.addRow(row_company_list);

        }
    }
    private String parseArray(Array service_spec){
        StringBuilder stringBuilder = new StringBuilder();
        String[] tempArr;
        try {
            tempArr = (String[]) service_spec.getArray();
            for (String s:tempArr) {
                stringBuilder.append(s);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return stringBuilder.toString();
    }
}
