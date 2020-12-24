package com.Tekup.ApiRestaurantItalien.Services;

import com.Tekup.ApiRestaurantItalien.Models.Client;
import com.Tekup.ApiRestaurantItalien.Models.Met;

import java.util.List;

/************************************
 ********* author : Khaled ***********
 *** last update : december 22, 2020**
 ************************************/
public interface MetService {

    List<Met> getAllMets();
    Met getMetByName(String nom);
    Met createMet(Met met);
    Met modifyMet(String nom,Met newMet);
    Met deleteMetByName(String nom);
}

