package com.controller;

import com.model.Staff;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class StaffController2 {

    @RequestMapping("/staff/list")
    public String list(Model model) {
        List<Staff> list = List.of(
                Staff.builder().id("user1@gmail.com").fullname("nguyễn văn user1").build(),
                Staff.builder().id("user2@gmail.com").fullname("nguyễn văn user2").build(),
                Staff.builder().id("user3@gmail.com").fullname("nguyễn văn user3").build(),
                Staff.builder().id("user4@gmail.com").fullname("nguyễn văn user4").build(),
                Staff.builder().id("user5@gmail.com").fullname("nguyễn văn user5").build(),
                Staff.builder().id("user6@gmail.com").fullname("nguyễn văn user6").build()
        );

        model.addAttribute("list", list);
        return "/demo/staff-list";
    }
}
