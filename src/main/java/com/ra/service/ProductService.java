package com.ra.service;

import com.ra.dto.request.CategoryDTO;
import com.ra.dto.request.ProductDTO;
import com.ra.dto.response.ResponseCategoryDTO;
import com.ra.dto.response.ResponseProductDTO;

import java.util.List;

public interface ProductService {
    List<ResponseProductDTO> findAll();
    ResponseProductDTO findById(Integer id) ;
    Boolean create(ProductDTO productDTO) ;
    Boolean delete(Integer id) ;
    Boolean update( Integer id , ProductDTO productDTO);
    List<ResponseProductDTO> findAllProductByCategory(Integer id  );
    List<ResponseProductDTO> findAllProductPage(int offset , int size );
    Integer countProduct();
}
