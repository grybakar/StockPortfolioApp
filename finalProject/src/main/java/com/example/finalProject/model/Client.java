package com.example.finalProject.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;


import javax.persistence.*;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String password;
    private String email;

    @JsonManagedReference
    @OneToMany(
            mappedBy = "client",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Portfolio> portfolio;

}
