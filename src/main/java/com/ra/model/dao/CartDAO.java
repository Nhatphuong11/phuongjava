package com.ra.model.dao;

import com.ra.model.entity.Cart;

public interface CartDAO {
    Boolean addCart(Cart cart) ;
    Cart findByIdUser(Integer idUser) ;
}
