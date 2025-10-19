package com.controller;

import com.entity.Category;
import com.entity.Product;
import com.dao.CategoryDAO;
import com.dao.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductDAO dao;

    @Autowired
    CategoryDAO categoryDAO;

    @RequestMapping("/product/index")
    public String index(Model model) {
        Product item = new Product();
        model.addAttribute("item", item);

        List<Product> items = dao.findAll();
        model.addAttribute("items", items);

        List<Category> categories = categoryDAO.findAll();
        model.addAttribute("categories", categories);

        return "product/index";
    }

    @RequestMapping("/product/edit/{id}")
    public String edit(Model model, @PathVariable("id") Integer id) {
        Product item = dao.findById(id).get();
        model.addAttribute("item", item);

        List<Product> items = dao.findAll();
        model.addAttribute("items", items);

        List<Category> categories = categoryDAO.findAll();
        model.addAttribute("categories", categories);

        return "product/index";
    }

    @RequestMapping("/product/create")
    public String create(Product item) {
        item.setImage("temp.jpg");
        dao.save(item);
        item.setImage(item.getId() + ".jpg");
        dao.save(item);
        return "redirect:/product/index";
    }

    @RequestMapping("/product/update")
    public String update(Product item) {
        Product oldProduct = dao.findById(item.getId()).get();
        item.setImage(oldProduct.getImage());
        dao.save(item);
        return "redirect:/product/edit/" + item.getId();
    }

    @RequestMapping("/product/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        dao.deleteById(id);
        return "redirect:/product/index";
    }
}