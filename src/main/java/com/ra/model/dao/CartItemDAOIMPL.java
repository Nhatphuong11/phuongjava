package com.ra.model.dao;
import com.ra.util.ConnectionDB;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

@Repository
public class CartItemDAOIMPL implements CartItemDAO{
//    @Autowired
//    private ProductDAO productDAO ;
//    @Autowired
//    private CartDAO cartDAO;
//    @Autowired
//    private HttpSession httpSession;
//    @Override
//    public List<CartItem> findAll(Integer idCart) {
//        Connection connection = null;
//        List<CartItem> list = new ArrayList<>();
//        try {
//            connection = ConnectionDB.openConnection();
//            CallableStatement callableStatement = connection.prepareCall("{CALL PROC_FIND_CART_ITEM_BY_CART(?)}");
//            callableStatement.setInt(1, idCart);
//            ResultSet rs = callableStatement.executeQuery();
//            while (rs.next()) {
//                CartItem cartItem = new CartItem();
//                cartItem.setId(rs.getInt("cartId"));
//                Product product = productDAO.findById(rs.getInt(rs.getInt("productId")));
//                cartItem.setProduct(product); ;
//                cartItem.setQuantity(rs.getInt("quantity"));
//                ResponseUserLoginDTO userLogin = (ResponseUserLoginDTO) httpSession.getAttribute("user") ;
//                Cart cart = cartDAO.findByIdUser(userLogin.getUserId());
//                cartItem.setCart(cart);
//                list.add(cartItem) ;
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            ConnectionDB.closeConnection(connection);
//        }
//        return list ;
//    }
//
//    @Override
//    public CartItem findById(Integer id) {
//        Connection connection = null;
//        CartItem cartItem = new CartItem() ;
//        try {
//            connection = ConnectionDB.openConnection();
//            CallableStatement callableStatement = connection.prepareCall("{CALL PROC_FIND_BY_ID_CART_ITEM(?)}");
//            callableStatement.setInt(1, id);
//            ResultSet rs = callableStatement.executeQuery();
//            while (rs.next()) {
//                cartItem.setId(rs.getInt("cartId"));
//                Product product = productDAO.findById(rs.getInt("productId"));
//                cartItem.setProduct(product); ;
//                cartItem.setQuantity(rs.getInt("quantity"));
//                ResponseUserLoginDTO userLogin = (ResponseUserLoginDTO) httpSession.getAttribute("user") ;
//                Cart cart = cartDAO.findByIdUser(userLogin.getUserId());
//                cartItem.setCart(cart);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            ConnectionDB.closeConnection(connection);
//        }
//        return cartItem ;
//    }
//
//    @Override
//    public Boolean create(CartItem item) {
//        Boolean isCheck = false;
//        Connection connection = null;
//        try {
//            connection = ConnectionDB.openConnection();
//            CallableStatement callableStatement = connection.prepareCall("{call PROC_CREATE_CART_ITEM(?,?,?)}");
//            callableStatement.setInt(1, item.getProduct().getProductId());
//            callableStatement.setInt(2, item.getQuantity());
//            callableStatement.setInt(3,item.getCart().getId());
//            int check = callableStatement.executeUpdate();
//            if (check > 0 ) {
//                isCheck = true ;
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }finally {
//            ConnectionDB.closeConnection(connection);
//        }
//        return isCheck;
//    }
//
//    @Override
//    public Boolean update(Integer qty, Integer id) {
//        Boolean isCheck = false;
//        Connection connection = null;
//        try {
//            connection = ConnectionDB.openConnection();
//            CallableStatement callableStatement = connection.prepareCall("{call PROC_UPDATE_QUANTITY_CART_ITEM(?,?)}");
//            callableStatement.setInt(1, qty );
//            callableStatement.setInt(2,id);
//            int check = callableStatement.executeUpdate();
//            if (check > 0 ) {
//                isCheck = true ;
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }finally {
//            ConnectionDB.closeConnection(connection);
//        }
//        return isCheck;
//    }
//
    @Override
    public Boolean delete(Integer id) {
        Boolean isCheck = false;
        Connection connection = null;
        try {
            connection = ConnectionDB.openConnection();
            CallableStatement callableStatement = connection.prepareCall("{call PROC_DELETE_CART_ITEM(?)}");
            callableStatement.setInt(1,id);
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
}
