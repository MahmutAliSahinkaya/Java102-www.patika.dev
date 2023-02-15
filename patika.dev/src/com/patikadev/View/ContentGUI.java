package com.patikadev.View;


import com.patikadev.Helper.Config;
import com.patikadev.Helper.DBConnector;
import com.patikadev.Helper.Helper;
import com.patikadev.Model.Content;
import com.patikadev.Model.Course;
import com.patikadev.Model.Educator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ContentGUI extends JFrame{
    private JPanel wrapper;
    private JLabel lbl_course_name;
    private JTextField fld_content_srch;
    private JTable tbl_content;
    private JButton btn_content_add;
    private JButton btn_content_delete;
    private JEditorPane pane_content_title;
    private JEditorPane pane_content_description;
    private JEditorPane pane_content_link;
    private JButton btn_content_back;
    private JButton btn_content_search;
    private Course course;
    private String courseName;
    private DefaultTableModel mdl_content_list;
    private Object[] row_content_list;

    public ContentGUI(String courseName){
        this.courseName = courseName;
        this.course = getSelectedCourse(this.courseName);
        add(wrapper);
        setSize(1000,750);
        setLocation(Helper.screenCenterPoint("x",getSize()) , Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        lbl_course_name.setText(this.course.getName());

        mdl_content_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0){
                    return false;
                }
                return super.isCellEditable(row,column);
            }
        };
        Object[] col_content_list = {"ID", "Başlık","Açıklama","Video Link","Quiz Soruları"," course_id"};
        mdl_content_list.setColumnIdentifiers(col_content_list);
        row_content_list = new Object[col_content_list.length];
        tbl_content.setModel(mdl_content_list);
        tbl_content.getTableHeader().setReorderingAllowed(false);
        tbl_content.getColumnModel().getColumn(0).setMaxWidth(70);
        loadContentModel();


        btn_content_add.addActionListener(e -> {
            if (Helper.isFieldEmpty(pane_content_title) || Helper.isFieldEmpty(pane_content_description) || Helper.isFieldEmpty(pane_content_link)){
                Helper.showMsg("fill");
            }else {
                String title = pane_content_title.getText();
                String description = pane_content_description.getText();
                String contentLink = pane_content_link.getText();
                int cours_id = course.getId();
                if (Content.add(title,description,contentLink,cours_id)){
                    Helper.showMsg("done");
                    loadContentModel();
                    pane_content_description.setText(null);
                    pane_content_title.setText(null);
                    pane_content_link.setText(null);
                }
            }
        });
        btn_content_back.addActionListener(e -> {
            Educator educator = Educator.getFetch(course.getUser_id());
            EducatorGUI educatorGUI = new EducatorGUI(educator);
            dispose();
        });
        btn_content_delete.addActionListener(e -> {
            if (Helper.confirm("sure")){
                int course_id = (int) tbl_content.getValueAt(tbl_content.getSelectedRow(),0);
                if (Content.delete(course_id)){
                    Helper.showMsg("done");
                    loadContentModel();
                }else {
                    Helper.showMsg("error");
                }

            }
        });

        fld_content_srch.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String searchBoxText = fld_content_srch.getText();
                    String title = courseName;
                    int id = Content.getCourseID(title);
                    System.out.println("course iddddd" + id);
                    if (!searchBoxText.equals("")) {
                        ArrayList<Content> searchTitleList = Content.searchContentForTitle(searchBoxText,id);
                        loadContentModel(searchTitleList);
                        fld_content_srch.setText(null);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        btn_content_search.addActionListener(e -> {
            String title = fld_content_srch.getText();
            int id = Content.getCourseID(title);
            if (!title.equals("")) {
                ArrayList<Content> searchTitleList = Content.searchContentForTitle(title,id);
                loadContentModel(searchTitleList);
                fld_content_srch.setText(null);
            }

        });
    }

    public static Course getSelectedCourse(String course_name){
        String query = "SELECT * FROM course WHERE course.name=?";
        Course obj = null;
        try {
            PreparedStatement ps = DBConnector.getInstance().prepareStatement(query);
            ps.setString(1,course_name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                int user_id = rs.getInt("user_id");
                int patika_id = rs.getInt("patika_id");
                String name = rs.getString("name");
                String lang = rs.getString("lang");
                obj = new Course(id,user_id,patika_id,name,lang);}
            return obj;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void loadContentModel(){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_content.getModel();
        clearModel.setRowCount(0);
        int i;
        int id = this.course.getId();
        for (Content obj: Content.getList(id)) {
            i = 0;
            row_content_list[i++] = obj.getId();
            row_content_list[i++] = obj.getTitle();
            row_content_list[i++] = obj.getDescription();
            row_content_list[i++] = obj.getContentLink();
            row_content_list[i++] = obj.getQuizQuestion();
            row_content_list[i++] = obj.getCourse_id();
            mdl_content_list.addRow(row_content_list);

        }
    }
    public void loadContentModel(ArrayList<Content> list){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_content.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Content obj: list) {
            i = 0;
            row_content_list[i++] = obj.getId();
            row_content_list[i++] = obj.getTitle();
            row_content_list[i++] = obj.getDescription();
            row_content_list[i++] = obj.getContentLink();
            row_content_list[i++] = obj.getQuizQuestion();
            row_content_list[i++] = obj.getCourse_id();
            mdl_content_list.addRow(row_content_list);

        }
    }

}
