package com.example.market.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long itemId;
    private String writer;
    private String password;
    private String content;
    private String reply;
    @ManyToOne
    @JoinColumn(name="item_id")
    private ItemEntity item;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
