package com.hufi.truongmamnon.SQL;

import android.os.StrictMode;

import com.hufi.truongmamnon.Class.DiemDanh;
import com.hufi.truongmamnon.Class.HocSinh;
import com.hufi.truongmamnon.Class.LopHoc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    public boolean isUserExist(int username, String password) {
        //if (connection == null)
        //    return false;
        //return true;

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from HocSinhs where MaHocSinh = " + username + " and MatKhau = '" + password + "'");
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

    public HocSinh getTenHocSinh(int ma) {
        Statement statement = null;
        String ho = "", ten = "";
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from HocSinhs where MaHocSinh = " + ma);
            if (resultSet.next())//nếu trong resultset không null thì sẽ trả về True
            {
                ho = resultSet.getString("Ho");
                ten = resultSet.getString("Ten");
            }
            //connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new HocSinh(ma, ho, ten);
    }

    public HocSinh getHocSinh(int ma) {
        int maLopHoc = 0;
        String ho = "", ten = "", noiSinh = "", danToc = "", tonGiao = "", quocTich = "", hoKhau = "", diaChi = "", emailPhuHuynh = "", hoTenPhuHuynh = "";
        String gioiTinh = "";       //"0" Nam, "1" Nu, "2" Khac
        String hinhAnh = "";
        Date ngayNhapHoc = null, ngaySinh = null;

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from HocSinhs where MaHocSinh = " + ma);
            if (resultSet.next())//nếu trong resultset không null thì sẽ trả về True
            {
                ma = resultSet.getInt("MaHocSinh");
                ho = resultSet.getString("Ho");
                ten = resultSet.getString("Ten");

                String maGioiTinh = resultSet.getString("MaGioiTinh");
                if (maGioiTinh.equals("0"))
                    gioiTinh = "Nam";
                else if (maGioiTinh.equals("1"))
                    gioiTinh = "Nữ";
                else
                    gioiTinh = "Khác";

                hinhAnh = resultSet.getString("HinhAnh");
                maLopHoc = resultSet.getInt("MaLopHoc");
                ngayNhapHoc = resultSet.getDate("NgayNhapHoc");
                ngaySinh = resultSet.getDate("NgaySinh");
                noiSinh = resultSet.getString("NoiSinh");

                String maDanToc = resultSet.getString("MaDanToc");
                danToc = getDanToc(maDanToc);

                String maTonGiao = resultSet.getString("MaTonGiao");
                tonGiao = getTonGiao(maTonGiao);

                String maQuocTich = resultSet.getString("MaQuocTich");
                quocTich = getQuocTich(maQuocTich);

                hoKhau = resultSet.getString("HoKhau");
                diaChi = resultSet.getString("DiaChi");
                hoTenPhuHuynh = resultSet.getString("HoTenPhuHuynh");
                emailPhuHuynh = resultSet.getString("EmailPhuHuynh");
            }
            //connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new HocSinh(ma, ho, ten, gioiTinh, hinhAnh, maLopHoc, ngayNhapHoc,  ngaySinh, noiSinh, danToc, tonGiao, quocTich, hoKhau, diaChi, hoTenPhuHuynh, emailPhuHuynh);
    }

    public void uploadHinhHocSinh(int ma, String hinhAnh) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeQuery("update HocSinhs set HinhAnh = '" + hinhAnh + "' where MaHocSinh = " + ma);
            //connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getDanToc(String ma) {
        String ten = "";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from DanTocs where MaDanToc = '" + ma + "'");
            if (resultSet.next())//nếu trong resultset không null thì sẽ trả về True
            {
                ten = resultSet.getString("TenDanToc");
            }
            //connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ten;
    }

    public String getTonGiao(String ma) {
        String ten = "";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from TonGiaos where MaTonGiao = '" + ma + "'");
            if (resultSet.next())//nếu trong resultset không null thì sẽ trả về True
            {
                ten = resultSet.getString("TenTonGiao");
            }
            //connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ten;
    }

    public String getQuocTich(String ma) {
        String ten = "";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from QuocGias where MaQuocGia = '" + ma + "'");
            if (resultSet.next())//nếu trong resultset không null thì sẽ trả về True
            {
                ten = resultSet.getString("TenQuocGia");
            }
            //connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ten;
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

    public List<DiemDanh> getDiemDanhList(Date ngay, int ma) throws SQLException {
        List<DiemDanh> list = new ArrayList<>();
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.

        DateFormat df = new SimpleDateFormat("yyyy-MM");
        String date = df.format(ngay);

        String sql = "select * from DiemDanhs where FORMAT(NgayDiemDanh,'yyyy-MM') = '" + date + "' and MaHocSinh = " + ma;
        // Thực thi câu lệnh SQL trả về đối tượng ResultSet. // Mọi kết quả trả về sẽ được lưu trong ResultSet
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            //list.add(new HocSinh(rs.getString("MaHocSinh"), rs.getString("Ho"), rs.getString("Ten"), rs.getBoolean("GioiTinh"), rs.getString("MaLopHoc")
            //       , rs.getString("NgayNhapHoc"), rs.getString("NoiSinh"), rs.getString("DanToc"), rs.getString("TonGiao"), rs.getString("QuocTich")
            //       , rs.getString("HoKhau"), rs.getString("DiaChi"), rs.getDate("NgaySinh"), rs.getString("EmailPhuHuynh"), rs.getString("HoTenPhuHuynh")));// Đọc dữ liệu từ ResultSet

            String diemDanh = "";
            diemDanh = getTrangThaiDiemDanh(rs.getString("MaTrangThaiDiemDanh"));

            if (diemDanh.equals("") != true) {
                String buoiVang =  "";
                if (diemDanh.contains("cả ngày"))
                {
                    buoiVang = "cả ngày";
                }
                else if (diemDanh.contains("sáng"))
                {
                    buoiVang = "sáng";
                }
                else if (diemDanh.contains("chiều"))
                {
                    buoiVang = "chiều";
                }

                String coPhep =  "";
                if (diemDanh.contains("có"))
                {
                    coPhep = "có";
                }
                else if (diemDanh.contains("không"))
                {
                    coPhep = "không";
                }

                list.add(new DiemDanh(rs.getInt("MaDiemDanh"), rs.getDate("NgayDiemDanh"), rs.getString("MaHocSinh"), buoiVang, coPhep));
            }
        }
        //connection.close();// Đóng kết nối
        return list;
    }

    public String getTrangThaiDiemDanh(String ma) {
        String ten = "";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from TrangThaiDiemDanhs where MaTrangThai = '" + ma + "'");
            if (resultSet.next())//nếu trong resultset không null thì sẽ trả về True
            {
                ten = resultSet.getString("TenTrangThai");
            }
            //connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ten;
    }

    public String getDiemDanhVang(Date ngay, int ma) throws SQLException {
        String diemDanh = "";

        Statement statement = connection.createStatement();// Tạo đối tượng Statement.

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = df.format(ngay);

        String sql = "select * from DiemDanhs where FORMAT(NgayDiemDanh,'yyyy-MM-dd') = '" + date + "' and MaHocSinh = " + ma;
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            diemDanh = getTrangThaiDiemDanh(rs.getString("MaTrangThaiDiemDanh"));
        }
        //connection.close();// Đóng kết nối
        return diemDanh;
    }

    public int getDiemDanhVangCaNgay(Date ngay, int ma) throws SQLException {
        int dem = 0;

        Statement statement = connection.createStatement();// Tạo đối tượng Statement.

        DateFormat df = new SimpleDateFormat("yyyy-MM");
        String date = df.format(ngay);

        String sql = "select * from DiemDanhs where FORMAT(NgayDiemDanh,'yyyy-MM') = '" + date + "' and MaHocSinh = " + ma;
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            String diemDanh = getTrangThaiDiemDanh(rs.getString("MaTrangThaiDiemDanh"));
            if (diemDanh.contains("cả ngày") == true)
                dem++;
        }
        //connection.close();// Đóng kết nối
        return dem;
    }

    public int getDiemDanhVangSang(Date ngay, int ma) throws SQLException {
        int dem = 0;

        Statement statement = connection.createStatement();// Tạo đối tượng Statement.

        DateFormat df = new SimpleDateFormat("yyyy-MM");
        String date = df.format(ngay);

        String sql = "select * from DiemDanhs where FORMAT(NgayDiemDanh,'yyyy-MM') = '" + date + "' and MaHocSinh = " + ma;
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            String diemDanh = getTrangThaiDiemDanh(rs.getString("MaTrangThaiDiemDanh"));
            if (diemDanh.contains("sáng") == true)
                dem++;
        }
        //connection.close();// Đóng kết nối
        return dem;
    }

    public int getDiemDanhVangChieu(Date ngay, int ma) throws SQLException {
        int dem = 0;

        Statement statement = connection.createStatement();// Tạo đối tượng Statement.

        DateFormat df = new SimpleDateFormat("yyyy-MM");
        String date = df.format(ngay);

        String sql = "select * from DiemDanhs where FORMAT(NgayDiemDanh,'yyyy-MM') = '" + date + "' and MaHocSinh = " + ma;
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            String diemDanh = getTrangThaiDiemDanh(rs.getString("MaTrangThaiDiemDanh"));
            if (diemDanh.contains("chiều") == true)
                dem++;
        }
        //connection.close();// Đóng kết nối
        return dem;
    }

    public int getDiemDanhVangKhongPhep(Date ngay, int ma) throws SQLException {
        int dem = 0;

        Statement statement = connection.createStatement();// Tạo đối tượng Statement.

        DateFormat df = new SimpleDateFormat("yyyy-MM");
        String date = df.format(ngay);

        String sql = "select * from DiemDanhs where FORMAT(NgayDiemDanh,'yyyy-MM') = '" + date + "' and MaHocSinh = " + ma;
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            String diemDanh = getTrangThaiDiemDanh(rs.getString("MaTrangThaiDiemDanh"));
            if (diemDanh.contains("không phép") == true)
                dem++;
        }
        //connection.close();// Đóng kết nối
        return dem;
    }

    public int getDiemDanhVangCoPhep(Date ngay, int ma) throws SQLException {
        int dem = 0;

        Statement statement = connection.createStatement();// Tạo đối tượng Statement.

        DateFormat df = new SimpleDateFormat("yyyy-MM");
        String date = df.format(ngay);

        String sql = "select * from DiemDanhs where FORMAT(NgayDiemDanh,'yyyy-MM') = '" + date + "' and MaHocSinh = " + ma;
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            String diemDanh = getTrangThaiDiemDanh(rs.getString("MaTrangThaiDiemDanh"));
            if (diemDanh.contains("có phép") == true)
                dem++;
        }
        //connection.close();// Đóng kết nối
        return dem;
    }
}
