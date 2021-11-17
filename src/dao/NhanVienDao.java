/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Entity.NhanVien;
import utils.XJdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class NhanVienDao extends DuAnDao<NhanVien, String>{

    String INSERT_SQL = "INSERT INTO NHANVIEN(Id_NV, MatKhau, VaiTro, HoTen, DiaChi, TrangThai) VALUES(?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE NHANVIEN SET MatKhau=?, VaiTro=?, HoTen=?, DiaChi=?, TrangThai=? WHERE Id_NV=?";
    String DELETE_SQL = "DELETE FROM NHANVIEN WHERE Id_NV=?";
    String SELECT_ALL_SQL = "SELECT*FROM NHANVIEN";
    String SELECT_BY_ID_SQL = "SELECT*FROM NHANVIEN WHERE Id_NV=?";
    
    @Override
    public void insert(NhanVien entity) {
        XJdbc.update(INSERT_SQL, entity.getMaNV(), entity.getMatKhau(), entity.getVaiTro(), entity.getHoTen(), entity.getDiaChi(), entity.getTrangThai());
    }

    @Override
    public void update(NhanVien entity) {
        XJdbc.update(UPDATE_SQL, entity.getMatKhau(), entity.getVaiTro(), entity.getHoTen(), entity.getDiaChi(), entity.getTrangThai(), entity.getMaNV());
    }

    @Override
    public void delete(String id) {
        XJdbc.update(DELETE_SQL, id); 
    }

    @Override
    public List<NhanVien> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public NhanVien selectById(String id) {
        List<NhanVien> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0); 
    }

    
    @Override
    protected List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<NhanVien>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while(rs.next()){
                NhanVien entity = new NhanVien();
                entity.setMaNV(rs.getString("Id_NV"));
                entity.setMatKhau(rs.getString("MatKhau"));
                entity.setVaiTro(rs.getBoolean("VaiTro"));
                entity.setHoTen(rs.getString("HoTen"));
                entity.setDiaChi(rs.getString("DiaChi"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
