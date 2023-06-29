package com.example.market.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Item")
public class ItemEntity {
    //반드시 포함-제목,섬령,최소가격,작성자
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private Long minPriceWanted;
    private String status;
    private String writer;
    private String password;
}
