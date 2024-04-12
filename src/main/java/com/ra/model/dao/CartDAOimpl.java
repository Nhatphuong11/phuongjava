package com.ra.model.dao;

import com.ra.model.entity.Cart;
import com.ra.model.entity.User;
import com.ra.util.ConnectionDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class CartDAOimpl implements CartDAO{
    @Autowired
    private UserDAO userDAO;
    @Override
    public Boolean addCart(Cart cart) {
        Boolean isCheck = false;
        Connection connection = null;
        try {
            connection = ConnectionDB.openConnection();
            CallableStatement callableStatement = connection.prepareCall("{call PROC_CREATE_CART(?)}");
            callableStatement.setInt(1, cart.getUser().getUserId());
            callableStatement.setDouble(2,0);
            callableStatement.setInt(3,0);
            int check = callableStatement.executeUpdate();
            if (check > 0 ) {
                isCheck = true ;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(connection);
        }
        return isCheck;
    }

    @Override
    public Cart findByIdUser(Integer id) {
        Connection connection = null;
        Cart cart = new Cart() ;
        try {
            connection = ConnectionDB.openConnection();
            CallableStatement callableStatement = connection.prepareCall("{CALL PROC_FIND_BY_ID_CART(?)}");
            callableStatement.setInt(1,id);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                cart.setId(rs.getInt("cartId"));
                User user = userDAO.findById(rs.getInt("userId")) ;
                cart.setUser(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return cart;
    }
}
