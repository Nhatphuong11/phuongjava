package com.ra.dto.response;

public class ResponseCategoryDTO {
    private  Integer categoryId;
    private String categoryName;
    private Boolean status ;

    public ResponseCategoryDTO(){
    }
    public ResponseCategoryDTO(Integer categoryId, String categoryName, Boolean status) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.status = status;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }


}
