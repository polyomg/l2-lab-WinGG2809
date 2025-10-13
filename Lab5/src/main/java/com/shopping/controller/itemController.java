package com.shopping.controller;

import com.shopping.db.DB;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class itemController {
    @RequestMapping("/item/index")
    public String list(Model model){
        model.addAttribute("items", DB.items.values());
        return "item/index";
    }
}
