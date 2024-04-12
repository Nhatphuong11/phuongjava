package com.ra.controller.admin;

import com.ra.dto.response.ResponseUserLoginDTO;
import com.ra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class UserAdminController {
    @Autowired
    private UserService userService;
    @RequestMapping("/useradmin")
    public String listuser(Model model){
        List<ResponseUserLoginDTO> list = userService.listUser();
        model.addAttribute("list", list);
        return "admin/users/listuser";
    }

    @RequestMapping("/lockuser/{id}")
    public String lockuser(@PathVariable("id") Integer id){
        userService.lock(id);
        return "redirect:/admin/useradmin";
    }

}
