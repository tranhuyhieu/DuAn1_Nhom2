/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Entity.ThongTinNV;
import utils.XJdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class ThongTinNVDao extends DuAnDao<ThongTinNV, String>{
    String INSERT_SQL = "INSERT INTO ThongTinNV(Id_NV,Email,SoDT)VALUES(?,?,?)";
    String UPDATE_SQL = "UPDATE ThongTinNV SET Id_NV=?,Email=?,SoDT=? WHERE Id_ThongTin=?";
    String DELETE_SQL = "DELETE FROM ThongTinNV WHERE Id_ThongTin=?";
    String SELECT_ALL_SQL = "SELECT*FROM ThongTinNV";
    String SELECT_BY_ID_SQL = "SELECT*FROM ThongTinNV Where Id_ThongTin=?";
    String SELECT_BY_ID_NV = "SELECT*FROM ThongTinNV Where Id_NV=?";

    @Override
    public void insert(ThongTinNV entity) {
        XJdbc.update(INSERT_SQL, entity.getMaThongTin(), entity.getMaNV(), entity.getEmail(), entity.getSoDienThoai()); 
    }

    @Override
    public void update(ThongTinNV entity) {
        XJdbc.update(UPDATE_SQL, entity.getMaNV(), entity.getEmail(), entity.getSoDienThoai(), entity.getMaThongTin()); 
    }

    @Override
    public void delete(String id) {
        XJdbc.update(DELETE_SQL, id); 
    }

    @Override
    public List<ThongTinNV> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);  
    }

    @Override
    public ThongTinNV selectById(String id) {
        List<ThongTinNV> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);  
    }

    @Override
    protected List<ThongTinNV> selectBySql(String sql, Object... args) {
        List<ThongTinNV> list = new ArrayList<ThongTinNV>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while(rs.next()){
                ThongTinNV entity = new ThongTinNV();
                entity.setMaThongTin(rs.getInt("Id_ThongTin"));
                entity.setMaNV(rs.getString("Id_NV"));
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
    
    public List<ThongTinNV> selectEmail(String Id_NV){
        String sql="select * from THONGTINNV Where Id_NV =?";
        return this.selectBySql(sql, Id_NV);
    }
<<<<<<< Updated upstream
    public List<ThongTinNV> selectByIdKH(String id) {
=======
    public List<ThongTinNV> selectByIdNV(String id) {
>>>>>>> Stashed changes
        return this.selectBySql(SELECT_BY_ID_NV, id);  
    }
    
}
    


