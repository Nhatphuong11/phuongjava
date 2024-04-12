package com.ra.model.entity;

public class Cart {
    private Integer id;

    private User user;

    private  Double total_price;

    private  Integer total_qty;

    public Cart(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Double total_price) {
        this.total_price = total_price;
    }

    public Integer getTotal_qty() {
        return total_qty;
    }

    public void setTotal_qty(Integer total_qty) {
        this.total_qty = total_qty;
    }

    public Cart(Integer id, User user, Double total_price, Integer total_qty) {
        this.id = id;
        this.user = user;
        this.total_price = total_price;
        this.total_qty = total_qty;
    }
}
