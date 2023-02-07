package com.hufi.truongmamnon.Class;

import java.util.Date;

public class HocSinh {
    int ma;
    String ho;
    String ten;
    String gioiTinh;
    String hinhAnh;
    int maLop;
    Date ngayNhapHoc;
    Date ngaySinh;
    String noiSinh;
    String danToc;
    String tonGiao;
    String quocTich;
    String hoKhau;
    String diaChi;
    String hoTenPhuHuynh;
    String emailPhuHuynh;

    public HocSinh(int ma, String ho, String ten) {
        this.ma = ma;
        this.ho = ho;
        this.ten = ten;
    }

    public HocSinh(int ma, String ho, String ten, String gioiTinh, String hinhAnh, int maLop, Date ngayNhapHoc, Date ngaySinh, String noiSinh, String danToc, String tonGiao, String quocTich, String hoKhau, String diaChi, String hoTenPhuHuynh, String emailPhuHuynh) {
        this.ma = ma;
        this.ho = ho;
        this.ten = ten;
        this.gioiTinh = gioiTinh;
        this.hinhAnh = hinhAnh;
        this.maLop = maLop;
        this.ngayNhapHoc = ngayNhapHoc;
        this.ngaySinh = ngaySinh;
        this.noiSinh = noiSinh;
        this.danToc = danToc;
        this.tonGiao = tonGiao;
        this.quocTich = quocTich;
        this.hoKhau = hoKhau;
        this.diaChi = diaChi;
        this.hoTenPhuHuynh = hoTenPhuHuynh;
        this.emailPhuHuynh = emailPhuHuynh;
    }

    @Override
    public String toString() {
        return "HocSinh{" +
                "ma=" + ma +
                ", ho='" + ho + '\'' +
                ", ten='" + ten + '\'' +
                ", gioiTinh='" + gioiTinh + '\'' +
                ", hinhAnh='" + hinhAnh + '\'' +
                ", maLop=" + maLop +
                ", ngayNhapHoc=" + ngayNhapHoc +
                ", ngaySinh=" + ngaySinh +
                ", noiSinh='" + noiSinh + '\'' +
                ", danToc='" + danToc + '\'' +
                ", tonGiao='" + tonGiao + '\'' +
                ", quocTich='" + quocTich + '\'' +
                ", hoKhau='" + hoKhau + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", hoTenPhuHuynh='" + hoTenPhuHuynh + '\'' +
                ", emailPhuHuynh='" + emailPhuHuynh + '\'' +
                '}';
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
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

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public int getMaLop() {
        return maLop;
    }

    public void setMaLop(int maLop) {
        this.maLop = maLop;
    }

    public Date getNgayNhapHoc() {
        return ngayNhapHoc;
    }

    public void setNgayNhapHoc(Date ngayNhapHoc) {
        this.ngayNhapHoc = ngayNhapHoc;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getNoiSinh() {
        return noiSinh;
    }

    public void setNoiSinh(String noiSinh) {
        this.noiSinh = noiSinh;
    }

    public String getDanToc() {
        return danToc;
    }

    public void setDanToc(String danToc) {
        this.danToc = danToc;
    }

    public String getTonGiao() {
        return tonGiao;
    }

    public void setTonGiao(String tonGiao) {
        this.tonGiao = tonGiao;
    }

    public String getQuocTich() {
        return quocTich;
    }

    public void setQuocTich(String quocTich) {
        this.quocTich = quocTich;
    }

    public String getHoKhau() {
        return hoKhau;
    }

    public void setHoKhau(String hoKhau) {
        this.hoKhau = hoKhau;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getHoTenPhuHuynh() {
        return hoTenPhuHuynh;
    }

    public void setHoTenPhuHuynh(String hoTenPhuHuynh) {
        this.hoTenPhuHuynh = hoTenPhuHuynh;
    }

    public String getEmailPhuHuynh() {
        return emailPhuHuynh;
    }

    public void setEmailPhuHuynh(String emailPhuHuynh) {
        this.emailPhuHuynh = emailPhuHuynh;
    }
}
