/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author admin
 */
public class DichVu {
    private int maDV;
    private int maHDCT;
    private String tenDV;
    private int soLuong;
    private float giaTien;
    private String trangThaiDichVu;

    public DichVu() {
    }

    public DichVu(int maDV, int maHDCT, String tenDV, int soLuong, float giaTien, String trangThaiDichVu) {
        this.maDV = maDV;
        this.maHDCT = maHDCT;
        this.tenDV = tenDV;
        this.soLuong = soLuong;
        this.giaTien = giaTien;
        this.trangThaiDichVu = trangThaiDichVu;
    }

    public int getMaDV() {
        return maDV;
    }

    public void setMaDV(int maDV) {
        this.maDV = maDV;
    }

    public int getMaHDCT() {
        return maHDCT;
    }

    public void setMaHDCT(int maHDCT) {
        this.maHDCT = maHDCT;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(float giaTien) {
        this.giaTien = giaTien;
    }

    public String getTrangThaiDichVu() {
        return trangThaiDichVu;
    }

    public void setTrangThaiDichVu(String trangThaiDichVu) {
        this.trangThaiDichVu = trangThaiDichVu;
    }
    
}
