package com.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder.Default;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Staff {
    private String id;           // Email
    private String fullname;     // Họ tên

    @Default
    private String photo = "photo.gif";

    @Default
    private Boolean gender = true;

    @Default
    private Date birthday = new Date();

    @Default
    private Double salary = 12345.68;

    @Default
    private Integer level = 0;
}
