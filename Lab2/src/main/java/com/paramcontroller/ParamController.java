package com.paramcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ParamController {

    @RequestMapping("/param/form")
    public String form() {
        return "form";
    }

    @RequestMapping("/param/save/{x}")
    public String save(
            @PathVariable("x") String x, //@PathVariable đọc biến từ url
            @RequestParam("y") String y, //@RequestParam đọc biến từ query string hoặc form field
            Model model) {

        // đưa giá trị x, y ra view
        model.addAttribute("x", x);
        model.addAttribute("y", y);

        return "form";
    }
}
