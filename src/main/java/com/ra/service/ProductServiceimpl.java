package com.ra.service;
import com.ra.dto.request.ProductDTO;
import com.ra.dto.response.ResponseProductDTO;
import com.ra.model.dao.ProductDAO;
import com.ra.model.dao.ProductDAOimpl;
import com.ra.model.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class ProductServiceimpl implements ProductService {
    @Autowired
    private ProductDAO productDAO_itf;

    @Autowired
    private ProductDAOimpl productDAOIpml ;

    private ModelMapper modelMapper;
    @Override
    public List<ResponseProductDTO> findAll() {
        modelMapper = new ModelMapper();
        List<Product> list = productDAO_itf.findAll();
        return list.stream()
                .map(item -> modelMapper.map(item,ResponseProductDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseProductDTO findById(Integer id) {
        modelMapper = new ModelMapper();
        Product product = productDAO_itf.findById(id);
        return modelMapper.map(product, ResponseProductDTO.class);
    }

    @Override
    public Boolean create(ProductDTO productDTO) {
        modelMapper = new ModelMapper();
        return productDAO_itf.create(modelMapper.map(productDTO, Product.class));
    }

    @Override
    public Boolean delete(Integer id) {
        return productDAO_itf.delete(id);
    }

    @Override
    public Boolean update(Integer id, ProductDTO productDTO) {
        modelMapper = new ModelMapper();
        return productDAO_itf.update(id, modelMapper.map(productDTO, Product.class));
    }

    public List<ResponseProductDTO> findAllProductByCategory(Integer id ) {
        modelMapper = new ModelMapper();
        List<Product> list = productDAOIpml.findAllProductByCategory(id) ;

        return list.stream()
                .map(item -> modelMapper.map(item, ResponseProductDTO.class))
                .collect(Collectors.toList());
    }
    public Integer countProduct() {
        return productDAOIpml.countProduct();
    }

    public List<ResponseProductDTO> findAllProductPage(int offset , int size ) {
        modelMapper = new ModelMapper() ;
        List<Product> list = productDAOIpml.findAllProductPage(offset,size) ;
        return list.stream()
                .map(item -> modelMapper.map(item, ResponseProductDTO.class))
                .collect(Collectors.toList());
    }
}
