package com.ra.dto.request;

import com.ra.model.entity.Category;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProductDTO {
    @NotEmpty(message = "Tên sản phẩm không được để rỗng")
    private String productName ;
    @NotNull(message = "Danh mục sản phẩm không được để rỗng")
    private Category category ;
    private String image;
    private Double price ;
    private String description ;
    private Integer stock;
    private Boolean status ;

    public ProductDTO(String productName, Category category, String image, Double price, String description, Integer stock, Boolean status) {
        this.productName = productName;
        this.category = category;
        this.image = image;
        this.price = price;
        this.description = description;
        this.stock = stock;
        this.status = status;
    }

    public ProductDTO() {

    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
