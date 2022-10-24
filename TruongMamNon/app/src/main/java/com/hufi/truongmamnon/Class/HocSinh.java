package com.hufi.truongmamnon.Class;

public class HocSinh {
    String ma;
    String ten;
    String gioiTinh;
    String namSinh;
    String maLop;
    String maHeHoc;
    String tenPhuHuynh;
    String sdt;
    String diaChi;
    String ngayDangKy;

    public HocSinh(String ten, String gioiTinh, String namSinh, String maLop, String maHeHoc, String tenPhuHuynh, String sdt, String diaChi, String ngayDangKy) {
        this.ten = ten;
        this.gioiTinh = gioiTinh;
        this.namSinh = namSinh;
        this.maLop = maLop;
        this.maHeHoc = maHeHoc;
        this.tenPhuHuynh = tenPhuHuynh;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.ngayDangKy = ngayDangKy;
    }

    @Override
    public String toString() {
        return "HocSinh{" +
                "ma='" + ma + '\'' +
                ", ten='" + ten + '\'' +
                ", gioiTinh='" + gioiTinh + '\'' +
                ", namSinh='" + namSinh + '\'' +
                ", maLop='" + maLop + '\'' +
                ", maHeHoc='" + maHeHoc + '\'' +
                ", tenPhuHuynh='" + tenPhuHuynh + '\'' +
                ", sdt='" + sdt + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", ngayDangKy='" + ngayDangKy + '\'' +
                '}';
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getMaHeHoc() {
        return maHeHoc;
    }

    public void setMaHeHoc(String maHeHoc) {
        this.maHeHoc = maHeHoc;
    }

    public String getTenPhuHuynh() {
        return tenPhuHuynh;
    }

    public void setTenPhuHuynh(String tenPhuHuynh) {
        this.tenPhuHuynh = tenPhuHuynh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getNgayDangKy() {
        return ngayDangKy;
    }

    public void setNgayDangKy(String ngayDangKy) {
        this.ngayDangKy = ngayDangKy;
    }
}
