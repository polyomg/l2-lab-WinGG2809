//package com.product.controller;
//
//import com.product.model.Product;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//public class ProductController {
//
//    @GetMapping("/product/form")
//    public String form(Model model) {
//        model.addAttribute("product", new Product());
//        return "product/form";
//    }
//
//    @PostMapping("/product/save")
//    public String save(@ModelAttribute Product product, Model model) {
//        // Nhận dữ liệu từ form -> map vào Product
//        model.addAttribute("product", product);
//        return "product/form";
//    }
//}
//

//package com.product.controller;
//
//import com.product.model.Product;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import java.util.Arrays;
//import java.util.List;
//
//@Controller
//public class ProductController {
//
//    @GetMapping("/product/form")
//    public String form(Model model) {
//        Product p = new Product();
//        p.setName("iPhone");
//        p.setPrice(5000.0);
//
//        model.addAttribute("product1", p);
//
//        return "product/form";
//    }
//
//    @PostMapping("/product/save")
//    public String save(@ModelAttribute("product2") Product p, Model model) {
//        model.addAttribute("product2", p);
//        //@@ModelAttribute Nhận dữ liệu từ form (mapping từ các input trong HTML vào đối tượng Java và)
//        Product p1 = new Product("iPhone", 5000.0);
//        model.addAttribute("product1", p1);
//        return "product/form";
//    }
//
//    @ModelAttribute("items")
//    public List<Product> getItems() {
//        return Arrays.asList(
//                new Product("A", 1.0),
//                new Product("B", 12.0)
//        );
//    }
//}

package com.product.controller;

import com.product.model.Product;
import org.antlr.v4.runtime.atn.PredictionContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class ProductController {


    private List<Product> items = new ArrayList<>();

    public ProductController() {
        items.add(new Product("A", 1.0));
        items.add(new Product("B", 12.0));
    }

    @GetMapping("/product/form")
    public String form(Model model) {
        Product p = new Product();
        p.setName("iPhone");
        p.setPrice(5000.0);

        model.addAttribute("product1", p);
        model.addAttribute("items", items);
        return "product/form";
    }

    @PostMapping("/product/save")
    public String save(@ModelAttribute("product2") Product p, RedirectAttributes redirectAttributes) {
        items.add(p);

        redirectAttributes.addFlashAttribute("product2" ,p);
        return "redirect:/product/form";
    }
}


