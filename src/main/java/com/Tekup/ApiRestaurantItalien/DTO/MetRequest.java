package com.Tekup.ApiRestaurantItalien.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

/************************************
 ********* author : Khaled ***********
 *** last update : december 22, 2020**
 ************************************/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetRequest {
    @NotBlank(message = "Name must contain characters")
    @Size(message = "size should be between 5 and 50" ,min = 5, max = 50)
    @Pattern(regexp = "[a-zA-Z ]+", message =  "Name must contain only characters")
    private String nom;
    @Positive(message = "This field should be positive")
    private float prix;
}
