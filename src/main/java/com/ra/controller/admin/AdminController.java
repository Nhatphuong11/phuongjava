package com.ra.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @RequestMapping(value= {"/", ""})
    public String index(Model model){
        return "admin/indexadmin";
    }

}
