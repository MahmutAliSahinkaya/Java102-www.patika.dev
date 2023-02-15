package com.patikadev.Model;

import com.patikadev.Helper.DBConnector;
import com.patikadev.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Content {
    private int id;
    private String title;
    private String description;
    private String contentLink;
    private String quizQuestion;
    private int course_id;
    private Course course;

    public Content(int id, String title, String description, String contentLink, String quizQuestion, int course_id) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.contentLink = contentLink;
        this.quizQuestion = quizQuestion;
        this.course_id = course_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContentLink() {
        return contentLink;
    }

    public void setContentLink(String contentLink) {
        this.contentLink = contentLink;
    }

    public String getQuizQuestion() {
        return quizQuestion;
    }

    public void setQuizQuestion(String quizQuestion) {
        this.quizQuestion = quizQuestion;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public static ArrayList<Content> getList(int courseId) {
        ArrayList<Content> contentList = new ArrayList<>();
        Content obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM content WHERE course_id = " + courseId);
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String contentLink = rs.getString("contentLink");
                String quizQuestion = rs.getString("quizQuestion");
                int course_id = rs.getInt("course_id");
                obj = new Content(id, title, description, contentLink, quizQuestion, course_id);
                contentList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contentList;
    }

    public static ArrayList<Content> getList(String contentTitle) {
        ArrayList<Content> contentList = new ArrayList<>();
        Content obj;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement("SELECT * FROM content WHERE title = ?");
            pr.setString(1, contentTitle);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String contentLink = rs.getString("contentLink");
                String quizQuestion = rs.getString("quizQuestion");
                int course_id = rs.getInt("course_id");
                obj = new Content(id, title, description, contentLink, quizQuestion, course_id);
                contentList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contentList;
    }

    public static Content getFetch(String title) {
        Content obj = null;
        String query = "SELECT * FROM content WHERE title = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, title);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = new Content(rs.getInt("id"), rs.getString("title"), rs.getString("description"), rs.getString("contentLink"), rs.getString("quizQuestion"), rs.getInt("course_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    public static boolean add(String title, String description, String contentLink, int course_id) {
        String query = "INSERT INTO content (title,description,contentLink,course_id) VALUES (?,?,?,?)";
        Content findContent = getFetch(title);
        if (findContent != null) {
            Helper.showMsg("Konu başlığı mevcut lütfen farklı bir konu başlığı giriniz!");
            return false;
        } else {
            try {
                PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
                pr.setString(1, title);
                pr.setString(2, description);
                pr.setString(3, contentLink);
                pr.setInt(4, course_id);
                return pr.executeUpdate() != -1;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static boolean delete(int content_id) {
        String query = "DELETE FROM content WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, content_id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Content> searchContentForTitle(String title, int id) {
        String query = "SELECT * FROM content WHERE title ILIKE '%{{title}}%' AND course_id = " + id;
        query = query.replace("{{title}}", title);
        ArrayList<Content> contentList = new ArrayList<>();
        Content obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                obj = new Content(rs.getInt("id"), rs.getString("title"), rs.getString("description"), rs.getString("contentLink"), rs.getString("quizQuestion"), rs.getInt("course_id"));
                contentList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contentList;
    }

    public static int getCourseID(String name) {
        String query = "SELECT id FROM course WHERE name = ?";
        int id = 0;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, name);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
            }
            return id;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static boolean addQuiz(String title, String quizQuestion) {
        String query = "UPDATE public.content SET quizQuestion=? WHERE title =?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, quizQuestion);
            pr.setString(2, title);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
