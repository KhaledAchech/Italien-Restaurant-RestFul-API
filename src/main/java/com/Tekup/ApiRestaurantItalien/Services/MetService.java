package com.Tekup.ApiRestaurantItalien.Services;

import com.Tekup.ApiRestaurantItalien.DTO.MetRequest;
import com.Tekup.ApiRestaurantItalien.DTO.MetResponse;
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
    MetResponse createMet(MetRequest met);
    MetResponse modifyMet(String nom,MetRequest newMet);
    MetResponse deleteMetByName(String nom);
}

