package com.ra.service;


import com.ra.dto.request.CategoryDTO;
import com.ra.dto.response.ResponseCategoryDTO;
import com.ra.model.entity.Category;

import java.util.List;
public interface CategoryService {
    List<ResponseCategoryDTO> findAll();
    ResponseCategoryDTO findById(Integer id) ;
    Boolean create(CategoryDTO category) ;
    Boolean delete(Integer id) ;
    Boolean update( Integer id , CategoryDTO categoryDTO);
}
