package com.hufi.truongmamnon.SQL;

import android.os.StrictMode;

import com.hufi.truongmamnon.Class.Account;
import com.hufi.truongmamnon.Class.HocSinh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQL {
    private static String ip = "192.168.1.25";
    private static String port = "1433";
    private static String Classes = "net.sourceforge.jtds.jdbc.Driver";
    private static String database = "HocPhi";
    private static String username = "sa";
    private static String password = "123";
    private static String url = "jdbc:jtds:sqlserver://"+ip+":"+port+"/"+database;
    private static int timeout = 5;     //Seconds

    private Connection connection;

    public SQL() { connection = Connect(); }

    public void Close() throws SQLException { connection.close(); }

    public Connection Connect() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection objConn = null;
        try {
            Class.forName(Classes);
            DriverManager.setLoginTimeout(timeout);
            objConn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return objConn;
    }

    public boolean isConnected() {
        if (connection != null)
            return true;
        return false;
    }

    public boolean isUserExist(String username, String password) {
        //if (connection == null)
        //    return false;
        //return true;

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Account where Email = '" + username + "' and Password = '" + password + "'");
            //while (resultSet.next()){
            //    textView.setText(resultSet.getS tring(1));
            //}
            if (resultSet.next())//nếu trong resultset không null thì sẽ trả về True
            {
                //hoten = resultSet.getString("Email");//Hàm lấy giá trị của tên cột (trường: field name) truyền vào
                //connection.close();
                return true;
            }
            else {
                //z = "Tài khoản không tồn tại !";
                //connection.close();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Account getAccount(String email) {
        String pwd = "";

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Account where Email = '" + email + "'");
            if (resultSet.next())//nếu trong resultset không null thì sẽ trả về True
            {
                pwd = resultSet.getString("Password");//Hàm lấy giá trị của tên cột (trường: field name) truyền vào
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Account(email, pwd);
    }

    public List<HocSinh> getHocSinhList() throws SQLException {
        List<HocSinh> list = new ArrayList<>();
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = "select * from HocSinh";
        // Thực thi câu lệnh SQL trả về đối tượng ResultSet. // Mọi kết quả trả về sẽ được lưu trong ResultSet
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            list.add(new HocSinh(rs.getString("TenHocSinh"), rs.getString("GioiTinh"), rs.getString("NamSinh"), rs.getString("MaLop"), rs.getString("MaHeHoc")
                    , rs.getString("TenPhuHuynh"), rs.getString("DienThoai"), rs.getString("DiaChiLienHe"), rs.getString("NgayDangKy")));// Đọc dữ liệu từ ResultSet
        }
        connection.close();// Đóng kết nối
        return list;
    }
}
