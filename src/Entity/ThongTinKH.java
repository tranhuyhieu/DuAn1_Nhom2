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
public class ThongTinKH {
    private int maThongTin;
    private String maKH;
    private String email;
    private String soDienThoai;

    public ThongTinKH() {
    }

    public ThongTinKH(int maThongTin, String maKH, String email, String soDienThoai) {
        this.maThongTin = maThongTin;
        this.maKH = maKH;
        this.email = email;
        this.soDienThoai = soDienThoai;
    }

    public int getMaThongTin() {
        return maThongTin;
    }

    public void setMaThongTin(int maThongTin) {
        this.maThongTin = maThongTin;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
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
