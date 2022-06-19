/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBContext;
import entity.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class CartDAO {
    ResultSet rs = null; //Nhận kết quả trả về

    public List<Cart> getCart(int id) {
        List<Cart> list = new ArrayList<>();
        String query = "select product.id, product.name, product.image, product.price, product.title, product.description, Cart.Amount\n"
                + "from Cart inner join product\n"
                + "on Cart.ProductID = product.id\n"
                + "where Cart.AccountID = ?";
        try (
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ) {
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),rs.getString(6));
                int amount = rs.getInt(7);
                list.add(new Cart(p, amount));
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        
        return list;
    }

    public int countCart(int id) {
        int count = 0;
        String query = "select count(*)\n"
                + "from Cart inner join product \n"
                + "on Cart.ProductID = product.id\n"
                + "where Cart.AccountID = ?";
        try (
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ) {
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return count;
    }

    public int countAllCart() {
        int count = 0;
        String query = "select count(*)\n"
                + "from Cart group by AccountID";
        try (
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ) {
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return count;
    }

    //Function returns a boolean to inform whether the product is added to cart or not
    //If Product is out of stock
    public boolean addToCart(int userID, int productID, int amount) {
        //Check if Product to add already has at least 1 in cart
        CartDAO dao = new CartDAO();

        //Before add to cart: check if product is out of stock
        if (countAmountProduct(productID) == 0) {
            return false;
        } else {
            List<Cart> list = dao.getCart(userID);
            for (Cart cart : list) {
                if (cart.getP().getId() == productID) {
                    String query = "update Cart\n"
                            + "set Amount = ?\n"
                            + "where AccountID = ? and ProductID = ?";
                    try (
                        Connection conn = new DBContext().getConnection();
                        PreparedStatement ps = conn.prepareStatement(query);
                    ) {
                        ps.setInt(1, cart.getAmount() + amount);
                        ps.setInt(2, userID);
                        ps.setInt(3, productID);
                        ps.executeUpdate();
                    } catch (Exception e) {
                    }
                if(amount==1)
                    //Call to delete1amount
                    dao.delete1Amount(productID);
                else
                    dao.delete1Amount1(productID,amount);
                    return true;
                }
            }
            //Else: Add 1 product into cart
            String query = "INSERT INTO Cart VALUES (?, ?, ?);";
            try (
                Connection conn = new DBContext().getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
            ) {
                ps.setInt(1, userID);
                ps.setInt(2, productID);
                ps.setInt(3, amount);
                ps.executeUpdate();
            } catch (Exception e) {
            }
            if(amount==1)
                dao.delete1Amount(productID);
            else
                dao.delete1Amount1(productID,amount);
            return true;
        }
    }

    //Minus 1 amount from Product after 1 Customer Add to cart
    public void delete1Amount(int ProductID) {
        String query = "update product\n"
                + "set amount = amount - 1\n"
                + "where id = ?";
        try (
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ) {
            ps.setInt(1, ProductID);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
public void delete1Amount1(int ProductID, int amount) {
        String query = "update product\n"
                + "set amount = amount - ?\n"
                + "where id = ?";
        try (
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ) {
            ps.setInt(1, amount);
            ps.setInt(2, ProductID);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    //Count the amount of a product to see if it is out of stock
    public int countAmountProduct(int ProductID) {
        String query = "select amount\n"
                + "from product\n"
                + "where id = ?";
        try (
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ) {
            ps.setInt(1, ProductID);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public int countNumCart(int userID) {
        String query = "select count(*) from Cart where AccountID = ?";
        try (
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ) {
            
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void deleteCart(int UserID) {
        CartDAO dao = new CartDAO();
        String query = "delete from Cart where AccountID = ?";
        try (
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ) {
            List<Cart> list = dao.getCart(UserID);
            for (Cart cart : list) {
                backProduct(UserID, cart.getP().getId());
            }
            ps.setInt(1, UserID);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    public void deleteCart1(int UserID) {
        CartDAO dao = new CartDAO();
        String query = "delete from Cart where AccountID = ?";
        try (
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ) {
            ps.setInt(1, UserID);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    public void backProduct(int UserID, int ProductID){
        int amount=0;
        String query = "select Amount from cart where AccountID = ? and ProductID = ?";
        try (
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ) {
            ps.setInt(1, UserID);
            ps.setInt(2, ProductID);
            rs = ps.executeQuery();
            rs.next();
            amount = rs.getInt(1);
        } catch (Exception e) {
        }
        
        query = "select amount from product where id = ?;";
        try (
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ) {
            ps.setInt(1, ProductID);
            rs = ps.executeQuery(   );
            rs.next();
            amount += rs.getInt(1);
        } catch (Exception e) {
        }
        
        query = "update product set amount = ? where id =?;";
        try (
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ) {
            ps.setInt(1, amount);
            ps.setInt(2, ProductID);
             ps.executeUpdate();
        } catch (Exception e) {
        }
        
    }
    
    public void back1Product(int UserID, int ProductID){
        int amount=0;
        String query = "select Amount from cart where AccountID = ? and ProductID = ?";
        try (
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ) {
            ps.setInt(1, UserID);
            ps.setInt(2, ProductID);
            rs = ps.executeQuery();
            rs.next();
            amount = rs.getInt(1);
        } catch (Exception e) {
        }
        query = "update Cart set Amount = ? where AccountID = ? and ProductID = ?;";
        try (
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ) {
            ps.setInt(1, amount - 1);
            ps.setInt(2, UserID);
            ps.setInt(3, ProductID);
            ps.executeUpdate();
        } catch (Exception e) {
        }
        query = "select amount from product where id = ?;";
        try (
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ) {
            ps.setInt(1, ProductID);
            rs = ps.executeQuery(   );
            rs.next();
            amount = rs.getInt(1);
        } catch (Exception e) {
        }
        
        query = "update product set amount = ? where id =?;";
        try (
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ) {
            ps.setInt(1, amount + 1);
            ps.setInt(2, ProductID);
             ps.executeUpdate();
        } catch (Exception e) {
        }
        
    }
    public void deleteProductCart(int UserID, int ProductID) {
        
        backProduct(UserID, ProductID);
        String query = "delete from cart where AccountID = ? and ProductID = ?";
        try (
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ) {
            ps.setInt(1, UserID);
            ps.setInt(2, ProductID);
             ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        CartDAO CartDAO = new CartDAO();
        System.out.println(CartDAO.countAllCart());
    }
}
