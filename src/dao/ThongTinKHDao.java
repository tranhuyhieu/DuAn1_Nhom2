/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Entity.ThongTinKH;
import Entity.ThongTinNV;
import utils.XJdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class ThongTinKHDao extends DuAnDao<ThongTinKH, String>{
    String INSERT_SQL = "INSERT INTO ThongTinKH(Id_ThongTin,Id_KH,Email,SoDT)VALUES(?,?,?,?)";
    String UPDATE_SQL = "UPDATE ThongTinNV SET Id_KH=?,Email=?,SoDT=? WHERE Id_ThongTin=?";
    String DELETE_SQL = "DELETE FROM ThongTinNV WHERE Id_ThongTin=?";
    String SELECT_ALL_SQL = "SELECT*FROM ThongTinKH";
    String SELECT_BY_ID_SQL = "SELECT*FROM ThongTinNV Id_ThongTin=?";

    @Override
    public void insert(ThongTinKH entity) {
        XJdbc.update(INSERT_SQL, entity.getMaThongTin(), entity.getMaKH(), entity.getEmail(), entity.getSoDienThoai()); 
    }

    @Override
    public void update(ThongTinKH entity) {
        XJdbc.update(INSERT_SQL, entity.getMaKH(), entity.getEmail(), entity.getSoDienThoai(), entity.getMaThongTin()); 
    }

    @Override
    public void delete(String id) {
        XJdbc.update(DELETE_SQL, id); 

    }

    @Override
    public List<ThongTinKH> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);  
    }

    @Override
    public ThongTinKH selectById(String id) {
        List<ThongTinKH> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);  
    }

    @Override
    protected List<ThongTinKH> selectBySql(String sql, Object... args) {
        List<ThongTinKH> list = new ArrayList<ThongTinKH>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while(rs.next()){
                ThongTinKH entity = new ThongTinKH();
                entity.setMaThongTin(rs.getInt("Id_ThongTin"));
                entity.setMaKH(rs.getString("Id_KH"));
                entity.setEmail(rs.getString("Email"));
                entity.setSoDienThoai(rs.getString("SoDT"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    

}
