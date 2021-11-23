/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;
import utils.XDate;

/**
 *
 * @author admin
 */
public class HoaDon {

    private String maHD;
    private String maNV;
    private String maKH;
    private String hoTenKH;
    private String soDienThoai;
    private String email;
    private String anhDatCoc;
    private float giamGia;
    private float tongTien;
    private Date ngayThanhToan;
    private int trangThaiHD;
    private int trangThaiTT;

    public HoaDon() {
    }

    public HoaDon(String maHD, String maNV, String maKH, String hoTenKH, String soDienThoai, String email, String anhDatCoc, float giamGia, float tongTien, Date ngayThanhToan, int trangThaiHD, int trangThaiTT) {
        this.maHD = maHD;
        this.maNV = maNV;
        this.maKH = maKH;
        this.hoTenKH = hoTenKH;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.anhDatCoc = anhDatCoc;
        this.giamGia = giamGia;
        this.tongTien = tongTien;
        this.ngayThanhToan = XDate.now();
        this.trangThaiHD = trangThaiHD;
        this.trangThaiTT = trangThaiTT;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getHoTenKH() {
        return hoTenKH;
    }

    public void setHoTenKH(String hoTenKH) {
        this.hoTenKH = hoTenKH;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAnhDatCoc() {
        return anhDatCoc;
    }

    public void setAnhDatCoc(String anhDatCoc) {
        this.anhDatCoc = anhDatCoc;
    }

    public float getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(float giamGia) {
        this.giamGia = giamGia;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public Date getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(Date ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public int getTrangThaiHD() {
        return trangThaiHD;
    }

    public void setTrangThaiHD(int trangThaiHD) {
        this.trangThaiHD = trangThaiHD;
    }

    public int getTrangThaiTT() {
        return trangThaiTT;
    }

    public void setTrangThaiTT(int trangThaiTT) {
        this.trangThaiTT = trangThaiTT;
    }

    @Override
    public String toString() {
        return "HoaDon{" + "maHD=" + maHD + ", maNV=" + maNV + ", maKH=" + maKH + ", hoTenKH=" + hoTenKH + ", soDienThoai=" + soDienThoai + ", email=" + email + ", anhDatCoc=" + anhDatCoc + ", giamGia=" + giamGia + ", tongTien=" + tongTien + ", ngayThanhToan=" + ngayThanhToan + ", trangThaiHD=" + trangThaiHD + ", trangThaiTT=" + trangThaiTT + '}';
    }
    
    

}
