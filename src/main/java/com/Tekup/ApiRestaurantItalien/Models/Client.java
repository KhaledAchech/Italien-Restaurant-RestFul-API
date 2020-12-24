package com.Tekup.ApiRestaurantItalien.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/************************************
 ********* author : Khaled ***********
 *** last update : december 22, 2020**
 ************************************/
@Data
@Entity
public class Client {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    private String prenom;
    private LocalDate dateDeNaissance;
    @Column(nullable = true)
    private String courriel;
    @Column(nullable = true)
    private String telephone;



    @OneToMany(mappedBy = "client",cascade = CascadeType.REMOVE)
    private Set<Ticket> tickets = new HashSet<>();

}
