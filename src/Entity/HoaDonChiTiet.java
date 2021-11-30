/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;

/**
 *
 * @author admin
 */
public class HoaDonChiTiet {
    private int maHDCT;
    private String maHD;
    private String maSan;
    private String maKG;
    private Date ngayDat;
    private String trongTai;
    private float giaTien;
    private int trangThai;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(int maHDCT, String maHD, String maSan, String maKG, Date ngayDat, String trongTai, float giaTien, int trangThai) {
        this.maHDCT = maHDCT;
        this.maHD = maHD;
        this.maSan = maSan;
        this.maKG = maKG;
        this.ngayDat = ngayDat;
        this.trongTai = trongTai;
        this.giaTien = giaTien;
        this.trangThai = trangThai;
    }

    public int getMaHDCT() {
        return maHDCT;
    }

    public void setMaHDCT(int maHDCT) {
        this.maHDCT = maHDCT;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaSan() {
        return maSan;
    }

    public void setMaSan(String maSan) {
        this.maSan = maSan;
    }

    public String getMaKG() {
        return maKG;
    }

    public void setMaKG(String maKG) {
        this.maKG = maKG;
    }

    public Date getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(Date ngayDat) {
        this.ngayDat = ngayDat;
    }

    public String getTrongTai() {
        return trongTai;
    }

    public void setTrongTai(String trongTai) {
        this.trongTai = trongTai;
    }

    public float getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(float giaTien) {
        this.giaTien = giaTien;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

   
}
