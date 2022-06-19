package dao;

import context.DBContext;
import entity.Account;
import entity.Bill;
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
public class BillDAO {

    ResultSet rs = null; //Nhận kết quả trả về

    public void addBill(int idAcc, String nameP, String image, int amount, int total) {
        String query = "INSERT INTO Bill values(?,?,?,?,?)";
        try (
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ) {
            ps.setInt(1, idAcc);
            ps.setString(2, nameP);
            ps.setString(3, image);
            ps.setInt(4, amount);
            ps.setInt(5, total);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    public List<Bill> getBill(int id) {
        List<Bill> list = new ArrayList<>();
        String query = "select * from Bill\n"
                    + "where idAcc = ?";
        try (
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ) {
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Bill b = new Bill(rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
                list.add(b);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        
        return list;
    }
    public static void main(String[] args) {
        BillDAO UserDAO = new BillDAO();

    }
}