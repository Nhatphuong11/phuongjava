package com.ra.model.dao;
import org.springframework.stereotype.Repository;
import com.ra.model.entity.Category;
import com.ra.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Repository
public class CategoryDAOimpl implements CategoryDAO{
    @Override
    public List<Category> findAll() {
        Connection connection = null;
        List<Category> categoryList = new ArrayList<>();
        try {
            connection = ConnectionDB.openConnection();
            CallableStatement callableStatement = connection.prepareCall("{CALL PRO_GET_ALL_CATEGORIES()}");
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getInt("categoryId"));
                category.setCategoryName(rs.getString("categoryName"));
                category.setStatus(rs.getBoolean("status"));
                categoryList.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return categoryList;
    }

    @Override
    public Category findById(Integer id) {
        Connection connection = null;
        Category category = new Category();
        try {
            connection = ConnectionDB.openConnection();
            CallableStatement callableStatement = connection.prepareCall("{CALL PROC_FINDByID_CATEGORY(?)}");
            callableStatement.setInt(1,id);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                category.setCategoryId(rs.getInt("categoryId"));
                category.setCategoryName(rs.getString("categoryName"));
                category.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return category;
    }

    @Override
    public Boolean create(Category category) {
        Boolean isCheck = false;
        Connection connection = null;
        try {
            connection = ConnectionDB.openConnection();
            CallableStatement callableStatement = connection.prepareCall("{call PROC_CREATE_CATEGORY(?,?)}");
            callableStatement.setString(1,category.getCategoryName());
            callableStatement.setBoolean(2,category.getStatus());
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
            CallableStatement callableStatement = connection.prepareCall("{call PROC_DELETE_CATEGORY(?)}");
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
    public Boolean update(Integer id, Category category) {
        Boolean isCheck = false;
        Connection connection = null;
        try {
            connection = ConnectionDB.openConnection();
            CallableStatement callableStatement = connection.prepareCall("{call PROC_UPDATE_CATEGORY(?,?,?)}");
            callableStatement.setString(1,category.getCategoryName());
            callableStatement.setBoolean(2,category.getStatus());
            callableStatement.setInt(3,id);
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
