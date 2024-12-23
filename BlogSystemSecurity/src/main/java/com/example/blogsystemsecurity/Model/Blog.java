package com.example.blogsystemsecurity.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @NotEmpty(message = "title cannot be empty")
//    @Column(columnDefinition = "varchar(50) not null")
    private String title;

//    @NotEmpty(message = "body cannot be empty")
//    @Column(columnDefinition = "varchar(50) not null")
    private String body;

    @ManyToOne
    @JsonIgnore
    private MyUser user;
}
