//package com.service;
//
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.bind.DefaultValue;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Optional;
//
//@Service
//public class ParamService {
//    @Autowired
//    HttpServletRequest request;
//
//    /**
//     * Đọc chuỗi giá trị của tham số
//     * @param name tên tham số
//     * @param defaultValue giá trị mặc định
//     * @return giá trị tham số hoặc giá trị mặc định nếu không tồn tại
//     */
//    public String getString(String name, String defaultValue) {
//        return Optional.ofNullable(request.getParameter(name))
//                .filter(s -> !s.isEmpty())
//                .orElse(defaultValue);
//    }
//
//    /**
//     * Đọc số nguyên giá trị của tham số
//     * @param name tên tham số
//     * @param defaultValue giá trị mặc định
//     * @return giá trị tham số hoặc giá trị mặc định nếu không tồn tại
//     */
//    public int getInt(String name, int defaultValue) {
//        return Optional.ofNullable(request.getParameter(name))
//                .map(Integer::parseInt)
//                .orElse(defaultValue);
//
//    }
//
//    /**
//     * Đọc số thực giá trị của tham số
//     * @param name tên tham số
//     * @param defaultValue giá trị mặc định
//     * @return giá trị tham số hoặc giá trị mặc định nếu không tồn tại
//     */
//    public double getDouble(String name, double defaultValue) {
//        return Optional.ofNullable(request.getParameter(name))
//                .map(Double::parseDouble)
//                .orElse(defaultValue);
//    }
//
//    /**
//     * Đọc giá trị boolean của tham số
//     * @param name tên tham số
//     * @param defaultValue giá trị mặc định
//     * @return giá trị tham số hoặc giá trị mặc định nếu không tồn tại
//     */
//    public boolean getBoolean(String name, boolean defaultValue) {
//        return Optional.ofNullable(request.getParameter(name))
//                .map(Boolean::parseBoolean)
//                .orElse(defaultValue);
//    }
//
//    /**
//     * Đọc giá trị thời gian của tham số
//     * @param name tên tham số
//     * @param pattern là định dạng thời gian
//     * @return giá trị tham số hoặc null nếu không tồn tại
//     * @throws RuntimeException lỗi sai định dạng
//     */
//    public Date getDate(String name, String pattern) {
//        return Optional.ofNullable(request.getParameter(name))
//                .filter(s -> !s.isEmpty())
//                .map(value -> {
//                    try {
//                        return new SimpleDateFormat(pattern).parse(value);
//                    } catch (Exception e) {
//                        throw new RuntimeException("Sai định dạng ngày cho tham số: " + name);
//                    }
//                })
//                .orElse(null);
//    }
//
//    /**
//     * Lưu file upload vào thư mục
//     *
//     * @param file chứa file upload từ client
//     * @param path đường dẫn tính từ webroot
//     * @return đối tượng chứa file đã lưu hoặc null nếu không có file upload
//     * @throws RuntimeException lỗi lưu file
//     */
//    public File save(MultipartFile file, String path) {
//        return Optional.ofNullable(file)
//                .filter(f -> !f.isEmpty())
//                .map(f -> {
//                    try {
//                        String realPath = request.getServletContext().getRealPath(path);
//                        File dir = new File(realPath);
//                        if (!dir.exists()) dir.mkdirs();
//                        File savedFile = new File(dir, f.getOriginalFilename());
//                        f.transferTo(savedFile);
//                        return savedFile;
//                    } catch (IOException e) {
//                        throw new RuntimeException("Lỗi lưu file: " + e.getMessage());
//                    }
//                })
//                .orElse(null);
//    }
//}
package com.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ParamService {

    @Autowired
    HttpServletRequest request;

    /**
     * Đọc chuỗi giá trị của tham số
     * @param name tên tham số
     * @param defaultValue giá trị mặc định
     * @return giá trị tham số hoặc giá trị mặc định nếu không tồn tại
     */
    public String getString(String name, String defaultValue) {
        String value = request.getParameter(name);
        return (value != null && !value.isEmpty()) ? value : defaultValue;
    }

    /**
     * Đọc số nguyên giá trị của tham số
     * @param name tên tham số
     * @param defaultValue giá trị mặc định
     * @return giá trị tham số hoặc giá trị mặc định nếu không tồn tại
     */
    public int getInt(String name, int defaultValue) {
        try {
            return Integer.parseInt(request.getParameter(name));
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * Đọc số thực giá trị của tham số
     * @param name tên tham số
     * @param defaultValue giá trị mặc định
     * @return giá trị tham số hoặc giá trị mặc định nếu không tồn tại
     */
    public double getDouble(String name, double defaultValue) {
        try {
            return Double.parseDouble(request.getParameter(name));
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * Đọc giá trị boolean của tham số
     * @param name tên tham số
     * @param defaultValue giá trị mặc định
     * @return giá trị tham số hoặc giá trị mặc định nếu không tồn tại
     */
    public boolean getBoolean(String name, boolean defaultValue) {
        String value = request.getParameter(name);
        if (value == null) return defaultValue;
        return Boolean.parseBoolean(value);
    }

    /**
     * Đọc giá trị thời gian của tham số
     * @param name tên tham số
     * @param pattern là định dạng thời gian
     * @return giá trị tham số hoặc null nếu không tồn tại
     * @throws RuntimeException lỗi sai định dạng
     */
    public Date getDate(String name, String pattern) {
        try {
            String value = request.getParameter(name);
            if (value == null || value.isEmpty()) return null;
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.parse(value);
        } catch (Exception e) {
            throw new RuntimeException("Sai định dạng ngày cho tham số: " + name);
        }
    }

    /**
     * Lưu file upload vào thư mục
     * @param file chứa file upload từ client
    * @param path đường dẫn tính từ webroot
    * @return đối tượng chứa file đã lưu hoặc null nếu không có file upload
    * @throws RuntimeException lỗi lưu file
    */
    public File save(MultipartFile file, String path) {
        if (file == null || file.isEmpty()) return null;
        try {
            String realPath = request.getServletContext().getRealPath(path);
            File dir = new File(realPath);
            if (!dir.exists()) dir.mkdirs();

            File savedFile = new File(dir, file.getOriginalFilename());
            file.transferTo(savedFile);
            return savedFile;
        } catch (IOException e) {
            throw new RuntimeException("Lỗi lưu file: " + e.getMessage());
        }
    }
}
