package com.ra.controller.admin;

import com.ra.dto.request.CategoryDTO;
import com.ra.dto.response.ResponseCategoryDTO;
import com.ra.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @RequestMapping("/category")
    public String listCategory(Model model){
        List<ResponseCategoryDTO> list = categoryService.findAll();
        model.addAttribute("list", list);
        return "admin/category/listcategory";
    }

    @GetMapping("/add-category")
    public String createCategory(Model model){
        CategoryDTO category = new CategoryDTO();
        model.addAttribute("category", category);
        return "admin/category/add-category";
    }

    @PostMapping("/add-category")
    public String postCreateCategory(@Valid @ModelAttribute("category") CategoryDTO category, BindingResult result){
        Boolean isCheck ;
        if(!result.hasErrors()) {
            isCheck = categoryService.create(category);
            if(isCheck) {
                return "redirect:/admin/category";
            }
        }
        return "admin/category/add-category";
    }
    @GetMapping("/delete-category/{id}")
    public String deleteCategory(@PathVariable Integer id){
        Boolean isDelete = categoryService.delete(id);
        if (isDelete) {
            return "redirect:/admin/category";  // Corrected path
        }
        return "admin/category/listcategory";
    }

    @GetMapping("/edit-category/{id}")
    public String editCategory(@PathVariable Integer id , Model model) {
        ResponseCategoryDTO category = categoryService.findById(id);
        model.addAttribute("category", category);
        return "admin/category/edit-category";
    }

    @PostMapping("/update-category/{id}")
    public String postEditCategory(@Valid @PathVariable Integer id ,
                                   @ModelAttribute("category") CategoryDTO categoryDTO, BindingResult result){
        Boolean isCheck ;
        if(!result.hasErrors()) {
            isCheck = categoryService.update(id, categoryDTO);
            if(isCheck) {
                return "redirect:/admin/category";
            }
        }
        return "admin/category/edit-category";

    }
}
