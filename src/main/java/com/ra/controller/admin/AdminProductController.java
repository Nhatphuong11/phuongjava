package com.ra.controller.admin;
import com.ra.dto.request.ProductDTO;
import com.ra.dto.response.ResponseCategoryDTO;
import com.ra.dto.response.ResponseProductDTO;
import com.ra.service.CategoryService;
import com.ra.service.ProductService;
import com.ra.service.ProductServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.channels.MulticastChannel;
import java.util.List;

@Controller
@RequestMapping("/admin")
@PropertySource("classpath:config.properties")
public class AdminProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Value("${path}")
    private String pathUpload ;

    // TODO : list product
    @GetMapping("/product")
    public String showListProduct(Model model){
        List<ResponseProductDTO> list = productService.findAll();
        model.addAttribute("list",list);
        return "admin/product/listproduct";
    }

    @GetMapping("/add-product")
    public String createProduct(Model model ){
        ProductDTO productDTO = new ProductDTO();
        model.addAttribute("product", productDTO);
        List<ResponseCategoryDTO> list = categoryService.findAll();
        model.addAttribute("category", list) ;
        return "admin/product/add-product";
    }

    @PostMapping("/add-product")
    public String postCreateProduct(@Valid @ModelAttribute("product") ProductDTO productDTO,
                                    BindingResult result,
                                    @RequestParam("fileImage")MultipartFile multipartFile,
                                    Model model){

        if (result.hasErrors()) {
            List<ResponseCategoryDTO> categories = categoryService.findAll();
            model.addAttribute("category", categories);
            return "admin/product/add-product";
        }

        if (multipartFile.isEmpty()) {
            model.addAttribute("fileError", "Please select a file to upload.");
            List<ResponseCategoryDTO> categories = categoryService.findAll();
            model.addAttribute("category", categories);
            return "admin/product/add-product";
        }
        try {
            // Upload the file
            String fileName = multipartFile.getOriginalFilename();
            File file = new File(pathUpload + fileName);
            multipartFile.transferTo(file);

            productDTO.setImage(fileName);

            productService.create(productDTO);

            return "redirect:/admin/product";
        } catch (IOException e) {
            // Handle IO exception
            throw new RuntimeException(e);
        }
    }


    @GetMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable Integer id ){
        Boolean isDelete = productService.delete(id) ;
        if ( isDelete) {
            return "redirect:/admin/product";
        }
        return "admin/product/listproduct";
    }

    @GetMapping("/edit-product/{id}")
    public String editProduct(@PathVariable Integer id , Model model) {
        ResponseProductDTO responseProductDTO = productService.findById(id) ;
        model.addAttribute("product", responseProductDTO);
        List<ResponseCategoryDTO> list = categoryService.findAll();
        model.addAttribute("category", list);
        return "admin/product/edit-product";
    }

    @PostMapping("/update-product/{id}")
    public String postEditProduct(@Valid @PathVariable Integer id ,
                                  @ModelAttribute("product") ProductDTO productDTO,
                                  BindingResult result, Model model ,
                                  @RequestParam("image-Update")MultipartFile multipartFile){
        if ( result.hasErrors()) {
            List<ResponseCategoryDTO> categories = categoryService.findAll();
            model.addAttribute("category", categories);
            return "admin/product/edit-product";
        }

        if (!multipartFile.isEmpty()) {
            String fileName = multipartFile.getOriginalFilename();
            File file = new File(pathUpload + fileName);
            try {
                multipartFile.transferTo(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            productDTO.setImage(fileName);
        } else {
            ResponseProductDTO responseProductDTO = productService.findById(id);
            productDTO.setImage(responseProductDTO.getImage());
        }
        productService.update(id, productDTO);
        return "redirect:/admin/product";
    }
}
