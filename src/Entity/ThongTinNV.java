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
public class ThongTinNV {
    private int maThongTin;
    private String maNV;
    private String email;
    private String soDienThoai;

    public ThongTinNV() {
    }

    public ThongTinNV(int maThongTin, String maNV, String email, String soDienThoai) {
        this.maThongTin = maThongTin;
        this.maNV = maNV;
        this.email = email;
        this.soDienThoai = soDienThoai;
    }

    public int getMaThongTin() {
        return maThongTin;
    }

    public void setMaThongTin(int maThongTin) {
        this.maThongTin = maThongTin;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }
    
}
