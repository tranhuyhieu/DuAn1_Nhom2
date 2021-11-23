/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.XJdbc;

/**
 *
 * @author PC
 */
public class ThongKeDao {
    String SELECT_DOANHTHU = "SELECT\n" +
"		sb.Id_San SANBONG,\n" +
"		MIN(hd.NgayThanhToan) NgayDau,\n" +
"		MAX(hd.NgayThanhToan) NgayCuoi,\n" +
"		SUM(hd.TongTien) TongTien\n" +
"	FROM HOADON hd\n" +
"		JOIN HOADONCHITIET hdct ON hd.Id_HD=hdct.Id_HD\n" +
"		JOIN SANBONG sb ON sb.Id_San=hdct.Id_San\n" +
"	GROUP BY sb.Id_San";
    
    String SELECT_TONGSOLANDAT="SELECT\n" +
"		hd.SoDienThoai,\n" +
"		hd.HoTenKH,\n" +
"		kh.NgayDK,\n" +
"		COUNT(hd.Id_HD) TongSoLanDatSan\n" +
"	FROM HOADON hd JOIN KHACHHANG kh ON kh.Id_KH=hd.Id_KH\n" +
"	GROUP BY hd.SoDienThoai, hd.HoTenKH, kh.NgayDK";
     
    public List<Object[]> getDoanhThu(){
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = SELECT_DOANHTHU;
                rs = XJdbc.query(sql);
                while (rs.next()) {
                    Object[] model = {
                        rs.getString("SANBONG"),
                        rs.getDate("NgayDau"),
                        rs.getDate("NgayCuoi"),
                        rs.getFloat("TongTien")
                    };
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }
    public List<Object[]> getTongSoLanDat(){
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = SELECT_TONGSOLANDAT;
                rs = XJdbc.query(sql);
                while (rs.next()) {
                    Object[] model = {
                        rs.getString("SoDienThoai"),
                        rs.getString("HoTenKH"),
                        rs.getDate("NgayDK"),
                        rs.getInt("TongSoLanDatSan")
                    };
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }
    public List<Object[]> getTimKH(String hoten) {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_TimKH (?)}";
                rs = XJdbc.query(sql, hoten);
                while (rs.next()) {
                    Object[] model = {
                        rs.getString("SoDienThoai"),
                        rs.getString("HoTenKH"),
                        rs.getDate("NgayDK"),
                        rs.getInt("TongSoLanDatSan")
                    };
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }
    
}
