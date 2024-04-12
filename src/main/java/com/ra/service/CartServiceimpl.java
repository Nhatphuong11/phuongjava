package com.ra.service;

import com.ra.model.dao.CartDAO;
import com.ra.model.entity.Cart;
import com.ra.model.entity.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceimpl implements CartService{
    @Autowired
    HttpSession httpSession;
    private List<CartItem> cartItems = new ArrayList<>();

    @Override
    public List<CartItem> getSession() {
        cartItems = (List<CartItem>) httpSession.getAttribute("cart");
        return cartItems != null ? cartItems : new ArrayList<>();
    }
    @Override
    public List<CartItem> getAll(Integer Id) {
        return null;
    }

    @Override
    public Boolean save(CartItem item) {
        cartItems = getSession();
        CartItem cartItem = check(item.getProduct().getProductId(),cartItems);
        if(cartItem == null){
            cartItems.add(item);
        }else {
            cartItem.setQuantity(cartItem.getQuantity()+ item.getQuantity());
        }
        httpSession.setAttribute("cart",cartItems);
        return true;
    }

    @Override
    public CartItem findById(Integer id) {
        return null;
    }

    public CartItem check(Integer productId, List<CartItem> cartItems){
        for (CartItem cartItem: cartItems){
            if(cartItem.getProduct().getProductId().equals(productId)){
                return cartItem;
            }
        }
        return null;
    }

}
