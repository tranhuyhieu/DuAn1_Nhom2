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
public class SanBong {
    private String maSan;
    private String loaiSan;
    private String trangThaiSan;
    private String giaSan;

    public SanBong() {
    }

    public SanBong(String maSan, String loaiSan, String trangThaiSan, String giaSan) {
        this.maSan = maSan;
        this.loaiSan = loaiSan;
        this.trangThaiSan = trangThaiSan;
        this.giaSan = giaSan;
    }

    public String getMaSan() {
        return maSan;
    }

    public void setMaSan(String maSan) {
        this.maSan = maSan;
    }

    public String getLoaiSan() {
        return loaiSan;
    }

    public void setLoaiSan(String loaiSan) {
        this.loaiSan = loaiSan;
    }

    public String getTrangThaiSan() {
        return trangThaiSan;
    }

    public void setTrangThaiSan(String trangThaiSan) {
        this.trangThaiSan = trangThaiSan;
    }

    public String getGiaSan() {
        return giaSan;
    }

    public void setGiaSan(String giaSan) {
        this.giaSan = giaSan;
    }
    
}
