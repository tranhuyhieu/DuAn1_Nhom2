/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import utils.XJdbc;
import Entity.KhachHang;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class KhachHangDao extends DuAnDao<KhachHang, String>{
    String INSERT_SQL = "INSERT INTO KHACHHANG(Id_KH,Id_NV,MatKhau,NgayDK,HoTen)VALUES(?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE KHACHHANG SET Id_NV=?,MatKhau=?,NgayDK=?,HoTen=? WHERE Id_KH=?";
    String DELETE_SQL = "DELETE FROM KHACHHANG WHERE Id_KH=?";
    String SELECT_ALL_SQL = "SELECT*FROM KHACHHANG";
    String SELECT_BY_ID_SQL = "SELECT*FROM KHACHHANG WHERE Id_KH=?";
    String SELECT_BY_EMAIL_SQL = "select KHACHHANG.Id_KH,KHACHHANG.Id_NV,MatKhau,NgayDK,HoTen from KHACHHANG \n" +
"right join THONGTINKH on KHACHHANG.Id_KH = THONGTINKH.Id_KH\n" +
"where  THONGTINKH.Email like ?";

    @Override
    public void insert(KhachHang entity) {
       XJdbc.update(INSERT_SQL, entity.getMaKH(), entity.getMaNV(), entity.getMatKhau(), entity.getNgayDK(), entity.getHoTen()); 
    }

    @Override
    public void update(KhachHang entity) {
        XJdbc.update(UPDATE_SQL, entity.getMaNV(), entity.getMatKhau(), entity.getNgayDK(), entity.getHoTen(), entity.getMaKH()); 
    }

    @Override
    public void delete(String id) {
        XJdbc.update(DELETE_SQL, id); 
    }

    @Override
    public List<KhachHang> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL); 
    }

    @Override
    public KhachHang selectById(String id) {
        List<KhachHang> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0); 
    }

    @Override
    protected List<KhachHang> selectBySql(String sql, Object... args) {
        List<KhachHang> list = new ArrayList<KhachHang>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while(rs.next()){
                KhachHang entity = new KhachHang();
                entity.setMaKH(rs.getString("Id_KH"));
                entity.setMaNV(rs.getString("Id_NV"));
                entity.setMatKhau(rs.getString("MatKhau"));
                entity.setNgayDK(rs.getDate("NgayDK"));
                entity.setHoTen(rs.getString("HoTen"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public KhachHang selectByEmail(String id) {
        List<KhachHang> list = this.selectBySql(SELECT_BY_EMAIL_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0); 
    }
    }
    

