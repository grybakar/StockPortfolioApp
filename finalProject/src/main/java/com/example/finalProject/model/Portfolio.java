package com.example.finalProject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Please type an appropriate portfolio name")
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Position> positions;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "clientId")
    private Client client;
}