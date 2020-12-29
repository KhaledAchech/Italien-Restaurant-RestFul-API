package com.Tekup.ApiRestaurantItalien.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

/************************************
 ********* author : Khaled ***********
 *** last update : december 22, 2020**
 ************************************/
@Getter@Setter
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int numero;
    private LocalDateTime date;
    private int nbCouvert;
    private float addition;

    @JsonIgnore
    @ManyToOne
    private Client client;

    @ManyToOne
    @JsonIgnore
    private Table table;


    @JsonIgnore
    @ManyToMany
    @JoinTable (name = "ticket_compose", joinColumns = @JoinColumn (name = "numero"),
            inverseJoinColumns = @JoinColumn(name = "nom"))
    private Set<Met> mets = new HashSet<>();


}
