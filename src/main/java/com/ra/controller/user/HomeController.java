package com.ra.controller.user;

import com.ra.dto.response.ResponseCategoryDTO;
import com.ra.dto.response.ResponseProductDTO;
import com.ra.service.CategoryService;
import com.ra.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = {"/","/home"})
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;
    @GetMapping("")
    public String home(Model model,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "8") int size) {
        List<ResponseCategoryDTO> listCategory = categoryService.findAll();
        model.addAttribute("listCategory", listCategory);
        int totalProduct = productService.countProduct();
        int totalPage = (int) Math.ceil((double) totalProduct / size);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPage", totalPage);
        List<ResponseProductDTO> listProduct = productService.findAllProductPage(page, size);
        model.addAttribute("listProduct", listProduct);
        return "users/home";
    }

    // TODO : danh sach san pham
    @GetMapping("/listJewellery")
    public String showList(Model model,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "8") int size){
        int totalProduct = productService.countProduct();
        int totalPage = (int) Math.ceil((double) totalProduct /size) ;
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPage", totalPage);
        List<ResponseProductDTO> list = productService.findAllProductPage(page,size) ;
        model.addAttribute("list",list);
        return "users/listJewellery";
    }


    // TODO : chi tiet san pham
    @GetMapping("/detail/{productId}")
    public String showDetail(Model model ,@PathVariable Integer productId){
        ResponseProductDTO product = productService.findById(productId);
        model.addAttribute("product", product);
        int idCategory = product.getCategory().getCategoryId();
        List<ResponseProductDTO> listProductByCategory = productService.findAllProductByCategory(idCategory ) ;
        model.addAttribute("listProductByCategory",listProductByCategory) ;
        List<ResponseProductDTO> list = productService.findAll();
        model.addAttribute("list",list);
        return "users/detail";
    }
}
