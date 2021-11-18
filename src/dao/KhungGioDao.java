/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Entity.DichVu;
import Entity.KhungGio;
import utils.XJdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class KhungGioDao extends DuAnDao<KhungGio, String>{
    String INSERT_SQL = "INSERT INTO KhungGio(Id_KG,KhungGio)VALUES(?,?)";
    String UPDATE_SQL = "UPDATE KhungGio SET KhungGio=? WHERE Id_KG=?";
    String DELETE_SQL = "DELETE FROM KhungGio WHERE Id_KG=?";
    String SELECT_ALL_SQL = "SELECT*FROM KhungGio";
    String SELECT_BY_ID_SQL = "SELECT*FROM KhungGio WHERE Id_KG=?";

    @Override
    public void insert(KhungGio entity) {
        XJdbc.update(INSERT_SQL, entity.getMaKG(), entity.getKhungGio());
    }

    @Override
    public void update(KhungGio entity) {
        XJdbc.update(UPDATE_SQL, entity.getMaKG(), entity.getKhungGio());
    }

    @Override
    public void delete(String id) {
        XJdbc.update(DELETE_SQL, id);
    }

    @Override
    public List<KhungGio> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL); 
    }

    @Override
    public KhungGio selectById(String id) {
        List<KhungGio> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0); 
    }

    @Override
    protected List<KhungGio> selectBySql(String sql, Object... args) {
        List<KhungGio> list = new ArrayList<KhungGio>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while(rs.next()){
                KhungGio entity = new KhungGio();
                entity.setMaKG(rs.getString("Id_KG"));
                entity.setKhungGio(rs.getString("KhungGio"));
                
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    
    
}
