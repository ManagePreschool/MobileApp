package com.hufi.truongmamnon;

import com.hufi.truongmamnon.Class.Account;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Database {
    //private JDBCController jdbcController = new JDBCController();
    private Connection connection;

    /*public Database() {
        connection = jdbcController.ConnnectionData(); // Tạo kết nối tới database
    }*/

   public List<Account> getAccountList() throws SQLException {
        List<Account> list = new ArrayList<>();
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = "select ID, Email, Password, Quyen from Account";
        // Thực thi câu lệnh SQL trả về đối tượng ResultSet. // Mọi kết quả trả về sẽ được lưu trong ResultSet
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            list.add(new Account(rs.getInt("id"), rs.getString("email"), rs.getString("password"), rs.getInt("quyen")));// Đọc dữ liệu từ ResultSet
        }
        connection.close();// Đóng kết nối
        return list;
    }
/*
    public boolean Insert(Account objUser) throws SQLException {
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = "insert in to Account(Email) values(" + objUser.getEmail() + ")";
        if (statement.executeUpdate(sql) > 0) { // Dùng lệnh executeUpdate cho các lệnh CRUD
            connection.close();
            return true;
        } else {
            connection.close();
            return false;
        }
    }

    public boolean Update(Account objUser) throws SQLException {
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        //String sql = "Update Account set Email = " + objUser.getEmail() + " where ID = " + objUser.getID();
        String sql = "Update Account set Email = " + objUser.getEmail();
        if (statement.executeUpdate(sql) > 0) {
            connection.close();
            return true;
        } else
            connection.close();
        return false;
    }

    public boolean Delete(Account objUser) throws SQLException {
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = "delete from Account where Email = " + objUser.getEmail();
        if (statement.executeUpdate(sql) > 0){
            connection.close();
            return true;
        }
        else
            connection.close();
        return false;
    }
*/
    public boolean isUserExist(String username, String password) throws SQLException {
        //Cursor mCursor = db.rawQuery("SELECT * FROM " + TABLE_CONTACTS + " WHERE    email=? ", new String[]{b});
        Statement statement = connection.createStatement();
        /*String sql = "SELECT * FROM Account WHERE " +
                "EXISTS (SELECT Email, Password FROM Account WHERE Email = " + username + " AND Password = " + password + ")";*/
        String sql = "SELECT * FROM Account";
        /*if (statement.executeUpdate(sql) > 0){
            connection.close();
            return true;
        }*/
        //else
            //connection.close();
        return false;
    }
}
