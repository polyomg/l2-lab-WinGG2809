package com.controller;

import com.dao.ProductDAO;
import com.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController2 {
    @Autowired
    ProductDAO dao;

    @RequestMapping("/product/sort")
    public String sort(Model model, @RequestParam("field") Optional<String> field) {
        Sort sort = Sort.by(Sort.Direction.DESC, field.orElse("price"));
        List<Product> items = dao.findAll(sort); // truy vấn tất cả
        model.addAttribute("items", items);
        return "product/sort";
    }
    @RequestMapping("/product/page")
    public String paginate(Model model, @RequestParam("p") Optional<Integer> p) {
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Product> page = dao.findAll(pageable);
        model.addAttribute("page", page);
        return "product/page";
    }
    @RequestMapping("product/list")
    public String listProducts(Model model,
                                             @RequestParam("p") Optional<Integer> p,
                                             @RequestParam("field") Optional<String> field) {
        String sortField = field.orElse("id");
        Sort sort = Sort.by(Sort.Direction.DESC, sortField);
        Pageable pageable = PageRequest.of(p.orElse(0), 5, sort);
        Page<Product> page = dao.findAll(pageable);
        model.addAttribute("page", page);
        model.addAttribute("field", sortField);
        return "product/list";
    }
}
