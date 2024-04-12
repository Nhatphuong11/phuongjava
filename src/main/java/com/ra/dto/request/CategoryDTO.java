package com.ra.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CategoryDTO {
    @NotEmpty(message = "Tên danh mục sản phầm không được để rỗng")
    private String categoryName;

    @NotNull(message = "Trạng thái không được để trống")
    private Boolean status ;

    public CategoryDTO() {

    }
    public CategoryDTO(String categoryName, Boolean status) {
        this.categoryName = categoryName;
        this.status = status;
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
