/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import sample.dto.MobileDTO;
import sample.utils.DBUtils;

/**
 *
 * @author Admin
 */
public class MobileDAO {

//    private static final String SEARCH = "SELECT * FROM Mobile WHERE mobileId = ? OR mobileName LIKE ?";
    private static final String VIEW = "SELECT * FROM Mobile WHERE mobileId = ?";
    private static final String DELETE = "DELETE Mobile WHERE mobileId = ?";
    private static final String UPDATE = "UPDATE Mobile SET price = ?,description = ?, quantity = ?, notSale = ? WHERE mobileId = ?";
//    private static final String CHECK_ID = "SELECT id FROM PhoneProject WHERE id = ?";
    private static final String ADD = "INSERT INTO Mobile (mobileId, description, price, mobileName, yearOfProduction, quantity, notSale)"
            + "VALUES (?, ?, ?, ?, ?, ?, ?)";

    public boolean addMobile(MobileDTO m) throws SQLException { // toi da la 1 (null)// gon vo arraylist
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ps = con.prepareStatement(ADD);
                ps.setString(1, m.getMobileId());
                ps.setString(2, m.getDescription());
                ps.setFloat(3, m.getPrice());
                ps.setString(4, m.getFullName());
                ps.setInt(5, m.getYearOfProduction());
                ps.setInt(6, m.getQuantity());
                ps.setBoolean(7, m.isNotSale());
                result = ps.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            System.out.println("Error at addMobile.");
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public boolean updateMobile(MobileDTO m) throws SQLException { // toi da la 1 (null)// gon vo arraylist
        boolean result = false;
        Connection con = null;
        PreparedStatement ps = null;
        // dieu kien ? abc : xyz
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ps = con.prepareStatement(UPDATE);
                ps.setFloat(1, m.getPrice());
                ps.setString(2, m.getDescription());
                ps.setInt(3, m.getQuantity());
                ps.setBoolean(4, m.isNotSale());
                ps.setString(5, m.getMobileId());
                result = ps.executeUpdate() > 0 ? true : false;
//                int a = ps.executeUpdate();
//                if(a > 0){
//                    result = true;
//                }
            }
        } catch (Exception e) {
            System.out.println("Error at updateMobile.");
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public boolean deleteMobile(String id) throws SQLException { // toi da la 1 (null)// gon vo arraylist
        boolean result = false;
        Connection con = null;
        PreparedStatement ps = null;
        // dieu kien ? abc : xyz
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ps = con.prepareStatement(DELETE);
                ps.setString(1, id);
                result = ps.executeUpdate() > 0 ? true : false;
//                int a = ps.executeUpdate();
//                if(a > 0){
//                    result = true;
//                }
            }
        } catch (Exception e) {
            System.out.println("Error at deleteMobile.");
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public MobileDTO getMobileByID(String id) throws SQLException { // toi da la 1 (null)// gon vo arraylist
        MobileDTO m = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ps = con.prepareStatement(VIEW);
                ps.setString(1, id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String idSQL = rs.getString("mobileId");
                    String descriptionSQL = rs.getString("description");
                    String nameSQL = rs.getString("mobileName");
                    float price = rs.getFloat("price");
                    int year = rs.getInt("yearOfProduction");
                    int quantity = rs.getInt("quantity");
                    boolean sale = rs.getBoolean("notSale");
                    m = new MobileDTO(id, descriptionSQL, price, nameSQL, year, quantity, sale);
                }
            }
        } catch (Exception e) {
            System.out.println("Error at getMobileByID.");
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return m;
    }

    //SELECT * FROM Mobile WHERE 
    public ArrayList<MobileDTO> searchMobile(String search, String min, String max) throws SQLException {
        // 2 th:
        //1. Staff: min, max: null:   mobileId = ? OR mobileName LIKE ?
        //2. User:                      price >= ? AND price <= ?
        ArrayList<MobileDTO> listMobile = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String query = "SELECT * FROM Mobile WHERE ";
                if (min == null || max == null) {
                    query = query + "mobileId = ? OR mobileName LIKE ?";
                    ps = con.prepareStatement(query);
                    String x = "%" + search + "%";
                    ps.setString(1, search);
                    ps.setString(2, x);
                    rs = ps.executeQuery();
                }
                if (min != null && max != null) {
                    query = query + " price >= ? AND price <= ?";
                    ps = con.prepareStatement(query);
                    ps.setFloat(1, Float.parseFloat(min));
                    ps.setFloat(2, Float.parseFloat(max));
                    rs = ps.executeQuery();
                }
                
                while (rs.next()) {
                    String idSQL = rs.getString("mobileId");
                    String descriptionSQL = rs.getString("description");
                    String nameSQL = rs.getString("mobileName");
                    float price = rs.getFloat("price");
                    int year = rs.getInt("yearOfProduction");
                    int quantity = rs.getInt("quantity");
                    boolean sale = rs.getBoolean("notSale");
                    MobileDTO mobile = new MobileDTO(idSQL, descriptionSQL, price, nameSQL, year, quantity, sale);
                    listMobile.add(mobile);
                }
            }
        } catch (Exception e) {
            System.out.println("Error at searchMobile.");
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return listMobile;
    }
}
