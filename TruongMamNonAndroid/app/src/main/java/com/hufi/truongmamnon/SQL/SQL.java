package com.hufi.truongmamnon.SQL;

import android.os.StrictMode;

import com.hufi.truongmamnon.Class.HocSinh;
import com.hufi.truongmamnon.Class.LopHoc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class SQL {
    private static String ip = "192.168.1.189";      //192.168.1.189
    private static String port = "1433";
    private static String Classes = "net.sourceforge.jtds.jdbc.Driver";
    private static String database = "TruongMamNon";
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
            ResultSet resultSet = statement.executeQuery("select * from HocSinhs where EmailPhuHuynh = '" + username + "' and MatKhau = '" + password + "'");
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

    public LopHoc getLopHoc(int maLop) {
        String tenLop = "";
        int maKhoiLop = 0;
        double hocPhi = 0;
        int siSoToiDa = 0;
        int maNienHoc = 0;

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from LopHocs where MaLop = '" + maLop + "'");
            if (resultSet.next())//nếu trong resultset không null thì sẽ trả về True
            {
                tenLop = resultSet.getString("TenLop");
                maKhoiLop = resultSet.getInt("MaKhoiLop");
                hocPhi = resultSet.getDouble("HocPhi");
                siSoToiDa = resultSet.getInt("SiSoToiDa");
                maNienHoc = resultSet.getInt("MaNienHoc");
            }
            //connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new LopHoc(maLop, tenLop, maKhoiLop, hocPhi, siSoToiDa, maNienHoc);
        //return new LopHoc(maLop, tenLop);
    }

    public HocSinh getHocSinh(String email) {
        int maLopHoc = 0;
        String ma = "", ho = "", ten = "", noiSinh = "", danToc = "", tonGiao = "", quocTich = "", hoKhau = "", diaChi = "", emailPhuHuynh = "", hoTenPhuHuynh = "";
        String gioiTinh = "";
        Date ngayNhapHoc = null, ngaySinh = null;

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from HocSinhs where EmailPhuHuynh = '" + email + "'");
            if (resultSet.next())//nếu trong resultset không null thì sẽ trả về True
            {
                ma = resultSet.getString("MaHocSinh");
                ho = resultSet.getString("Ho");
                ten = resultSet.getString("Ten");
                gioiTinh = resultSet.getString("GioiTinh");
                maLopHoc = resultSet.getInt("MaLopHoc");
                ngayNhapHoc = resultSet.getDate("NgayNhapHoc");
                ngaySinh = resultSet.getDate("NgaySinh");
                noiSinh = resultSet.getString("NoiSinh");
                danToc = resultSet.getString("DanToc");
                tonGiao = resultSet.getString("TonGiao");
                quocTich = resultSet.getString("QuocTich");
                hoKhau = resultSet.getString("HoKhau");
                diaChi = resultSet.getString("DiaChi");
                hoTenPhuHuynh = resultSet.getString("HoTenPhuHuynh");
                emailPhuHuynh = resultSet.getString("EmailPhuHuynh");
            }
            //connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new HocSinh(ma, ho, ten, gioiTinh, maLopHoc, ngayNhapHoc,  ngaySinh, noiSinh, danToc, tonGiao, quocTich, hoKhau, diaChi, hoTenPhuHuynh, emailPhuHuynh);
    }

    /*public List<HocSinh> getHocSinhList() throws SQLException {
        List<HocSinh> list = new ArrayList<>();
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = "select * from HocSinhs";
        // Thực thi câu lệnh SQL trả về đối tượng ResultSet. // Mọi kết quả trả về sẽ được lưu trong ResultSet
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            //list.add(new HocSinh(rs.getString("MaHocSinh"), rs.getString("Ho"), rs.getString("Ten"), rs.getBoolean("GioiTinh"), rs.getString("MaLopHoc")
            //       , rs.getString("NgayNhapHoc"), rs.getString("NoiSinh"), rs.getString("DanToc"), rs.getString("TonGiao"), rs.getString("QuocTich")
            //       , rs.getString("HoKhau"), rs.getString("DiaChi"), rs.getDate("NgaySinh"), rs.getString("EmailPhuHuynh"), rs.getString("HoTenPhuHuynh")));// Đọc dữ liệu từ ResultSet
            list.add(new HocSinh(rs.getString("MaHocSinh"), rs.getString("Ho"), rs.getString("Ten"), rs.getBoolean("GioiTinh"), rs.getDate("NgaySinh")));
        }
        //connection.close();// Đóng kết nối
        return list;
    }*/
}
