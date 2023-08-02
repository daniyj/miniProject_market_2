package com.example.market.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Item")
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    private String imageUrl;
    @Column(nullable = false)
    private Long minPriceWanted;
    private String status;
    @Column(nullable = false)
    private String writer;
    private String password;

    @OneToMany(mappedBy = "item")
    private List<CommentEntity> comments = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<ProposalEntity> proposals = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity user;
}
