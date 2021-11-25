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
import java.util.List;

/**
 *
 * @author PC
 */
public class HoaDonCTDao extends DuAnDao<HoaDonChiTiet, Integer>{
    String INSERT_SQL = "INSERT INTO HOADONCHITIET(Id_HDCT,Id_HD,Id_San,Id_KG,NgayDat,GiaTien,TrangThai)VALUES(?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE HOADONCHITIET SET Id_HD=?,Id_San=?,Id_KG=?,NgayDat=?,GiaTien=?,TrangThai=? WHERE Id_HDCT=?";
    String DELETE_SQL = "DELETE FROM HOADONCHITIET WHERE Id_HDCT=?";
    String SELECT_ALL_SQL = "SELECT*FROM HOADONCHITIET";
    String SELECT_BY_ID_SQL = "SELECT*FROM HOADONCHITIET WHERE Id_HDCT=?";

    @Override
    public void insert(HoaDonChiTiet entity) {
        XJdbc.update(INSERT_SQL, entity.getMaHDCT(), entity.getMaHD(), entity.getMaSan(), entity.getMaKG(), entity.getNgayDat(), entity.getGiaTien(), entity.getTrangThai()); 
    }

    @Override
    public void update(HoaDonChiTiet entity) {
        XJdbc.update(UPDATE_SQL, entity.getMaHD(), entity.getMaSan(), entity.getMaKG(), entity.getNgayDat(), entity.getGiaTien(), entity.getTrangThai(), entity.getMaHDCT()); 
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
                entity.setMaHD(rs.getString("Id_HD"));
                entity.setMaSan(rs.getString("Id_San"));
                entity.setMaKG(rs.getString("Id_KG"));
                entity.setNgayDat(rs.getDate("NgayDat"));
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
    
    public  List<HoaDonChiTiet> selectByKeyword(String keyword){
        String sql = "SELECT * FROM HOADONCHITIET WHERE Id_HD LIKE ?";
        return this.selectBySql(sql, "%"+keyword+"%");
    }
}
