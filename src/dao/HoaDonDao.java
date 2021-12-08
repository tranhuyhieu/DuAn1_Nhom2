/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Entity.HoaDon;
import Entity.HoaDonChiTiet;
import utils.XDate;
import utils.XJdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author PC
 */
public class HoaDonDao extends DuAnDao<HoaDon, String>{

    String INSERT_SQL = "INSERT INTO HOADON(Id_NV ,Id_KH,HoTenKH,SoDienThoai,Email,AnhDatCoc,GiamGia,TongTien,NgayThanhToan,TrangThaiHD,TrangThaiTT)VALUES(?,?,?,?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE HOADON SET Id_NV=? ,Id_KH=?,HoTenKH=?,SoDienThoai=?,Email=?,AnhDatCoc=?,GiamGia=?,TongTien=?,NgayThanhToan=?,TrangThaiHD=?,TrangThaiTT=? WHERE Id_HD=?";
    String DELETE_SQL = "DELETE FROM HOADON WHERE Id_HD=?";
    String SELECT_ALL_SQL = "SELECT*FROM HOADON";
    String SELECT_BY_ID_SQL = "SELECT *FROM HOADON WHERE Id_HD=?";
    String SELECT_BY_ID = "SELECT * FROM HOADON WHERE EMAIL = ?";
    String SELECT_ALL_BY_TTHD_SQL = "SELECT*FROM HOADON where TrangThaiHD = ?";
    @Override
    public void insert(HoaDon entity) {
        XJdbc.update(INSERT_SQL, entity.getMaNV(), entity.getMaKH(), entity.getHoTenKH(), entity.getSoDienThoai(),entity.getEmail(), entity.getAnhDatCoc(),entity.getGiamGia(),entity.getTongTien(),entity.getNgayThanhToan(),entity.getTrangThaiHD(),entity.getTrangThaiTT()); 
    }

    @Override
    public void update(HoaDon entity) {
        XJdbc.update(UPDATE_SQL, entity.getMaNV(), entity.getMaKH(), entity.getHoTenKH(), entity.getSoDienThoai(),entity.getEmail(), entity.getAnhDatCoc(),entity.getGiamGia(),entity.getTongTien(),entity.getNgayThanhToan(),entity.getTrangThaiHD(),entity.getTrangThaiTT(), entity.getMaHD()); 
    }

    @Override
    public void delete(String id) {
        XJdbc.update(DELETE_SQL, id); 
    }

    @Override
    public List<HoaDon> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL); 
    }

    @Override
    public HoaDon selectById(String id) {
        List<HoaDon> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0); 
    }

    /**
     *
     * @param sql
     * @param args
     * @return
     */
    @Override
    public List<HoaDon> selectBySql(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while(rs.next()){
                HoaDon entity = new HoaDon();
                entity.setMaHD(rs.getString("Id_HD"));
                entity.setMaKH(rs.getString("Id_KH"));
                entity.setMaNV(rs.getString("Id_NV"));
                entity.setHoTenKH(rs.getString("HoTenKH"));
                entity.setSoDienThoai(rs.getString("SoDienThoai"));
                entity.setEmail(rs.getString("Email"));
                entity.setAnhDatCoc(rs.getString("AnhDatCoc"));
                entity.setGiamGia(rs.getFloat("GiamGia"));
                entity.setTongTien(rs.getFloat("TongTien"));
                entity.setNgayThanhToan(rs.getDate("NgayThanhToan"));
                entity.setTrangThaiHD(rs.getInt("TrangThaiHD"));
                entity.setTrangThaiTT(rs.getInt("TrangThaiTT"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
    public List<HoaDon> selectByIdNew(String id) {
        List<HoaDon> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list; 
    }
   public HoaDon selecthoaDon(String Email, Date NgayThanhToan){
       String sql = "select * from HOADON where Email = ? and NgayThanhToan =? and TrangThaiHD = 0" ;
       List<HoaDon> listhd = this.selectBySql(sql,Email,NgayThanhToan);
       if(listhd.isEmpty()){
            return null;
        }
        return listhd.get(0);
   }
   public HoaDon selectByEmailHoaDon(String Email){
       String sql = "select * from HOADON where Email = ?" ;
       List<HoaDon> listhd = this.selectBySql(sql,Email);
       if(listhd.isEmpty()){
            return null;
        }
        return listhd.get(0);
   }
    public List<HoaDon> selectByEmail(String email){
        String sql = "SELECT * FROM HOADON WHERE EMAIL = ?";
        return this.selectBySql(sql, email);
    }
    

}
