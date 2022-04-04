package com.aksionav.bookservice.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer code;

    @Column
    private String title;

    @JoinColumn(name = "author_code")
    @ManyToOne(cascade = CascadeType.ALL)
    private Author author;
}
