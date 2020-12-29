package com.Tekup.ApiRestaurantItalien.Repositories;

import com.Tekup.ApiRestaurantItalien.Models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/************************************
 ********* author : Khaled ***********
 *** last update : december 22, 2020**
 ************************************/
public interface ClientRepoistory extends JpaRepository<Client, Long> {


    /*@Query("select DAYNAME(t.date) as day from Ticket t "
            + "where t.client = :clt " +
            "GROUP BY day " +
            "HAVING (COUNT(day) = MAX(COUNT(day))")

     */
    @Query("select DAYNAME(t.date) as day from Ticket t "
            + "where t.client = :clt ")
    List<String> GetMostReservedDaysByClient(@Param("clt")Client client);
}
