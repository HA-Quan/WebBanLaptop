/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBContext;
import entity.Account;
import entity.Category;
import entity.Price;
import entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class DAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String query = "select * from product";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(9)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        String query = "select * from Category";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt(1),
                        rs.getString(2)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Product getLast() {
        String query = "select top 1 * from product\n"
                + "order by id desc";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),rs.getInt(9));
            }
        } catch (Exception e) {
        }
        return null;
    }
    public List<Product> getProductByCID(String cid) {
            List<Product> list = new ArrayList<>();
            String query = "select * from product\n"
                    + "where cateID = ?";
            try {
                conn = new DBContext().getConnection();//mo ket noi voi sql
                ps = conn.prepareStatement(query);
                ps.setString(1, cid);
                rs = ps.executeQuery();
                while (rs.next()) {
                    list.add(new Product(rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getInt(4),
                            rs.getString(5),
                            rs.getString(6),rs.getInt(9)));
                }
            } catch (Exception e) {
            }
            return list;
        }
    public List<Price> getPrice() {
        List<Price> list = new ArrayList<>();
        String query = "select * from Price";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Price(rs.getInt(1),
                        rs.getString(2)));
            }
        } catch (Exception e) {
        }
        return list;
    }
 public List<Product> searchPrice(String pid) {
            List<Product> list = new ArrayList<>();
            String query = "select * from product\n"
                    + "where priID = ?";
            try {
                conn = new DBContext().getConnection();//mo ket noi voi sql
                ps = conn.prepareStatement(query);
                ps.setString(1, pid);
                rs = ps.executeQuery();
                while (rs.next()) {
                    list.add(new Product(rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3), (int) rs.getDouble(4),
                            rs.getString(5),
                            rs.getString(6),rs.getInt(9)));
                }
            } catch (Exception e) {
            }
            return list;
        }
    public Product getProductByID(String id) {
            String query = "select * from product\n"
                    + "where id = ?";
            try {
                conn = new DBContext().getConnection();//mo ket noi voi sql
                ps = conn.prepareStatement(query);
                ps.setString(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    return new Product(rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getInt(4),
                            rs.getString(5),
                            rs.getString(6),rs.getInt(9));
                }
            } catch (Exception e) {
            }
            return null;
        }
    public List<Product> searchByName(String SearchP) {
            List<Product> list = new ArrayList<>();
            String query = "select * from product where [name] like ?";
            try {
                conn = new DBContext().getConnection();//mo ket noi voi sql
                ps = conn.prepareStatement(query);
                ps.setString(1, "%" + SearchP + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    list.add(new Product(rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getInt(4),
                            rs.getString(5),
                            rs.getString(6),rs.getInt(9)));
                }
            } catch (Exception e) {
            }
            return list;
        }
    public List<Product> getProductBySellID(int id) {
            List<Product> list = new ArrayList<>();
            String query = "select * from product\n"
                    + "where sell_ID = ?";
            try {
                conn = new DBContext().getConnection();//mo ket noi voi sql
                ps = conn.prepareStatement(query);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    list.add(new Product(rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getInt(4),
                            rs.getString(5),
                            rs.getString(6),rs.getInt(9)));
                }
            } catch (Exception e) {
            }
            return list;
        }
    public Account login(String user, String pass) {
            String query = "select * from account\n"
                    + "where [user] = ?\n"
                    + "and pass = ?";
            try {
                conn = new DBContext().getConnection();//mo ket noi voi sql
                ps = conn.prepareStatement(query);
                ps.setString(1, user);
                ps.setString(2, pass);
                rs = ps.executeQuery();
                while (rs.next()) {
                    return new Account(rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getInt(4),
                            rs.getInt(5));
                }
            } catch (Exception e) {
            }
            return null;
        }
    public Account checkAccountExist(String user) {
            String query = "select * from account\n"
                    + "where [user] = ?\n";
            try {
                conn = new DBContext().getConnection();//mo ket noi voi sql
                ps = conn.prepareStatement(query);
                ps.setString(1, user);
                rs = ps.executeQuery();
                while (rs.next()) {
                    return new Account(rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getInt(4),
                            rs.getInt(5));
                }
            } catch (Exception e) {
            }
            return null;
        }
    public void singup(String user, String pass) {
            String query = "insert into account\n"
                    + "values(?,?,0,0)";
            try {
                conn = new DBContext().getConnection();//mo ket noi voi sql
                ps = conn.prepareStatement(query);
                ps.setString(1, user);
                ps.setString(2, pass);
                ps.executeUpdate();
            } catch (Exception e) {
            }
        }
    public void deleteProduct(String pid) {
            String query = "delete from product\n"
                    + "where id = ?";
            try {
                conn = new DBContext().getConnection();//mo ket noi voi sql
                ps = conn.prepareStatement(query);
                ps.setString(1, pid);
                ps.executeUpdate();
            } catch (Exception e) {
            }
        }
    public void insertProduct(String name, String image, String price,
                String title, String description, String category, int sid,String amount) {
            String query = "INSERT [dbo].[product] \n"
                    + "([name], [image], [price], [title], [description], [cateID], [sell_ID],[amount])\n"
                    + "VALUES(?,?,?,?,?,?,?,?)";
            try {
                conn = new DBContext().getConnection();//mo ket noi voi sql
                ps = conn.prepareStatement(query);
                ps.setString(1, name);
                ps.setString(2, image);
                ps.setString(3, price);
                ps.setString(4, title);
                ps.setString(5, description);
                ps.setString(6, category);
                ps.setInt(7, sid);
                ps.setString(8, amount);
                ps.executeUpdate();
            } catch (Exception e) {
            }
        }
    public void editProduct(String name, String image, String price,
            String title, String description, String category, String amount ,String pid) {
        String query = "update product\n"
                + "set [name] = ?,\n"
                + "[image] = ?,\n"
                + "price = ?,\n"
                + "title = ?,\n"
                + "[description] = ?,\n"
                + "cateID = ?,\n"
                +"amount = ?\n"
                + "where id = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, image);
            ps.setString(3, price);
            ps.setString(4, title);
            ps.setString(5, description);
            ps.setString(6, category);
            ps.setString(7, amount);
            ps.setString(8, pid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    public int countProduct() {
            String query = "SELECT COUNT(*) FROM product";
            try {
                conn = new DBContext().getConnection();//mo ket noi voi sql
                ps = conn.prepareStatement(query);
                rs = ps.executeQuery();
                while (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (Exception e) {
            }
            return 0;
        }
    public int countProductCateID(int cateID) {
            String query = "SELECT COUNT(*) FROM product Where cateID = ?";
            try {
                conn = new DBContext().getConnection();//mo ket noi voi sql
                ps = conn.prepareStatement(query);
            ps.setInt(1, cateID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (Exception e) {
            }
            return 0;
        }
    public List<Product> pagingProductCateID(int IDcate,int index) {
            List<Product> list = new ArrayList<>();
            String query = "SELECT * FROM product Where cateID = ? ORDER BY id OFFSET ? ROWS FETCH NEXT 3  ROWS ONLY";
            try {
                conn = new DBContext().getConnection();//mo ket noi voi sql
                ps = conn.prepareStatement(query);
                ps.setInt(1, IDcate);
                ps.setInt(2, (index - 1) * 3);
                rs = ps.executeQuery();
                while (rs.next()) {
                    list.add(new Product(rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getInt(4),
                            rs.getString(5),
                            rs.getString(6),rs.getInt(9)));
                }
            } catch (Exception e) {
            }
            return list;
        }

    public List<Product> pagingProduct(int index) {
            List<Product> list = new ArrayList<>();
            String query = "SELECT * FROM product ORDER BY id OFFSET ? ROWS FETCH NEXT 3  ROWS ONLY";
            try {
                conn = new DBContext().getConnection();//mo ket noi voi sql
                ps = conn.prepareStatement(query);
                ps.setInt(1, (index - 1) * 3);
                rs = ps.executeQuery();
                while (rs.next()) {
                    list.add(new Product(rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getInt(4),
                            rs.getString(5),
                            rs.getString(6),rs.getInt(9)));
                }
            } catch (Exception e) {
            }
            return list;
        }
    public static void main(String[] args) {
        DAO dao = new DAO();
        List<Product> list = dao.getAllProduct();
        List<Category> listC = dao.getAllCategory();

        for (Category o : listC) {
            System.out.println(o);
        }
        List<Price> listP = dao.getPrice();

        for (Price o : listP) {
            System.out.println(o);
        }
    }

}
