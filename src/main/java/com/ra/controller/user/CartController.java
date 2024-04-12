package com.ra.controller.user;


import com.ra.dto.response.ResponseProductDTO;
import com.ra.model.entity.CartItem;
import com.ra.model.entity.Product;
import com.ra.service.CartItemService;
import com.ra.service.CartService;
import com.ra.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller

public class CartController {
    @Autowired
    ProductService productService;
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    CartService cartService;
    @Autowired
    HttpSession httpSession ;
    private ModelMapper modelMapper = new ModelMapper();
    @GetMapping("/cart")
    public String cart(Model model){
        List<CartItem> cart = cartService.getSession();
        model.addAttribute("cart",cart);
        return "users/cart";
    }

    @PostMapping("/cart")
    public String postCart(@RequestParam("productId")Integer productId, @RequestParam("quantity")Integer qty){
        ResponseProductDTO product = productService.findById(productId);
        CartItem cartItem = new CartItem();
        cartItem.setProduct(modelMapper.map(product,Product.class));
        cartItem.setQuantity(qty);
        cartService.save(cartItem);
        return "redirect:/cart";
    }

    @GetMapping("/delete-cart/{id}")
    public String deleteCart(@PathVariable Integer id ) {
        Boolean isDelete = cartItemService.delete(id);
        if ( isDelete) {
            return "redirect:/cart";
        }
        return "users/cart";
    }
}
