package com.Tekup.ApiRestaurantItalien.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
/************************************
 ********* author : Khaled ***********
 *** last update : december 22, 2020**
 ************************************/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponse {
    private LocalTime date;
    private int nbCouvert;
    private float addition;
}
