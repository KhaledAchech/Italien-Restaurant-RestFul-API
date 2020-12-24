package com.Tekup.ApiRestaurantItalien.Models;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


/************************************
 ********* author : Khaled ***********
 *** last update : december 22, 2020**
 ************************************/
@Data
@Entity(name = "TableDeRestaurant")
public class Table {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int numero;

    private int nbCouvert;
    private String type;
    private float supplement;

    @OneToMany(mappedBy = "table",cascade = CascadeType.REMOVE)
    private Set<Ticket> tickets = new HashSet<>();

}
