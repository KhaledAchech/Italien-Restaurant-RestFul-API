package com.Tekup.ApiRestaurantItalien.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;

/************************************
 ********* author : Khaled ***********
 *** last update : december 22, 2020**
 ************************************/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequest {

    @NotBlank(message = "Name must contain characters")
    @Size(message = "size should be between 5 and 50" ,min = 5, max = 50)
    @Pattern(regexp = "[a-zA-Z ]+", message =  "Name must contain only characters")
    private String nom;

    @Size(min = 5, max = 50)
    @Pattern(regexp = "[a-zA-Z ]+", message =  "Name must contain only characters")
    @NotBlank(message = "First name must contain characters")
    private String prenom;

    @Past
    private LocalDate dateDeNaissance;

    @Email
    private String courriel;

    @Pattern(regexp = "[0-9]+", message =  "telephone number must contain only numbers")
    private String telephone;
}
