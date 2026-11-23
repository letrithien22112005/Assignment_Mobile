/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sample.dto.UserDTO;
import sample.utils.DBUtils;

/**
 *
 * @author Owner
 */
public class UserDAO {
    private static final String LOGIN = "SELECT * FROM Users WHERE userId = ? AND password = ?";
    public UserDTO checkLogin(String id, String pass) throws SQLException {
        UserDTO u = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
             con = DBUtils.getConnection();
            if(con != null){
                ps =  con.prepareStatement(LOGIN);
                ps.setString(1, id);
                ps.setString(2, pass);
                rs = ps.executeQuery();
                if(rs.next()){
                    String idSQL = rs.getString("userId");
                    int passSQL = rs.getInt("password");
                    String nameSQL = rs.getString("fullName");
                    int roleSQL = rs.getInt("role");
                    u = new UserDTO(idSQL, passSQL, nameSQL, roleSQL);
                }
            }
        } catch (Exception e) {
            System.out.println("Error at checkLoin.");
        } finally {
            if(rs != null) rs.close();
            if(ps != null) ps.close();
            if(con != null) con.close();
        }
        return u;
    }
}
