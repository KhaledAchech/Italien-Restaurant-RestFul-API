package com.Tekup.ApiRestaurantItalien.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

/************************************
 ********* author : Khaled ***********
 *** last update : december 22, 2020**
 ************************************/
@Data
@Entity
public class Met {

    @Id
    private String nom;
    private float prix;

    @JsonIgnore
    @ManyToMany(mappedBy = "mets")
    private Set<Ticket> tickets = new HashSet<>();

}
