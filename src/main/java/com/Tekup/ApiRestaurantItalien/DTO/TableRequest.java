package com.Tekup.ApiRestaurantItalien.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

/************************************
 ********* author : Khaled ***********
 *** last update : december 22, 2020**
 ************************************/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableRequest {
    @Positive(message = "This field should be positive")
    private int nbCouvert;
    @NotBlank(message = "Type must contain characters")
    private String type;
    @Positive(message = "This field should be positive")
    private float supplement;
}
