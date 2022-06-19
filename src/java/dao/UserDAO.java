/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBContext;
import entity.Account;
import entity.Information;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class UserDAO {

    ResultSet rs = null; //Nhận kết quả trả về

    public void addInfor(String fname, String lname, String email, String address, int uid) {
        String query = "INSERT INTO user_infor values(?,?,?,?,?,?)";
        try (
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ) {
            ps.setInt(1, uid);
            ps.setString(2, fname);
            ps.setString(3, lname);
            ps.setString(4, email);
            ps.setString(5, address);
            
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        UserDAO UserDAO = new UserDAO();

    }
}
