package com.example.market.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "User")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String password;
    private String phone;
    private String email;
    private String address;

    // mappedBy :연관관계의 주인이 되는 필드 설정
    @OneToMany(mappedBy = "user")
    private List<CommentEntity> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<ProposalEntity> proposals = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<ItemEntity> items = new ArrayList<>();

}
