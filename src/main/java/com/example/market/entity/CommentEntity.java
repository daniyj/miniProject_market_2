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
//    private Long itemId;
    @Column(nullable = false)
    private String writer;
    private String password;
    @Column(nullable = false)
    private String content;
    private String reply;
    @ManyToOne
    @JoinColumn(name="item_id") //db의 컬럼에서 사용될 이름
    private ItemEntity item;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
