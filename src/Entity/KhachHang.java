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
public class KhachHang {
    private String maKH;
    private String maNV;
    private String matKhau;
    private Date ngayDK;
    private String hoTen;

    public KhachHang() {
    }

    public KhachHang(String maKH, String maNV, String matKhau, Date ngayDK, String hoTen) {
        this.maKH = maKH;
        this.maNV = maNV;
        this.matKhau = matKhau;
        this.ngayDK = ngayDK;
        this.hoTen = hoTen;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public Date getNgayDK() {
        return ngayDK;
    }

    public void setNgayDK(Date ngayDK) {
        this.ngayDK = ngayDK;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
    
}
