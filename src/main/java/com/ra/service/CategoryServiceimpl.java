package com.ra.service;
import com.ra.dto.response.ResponseCategoryDTO;
import com.ra.model.dao.CategoryDAOimpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ra.dto.request.CategoryDTO;
import com.ra.model.entity.Category;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceimpl implements CategoryService {
    @Autowired
    private CategoryDAOimpl categoryDAO_itf;
    private ModelMapper modelMapper ;

    @Override
    public List<ResponseCategoryDTO> findAll() {
        modelMapper = new ModelMapper();
        List<Category>list = categoryDAO_itf.findAll();

        return list.stream()
                .map(item -> modelMapper.map(item,ResponseCategoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseCategoryDTO findById(Integer id) {
        modelMapper = new ModelMapper();
        Category category = categoryDAO_itf.findById(id);
        return modelMapper.map(category, ResponseCategoryDTO.class);
    }

    @Override
    public Boolean create(CategoryDTO category) {
        modelMapper = new ModelMapper();
        return categoryDAO_itf.create(modelMapper.map(category, Category.class));
    }

    @Override
    public Boolean delete(Integer id) {
        return categoryDAO_itf.delete(id);
    }

    @Override
    public Boolean update( Integer id , CategoryDTO categoryDTO) {
        modelMapper = new ModelMapper();
        return categoryDAO_itf.update(id,modelMapper.map(categoryDTO, Category.class));
    }
}
