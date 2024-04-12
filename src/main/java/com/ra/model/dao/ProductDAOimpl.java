package com.ra.model.dao;
import com.ra.model.entity.Category;
import com.ra.model.entity.Product;
import com.ra.util.ConnectionDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Repository
public class ProductDAOimpl implements ProductDAO{
    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    public List<Product> findAll() {
        Connection connection = null ;
        List<Product> list = new ArrayList<>();

        try {
            connection = ConnectionDB.openConnection();
            CallableStatement callableStatement = connection.prepareCall("{CALL PROC_FIND_ALL_PRODUCT()}");
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("productID"));
                product.setProductName(rs.getString("productName"));
                Category category = categoryDAO.findById(rs.getInt("categoryId"));
                product.setCategory(category);
                product.setImage(rs.getString("image"));
                product.setPrice(rs.getDouble("price"));
                product.setDescription(rs.getString("description"));
                product.setStock(rs.getInt("stock"));
                product.setStatus(rs.getBoolean("status"));
                list.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return list;
    }

    @Override
    public Product findById(Integer id) {
        Connection connection = null ;
        Product product = new Product();
        try {
            connection = ConnectionDB.openConnection();
            CallableStatement callableStatement = connection.prepareCall("{CALL PROC_FIND_By_ID_PRODUCT(?)}");
            callableStatement.setInt(1,id);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                product.setProductId(rs.getInt("productId"));
                product.setProductName(rs.getString("productName"));
                Category category = categoryDAO.findById(rs.getInt("categoryID"));
                product.setCategory(category);
                product.setImage(rs.getString("image"));
                product.setPrice(rs.getDouble("price"));
                product.setDescription(rs.getString("description"));
                product.setStock(rs.getInt("stock"));
                product.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return product;
    }

    @Override
    public Boolean create(Product product) {
        Boolean isCheck = false;
        Connection connection = null;
        try {
            connection = ConnectionDB.openConnection();
            CallableStatement callableStatement = connection.prepareCall("{call PROC_CREATE_PRODUCT(?,?,?,?,?,?)}");
            callableStatement.setString(1, product.getProductName());
            callableStatement.setInt(2, product.getCategory().getCategoryId());
            callableStatement.setString(3, product.getImage());
            callableStatement.setDouble(4, product.getPrice());
            callableStatement.setString(5, product.getDescription());
            callableStatement.setInt(6, product.getStock());
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
    public Boolean delete(Integer id) {
        Boolean isCheck = false;
        Connection connection = null;
        try {
            connection = ConnectionDB.openConnection();
            CallableStatement callableStatement = connection.prepareCall("{call PROC_DELETE_PRODUCT(?)}");
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

    @Override
    public Boolean update(Integer id, Product product) {
        Boolean isCheck = false;
        Connection connection = null;
        try {
            connection = ConnectionDB.openConnection();
            CallableStatement callableStatement = connection.prepareCall("{call PROC_UPDATE_PRODUCT(?,?,?,?,?,?,?,?)}");
            callableStatement.setString(1, product.getProductName());
            callableStatement.setInt(2, product.getCategory().getCategoryId());
            callableStatement.setString(3, product.getImage());
            callableStatement.setDouble(4, product.getPrice());
            callableStatement.setString(5, product.getDescription());
            callableStatement.setInt(6, product.getStock());
            callableStatement.setBoolean(7, product.getStatus());
            callableStatement.setInt(8,id );
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

    public List<Product> findAllProductByCategory(Integer id ) {
        List<Product> list = new ArrayList<>();
        Connection connection = null ;

        try {
            connection = ConnectionDB.openConnection();
            CallableStatement callableStatement = connection.prepareCall("{CALL PROC_SHOW_PRODUCT_BY_CATEGORY(?)}");
            callableStatement.setInt(1,id);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setProductName(rs.getString("productName"));
                Category category = categoryDAO.findById(rs.getInt("categoryID"));
                product.setCategory(category);
                product.setImage(rs.getString("image"));
                product.setPrice(rs.getDouble("price"));
                product.setDescription(rs.getString("description"));
                product.setStock(rs.getInt("stock"));
                product.setStatus(rs.getBoolean("status"));
                list.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }

        return list ;
    }

    public Integer countProduct() {
        Connection connection = null;
        int count = 0 ;
        try {
            connection = ConnectionDB.openConnection();
            CallableStatement callableStatement = connection.prepareCall("{CALL PROC_COUNT_PRODUCT()}");
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                count= resultSet.getInt("total_product");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }

        return count  ;
    }
    public List<Product> findAllProductPage(int offset , int size ) {
        List<Product> list = new ArrayList<>();
        Connection connection = null  ;

        try {
            connection = ConnectionDB.openConnection();
            CallableStatement callableStatement = connection.prepareCall("{CALL PROC_GET_ALL_PRODUCT_PAGE(?,?)}");
            callableStatement.setInt(1, offset);
            callableStatement.setInt(2, size);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setProductName(rs.getString("productName"));
                Category category = categoryDAO.findById(rs.getInt("categoryID"));
                product.setCategory(category);
                product.setImage(rs.getString("image"));
                product.setPrice(rs.getDouble("price"));
                product.setDescription(rs.getString("description"));
                product.setStock(rs.getInt("stock"));
                product.setStatus(rs.getBoolean("status"));
                list.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }


        return list ;
    }
}
