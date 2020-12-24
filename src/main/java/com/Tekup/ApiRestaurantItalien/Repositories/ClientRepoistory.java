package com.Tekup.ApiRestaurantItalien.Repositories;

import com.Tekup.ApiRestaurantItalien.Models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
/************************************
 ********* author : Khaled ***********
 *** last update : december 22, 2020**
 ************************************/
public interface ClientRepoistory extends JpaRepository<Client, Long> {
}
