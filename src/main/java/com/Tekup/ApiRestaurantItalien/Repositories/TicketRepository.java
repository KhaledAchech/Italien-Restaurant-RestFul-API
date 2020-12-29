package com.Tekup.ApiRestaurantItalien.Repositories;

import com.Tekup.ApiRestaurantItalien.Models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

/************************************
 ********* author : Khaled ***********
 *** last update : december 22, 2020**
 ************************************/
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    @Query("select Day(DATE(t.date)) as date, sum(t.addition) as revenue "
            +
            "FROM Ticket t "
            +
            "group by date")
    List<String> getRevenueByDay();

    @Query("select Month(DATE(t.date)) as date, sum(t.addition) as revenue "
            +
            "FROM Ticket t "
            +
            "group by date")
    List<String> getRevenueByMonth();

    @Query("select Week(DATE(t.date)) as date, sum(t.addition) as revenue "
            +
            "FROM Ticket t "
            +
            "group by date")
    List<String> getRevenueByWeek();

    @Query("select sum(t.addition) as revenue "
            +
            "FROM Ticket t "
            +
            "where t.date = :dt ")
    List<String> getRevenueAtAGivenDate(@Param("dt")LocalDateTime date);
}
