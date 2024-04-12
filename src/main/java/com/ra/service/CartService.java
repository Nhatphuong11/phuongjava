package com.ra.service;

import com.ra.model.entity.Cart;
import com.ra.model.entity.CartItem;

import java.util.List;

public interface  CartService {
    List<CartItem> getAll(Integer id);
    Boolean save(CartItem item);

    CartItem findById(Integer id);


    List<CartItem> getSession();


}
