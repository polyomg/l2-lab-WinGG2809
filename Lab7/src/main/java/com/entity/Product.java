package com.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "Products")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    Double price;
    @Temporal(TemporalType.DATE)
    @Column(name = "CreateDate")
    Date createDate = new Date();
    @ManyToOne @JoinColumn(name = "Categoryid")
    Category category;
}
