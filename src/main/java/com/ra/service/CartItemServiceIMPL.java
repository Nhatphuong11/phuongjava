package com.ra.service;


import com.ra.dto.response.ResponseUserLoginDTO;
import com.ra.model.dao.CartItemDAO;
import com.ra.model.entity.Cart;
import com.ra.model.entity.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class CartItemServiceIMPL implements CartItemService{
    @Autowired
    private CartItemDAO cartItemDAO ;
    @Override
    public Boolean delete(Integer id) {
        return cartItemDAO.delete(id);
    }
}
