/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Entity.HoaDonChiTiet;
import Entity.KhachHang;
import utils.XJdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author PC
 */
public class HoaDonCTDao extends DuAnDao<HoaDonChiTiet, Integer>{
    String INSERT_SQL = "INSERT INTO HOADONCHITIET(Id_HD,Id_San,Id_KG,NgayDat,GiaTien,TrangThai)VALUES(?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE HOADONCHITIET SET Id_HD=?,Id_San=?,Id_KG=?,NgayDat=?, TrongTai=?, GiaTien=?,TrangThai=? WHERE Id_HDCT=?";
    String DELETE_SQL = "DELETE FROM HOADONCHITIET WHERE Id_HDCT=?";
    String SELECT_ALL_SQL = "SELECT*FROM HOADONCHITIET";
    String SELECT_BY_ID_SQL = "SELECT*FROM HOADONCHITIET WHERE Id_HDCT=?";
    String SELECT_BY_ID_HD = "SELECT*FROM HOADONCHITIET Where Id_HD=?";

    @Override
    public void insert(HoaDonChiTiet entity) {
        XJdbc.update(INSERT_SQL, entity.getMaHD(), entity.getMaSan(), entity.getMaKG(), entity.getNgayDat(), entity.getGiaTien(), entity.getTrangThai()); 
    }

    @Override
    public void update(HoaDonChiTiet entity) {
        XJdbc.update(UPDATE_SQL, entity.getMaHD(), entity.getMaSan(), entity.getMaKG(), entity.getNgayDat(), entity.getTrongTai(), entity.getGiaTien(), entity.getTrangThai(), entity.getMaHDCT()); 
    }

    @Override
    public void delete(Integer id) {
        XJdbc.update(DELETE_SQL, id); 
    }

    @Override
    public List<HoaDonChiTiet> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL); 
    }

    @Override
    public HoaDonChiTiet selectById(Integer id) {
        List<HoaDonChiTiet> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0); 
    }

    @Override
    protected List<HoaDonChiTiet> selectBySql(String sql, Object... args) {
        List<HoaDonChiTiet> list = new ArrayList<HoaDonChiTiet>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while(rs.next()){
                HoaDonChiTiet entity = new HoaDonChiTiet();
                entity.setMaHDCT(rs.getInt("Id_HDCT"));
                entity.setMaHD(rs.getInt("Id_HD"));
                entity.setMaSan(rs.getString("Id_San"));
                entity.setMaKG(rs.getString("Id_KG"));
                entity.setNgayDat(rs.getDate("NgayDat"));
                entity.setTrongTai(rs.getString("TrongTai"));
                entity.setGiaTien(rs.getFloat("GiaTien"));
                entity.setTrangThai(rs.getInt("TrangThai"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
    public List<HoaDonChiTiet> selectHoaDonChiTietByHoaDon(String Email){
        String sql= "select HOADONCHITIET.* from HOADONCHITIET join HOADON on HOADON.Id_HD= HOADONCHITIET.Id_HD Where HOADON.Email= ?";
        return this.selectBySql(sql, Email);
    }
    
    public  List<HoaDonChiTiet> selectByKeyword(String keyword){
        String sql = "SELECT * FROM HOADONCHITIET WHERE Id_HD LIKE ?";
        return this.selectBySql(sql, "%"+keyword+"%");
    }
    public List<HoaDonChiTiet> selectHoaDonChiTiet(String Id_San,Date NgayThanhToan){
        String sql = "select HOADONCHITIET.* from HOADONCHITIET join HOADON on HOADONCHITIET.Id_HD = HOADON.Id_HD where HOADONCHITIET.Id_San = ? and HOADON.NgayThanhToan = ? and HOADONCHITIET.TrangThai = 0";
        return this.selectBySql(sql,Id_San,NgayThanhToan);
    }

    public double getTongTien(Object...args){
        double tien = 0;
        String sql = "select sum(GiaTien) as TongTien from HOADONCHITIET where ID_HD = ?";
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while(rs.next()){
                tien = rs.getFloat("TongTien");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tien;
    }
    
    public List<HoaDonChiTiet> selectByIdHD(Integer id) {
        return this.selectBySql(SELECT_BY_ID_HD, id);  
    }
    public int selectMaxIdHoaDon(String Email){
        int Id_HD = 0 ;
        String sql = "select MAX(Id_HD) from HOADON where Email = ?";
        try {
            ResultSet rs = XJdbc.query(sql,Email);
            while (rs.next()) {                
                Id_HD = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Id_HD ;
    }
}
