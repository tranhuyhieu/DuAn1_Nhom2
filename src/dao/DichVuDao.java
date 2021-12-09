/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Entity.DichVu;
import Entity.HoaDonChiTiet;
import utils.XJdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class DichVuDao extends DuAnDao<DichVu, Integer>{
    String INSERT_SQL = "INSERT INTO DichVu(Id_HDCT,TenDV,SoLuong,GiaTien,TrangThaiDV)VALUES(?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE DichVu SET Id_HDCT=?,TenDV=?,SoLuong=?,GiaTien=?,TrangThaiDV=? WHERE Id_DV=?";
    String DELETE_SQL = "DELETE FROM DichVu WHERE Id_DV=?";
    String SELECT_ALL_SQL = "SELECT*FROM DichVu";
    String SELECT_BY_ID_SQL = "SELECT*FROM DichVu WHERE Id_DV=?";
    String SELECT_BY_ID_HDCT = "SELECT*FROM DICHVU Where Id_HDCT=?";

    @Override
    public void insert(DichVu entity) {
        XJdbc.update(INSERT_SQL, entity.getMaHDCT(), entity.getTenDV(), entity.getSoLuong(), entity.getGiaTien(), entity.getTrangThaiDichVu()); 
    }

    @Override
    public void update(DichVu entity) {
        XJdbc.update(UPDATE_SQL, entity.getMaHDCT(), entity.getTenDV(), entity.getSoLuong(), entity.getGiaTien(),entity.getTrangThaiDichVu(), entity.getMaDV()); 
    }

    @Override
    public void delete(Integer id) {
        XJdbc.update(DELETE_SQL, id); 
    }

    @Override
    public List<DichVu> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL); 
    }

    @Override
    public DichVu selectById(Integer id) {
        List<DichVu> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0); 
    }

    @Override
    protected List<DichVu> selectBySql(String sql, Object... args) {
        List<DichVu> list = new ArrayList<DichVu>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while(rs.next()){
                DichVu entity = new DichVu();
                entity.setMaDV(rs.getInt("Id_DV"));
                entity.setMaHDCT(rs.getInt("Id_HDCT"));   
                entity.setTenDV(rs.getString("TenDV"));
                entity.setSoLuong(rs.getInt("SoLuong"));
                entity.setGiaTien(rs.getFloat("GiaTien"));
                entity.setTrangThaiDichVu(rs.getBoolean("TrangThaiDV"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
    public List<DichVu> selectByIdHDCT(Integer id) {

        return this.selectBySql(SELECT_BY_ID_HDCT, id);  
    }
}
