package com.controller;

import com.service.MailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.service.MailService;
import com.service.MailService.Mail;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.util.UUID;

@Controller
public class MailController {
    @Autowired
    MailService mailService;

    @ResponseBody
    @RequestMapping("/mail/send")
    public String send() {
//        try {
//            String subject = "Email thử nghiệm từ Spring Boot";
//            String body = "<h1>Đây là email test!</h1>"
//                    + "<p>Xin chào, email này được gửi tự động từ "
//                    + "dịch vụ mail của tôi.</p>";
//
//            mailService.send("nguyendailong2809@gmail.com", subject, body);
//            return "Mail của bạn đã được gửi đi";
//        } catch (RuntimeException e) {
//            return e.getMessage();
//        }

        //Bài 1
        try {
            mailService.send("nguyendailong2809@gmail.com", "Subject", "lolololololololol");
            return "Mail của bạn đã được gửi đi";
        } catch (MessagingException e) {
            return e.getMessage();
        }


        //Bài 2
//        String subject = "Email thử nghiệm từ Spring Boot";
//        String body = "<h1>Đây là email test!</h1>"
//                + "<p>Xin chào, email này được gửi tự động từ "
//                + "dịch vụ mail của tôi.</p>";

//        mailService.push("nguyendailong2809@gmail.com", "Subject", "lolololololololol");
//
//        return "Mail của bạn đã được xếp vào hàng đợi";
   }

    @GetMapping("mail/form")
    public String form(Model model) {
        model.addAttribute("mail", Mail.builder().build());
        return "mail/form";
    }

    @PostMapping("mail/send-direct")
    public String sendDirect(@ModelAttribute("mail") Mail mail, @RequestParam("files") MultipartFile[] files, Model model) {
        attachFiles(mail, files);
        try {
            mailService.send(mail);
            model.addAttribute("message", "Đã gửi trực tiếp");
        } catch (Exception e) {
            model.addAttribute("message", "Lỗi gửi mail: " + e.getMessage());
        }
        return "mail/form";
    }

    @PostMapping("mail/send-queue")
    public String sendQueue(@ModelAttribute("mail") Mail mail, @RequestParam("files") MultipartFile[] files, Model model) {
        attachFiles(mail, files);
        mailService.push(mail);
        model.addAttribute("message", "Đã xếp hàng đợi");
        return "mail/form";
    }

    private void attachFiles(Mail mail, MultipartFile[] files) {
        try {
            StringBuilder filenames = new StringBuilder();
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    String path = "uploads/" + UUID.randomUUID() + "_" + file.getOriginalFilename();
                    File f = new File(path);
                    f.getParentFile().mkdirs();
                    Files.write(f.toPath(), file.getBytes());
                    filenames.append(path).append(";");
                }
            }
            mail.setFilenames(filenames.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
