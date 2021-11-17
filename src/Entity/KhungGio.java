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
public class KhungGio {
    private String maKG;
    private String khungGio;

    public KhungGio() {
    }

    public KhungGio(String maKG, String khungGio) {
        this.maKG = maKG;
        this.khungGio = khungGio;
    }

    public String getMaKG() {
        return maKG;
    }

    public void setMaKG(String maKG) {
        this.maKG = maKG;
    }

    public String getKhungGio() {
        return khungGio;
    }

    public void setKhungGio(String khungGio) {
        this.khungGio = khungGio;
    }
    
}
