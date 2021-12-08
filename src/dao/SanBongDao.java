/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Entity.SanBong;
import utils.XJdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class SanBongDao extends DuAnDao<SanBong, String>{
    String INSERT_SQL = "INSERT INTO SANBONG(Id_San,LoaiSan,TrangThaiSan,GiaSan)VALUES(?,?,?,?)";
    String UPDATE_SQL = "UPDATE SANBONG SET LoaiSan=?,TrangThaiSan=?,GiaSan=? WHERE Id_San=?";
    String DELETE_SQL = "DELETE FROM SANBONG WHERE Id_San=?";
    String SELECT_ALL_SQL = "SELECT*FROM SANBONG";
    String SELECT_BY_ID_SQL = "SELECT*FROM SANBONG WHERE Id_San=?";

    @Override
    public void insert(SanBong entity) {
        XJdbc.update(INSERT_SQL, entity.getMaSan(), entity.getLoaiSan(),entity.getTrangThaiSan(),entity.getGiaSan());
    }

    @Override
    public void update(SanBong entity) {
        XJdbc.update(UPDATE_SQL, entity.getLoaiSan(),entity.getTrangThaiSan(),entity.getGiaSan(),entity.getMaSan());
    }

    @Override
    public void delete(String id) {
        XJdbc.update(DELETE_SQL, id);
    }

    @Override
    public List<SanBong> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL); 
    }

    @Override
    public SanBong selectById(String id) {
        List<SanBong> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0); 
    }

    @Override
    protected List<SanBong> selectBySql(String sql, Object... args) {
        List<SanBong> list = new ArrayList<SanBong>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while(rs.next()){
                SanBong entity = new SanBong();
                entity.setMaSan(rs.getString("Id_San"));
                entity.setLoaiSan(rs.getString("LoaiSan"));
                entity.setTrangThaiSan(rs.getString("TrangThaiSan"));
                entity.setGiaSan(rs.getFloat("GiaSan"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
}
