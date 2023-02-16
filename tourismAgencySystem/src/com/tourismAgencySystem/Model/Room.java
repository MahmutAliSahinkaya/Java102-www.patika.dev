package com.tourismAgencySystem.Model;

import com.tourismAgencySystem.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Room {
    private int id;
    private String roomType;
    private int bedNum;
    private int stock;
    private boolean tv;
    private boolean minibar;
    private boolean safe;

    private Hotel hotel;

    public Room(int id, String roomType, int bedNum, int stock, boolean tv, boolean minibar, boolean safe) {
        this.id = id;
        this.roomType = roomType;
        this.bedNum = bedNum;
        this.stock = stock;
        this.tv = tv;
        this.minibar = minibar;
        this.safe = safe;
    }

    public static int isRoomTypeAdded(String roomType, int hotelId) {
        String query = "SELECT id FROM room WHERE roomType = ? AND hotel_id = ?";
        int id = 0;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, roomType);
            pr.setInt(2, hotelId);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public static boolean add(String room_type, int stock, int hotel_id, int bed_num, boolean tv, boolean minibar, boolean safe) {
        String query = "INSERT INTO room (roomType, stock, hotel_id, bedNum, tv, minibar, safe) VALUES (?,?,?,?,?,?,?)";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, room_type);
            pr.setInt(2, stock);
            pr.setInt(3, hotel_id);
            pr.setInt(4, bed_num);
            pr.setBoolean(5, tv);
            pr.setBoolean(6, minibar);
            pr.setBoolean(7, safe);

            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getHotelIdByRoomId(int id) {
        String query = "SELECT course_id from room WHERE id = " + id;
        int result = 0;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            result = rs.getInt("id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public int getId() {
        return id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getBedNum() {
        return bedNum;
    }

    public void setBedNum(int bedNum) {
        this.bedNum = bedNum;
    }

    public boolean isTv() {
        return tv;
    }

    public void setTv(boolean tv) {
        this.tv = tv;
    }

    public boolean isMinibar() {
        return minibar;
    }

    public void setMinibar(boolean minibar) {
        this.minibar = minibar;
    }

    public boolean isSafe() {
        return safe;
    }

    public void setSafe(boolean safe) {
        this.safe = safe;
    }
}
