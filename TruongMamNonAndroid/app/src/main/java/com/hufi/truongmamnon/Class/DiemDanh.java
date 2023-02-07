package com.hufi.truongmamnon.Class;

import java.util.Date;

public class DiemDanh {
    int ma;
    Date ngay;
    String maHocSinh;
    String trangThai;
    String coPhep;

    public DiemDanh(int ma, Date ngay, String maHocSinh, String trangThai, String coPhep) {
        this.ma = ma;
        this.ngay = ngay;
        this.maHocSinh = maHocSinh;
        this.trangThai = trangThai;
        this.coPhep = coPhep;
    }

    @Override
    public String toString() {
        return "DiemDanh{" +
                "ma=" + ma +
                ", ngay=" + ngay +
                ", maHocSinh='" + maHocSinh + '\'' +
                ", trangThai='" + trangThai + '\'' +
                ", coPhep='" + coPhep + '\'' +
                '}';
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public String getMaHocSinh() {
        return maHocSinh;
    }

    public void setMaHocSinh(String maHocSinh) {
        this.maHocSinh = maHocSinh;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getCoPhep() {
        return coPhep;
    }

    public void setCoPhep(String coPhep) {
        this.coPhep = coPhep;
    }
}
