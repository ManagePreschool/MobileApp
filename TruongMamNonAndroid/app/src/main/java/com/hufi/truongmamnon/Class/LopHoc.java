package com.hufi.truongmamnon.Class;

public class LopHoc {
    int maLop;
    String tenLop;
    int maKhoiLop;
    double hocPhi;
    int siSoToiDa;
    int maNienHoc;

    public LopHoc(int maLop, String tenLop, int maKhoiLop, double hocPhi, int siSoToiDa, int maNienHoc) {
        this.maLop = maLop;
        this.tenLop = tenLop;
        this.maKhoiLop = maKhoiLop;
        this.hocPhi = hocPhi;
        this.siSoToiDa = siSoToiDa;
        this.maNienHoc = maNienHoc;
    }

    @Override
    public String toString() {
        return "LopHoc{" +
                "maLop=" + maLop +
                ", tenLop='" + tenLop + '\'' +
                ", maKhoiLop=" + maKhoiLop +
                ", hocPhi=" + hocPhi +
                ", siSoToiDa=" + siSoToiDa +
                ", maNienHoc=" + maNienHoc +
                '}';
    }

    public int getMaLop() {
        return maLop;
    }

    public void setMaLop(int maLop) {
        this.maLop = maLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public int getMaKhoiLop() {
        return maKhoiLop;
    }

    public void setMaKhoiLop(int maKhoiLop) {
        this.maKhoiLop = maKhoiLop;
    }

    public double getHocPhi() {
        return hocPhi;
    }

    public void setHocPhi(double hocPhi) {
        this.hocPhi = hocPhi;
    }

    public int getSiSoToiDa() {
        return siSoToiDa;
    }

    public void setSiSoToiDa(int siSoToiDa) {
        this.siSoToiDa = siSoToiDa;
    }

    public int getMaNienHoc() {
        return maNienHoc;
    }

    public void setMaNienHoc(int maNienHoc) {
        this.maNienHoc = maNienHoc;
    }
}
