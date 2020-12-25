package com.Tekup.ApiRestaurantItalien.DTO;

import com.Tekup.ApiRestaurantItalien.Models.Client;
import com.Tekup.ApiRestaurantItalien.Models.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalTime;

/************************************
 ********* author : Khaled ***********
 *** last update : december 22, 2020**
 ************************************/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketRequest {
    @FutureOrPresent(message = "Date should be present or in the near future")
    private LocalTime date;
    @Positive(message = "This field should be positive")
    private int nbCouvert;
    @Positive(message = "This field should be positive")
    private float addition;
}
