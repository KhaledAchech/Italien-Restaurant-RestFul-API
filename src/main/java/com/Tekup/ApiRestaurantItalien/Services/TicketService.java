package com.Tekup.ApiRestaurantItalien.Services;

import com.Tekup.ApiRestaurantItalien.DTO.MetResponse;
import com.Tekup.ApiRestaurantItalien.DTO.TicketRequest;
import com.Tekup.ApiRestaurantItalien.DTO.TicketResponse;
import com.Tekup.ApiRestaurantItalien.Models.Client;
import com.Tekup.ApiRestaurantItalien.Models.Met;
import com.Tekup.ApiRestaurantItalien.Models.Table;
import com.Tekup.ApiRestaurantItalien.Models.Ticket;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/************************************
 ********* author : Khaled ***********
 *** last update : december 22, 2020**
 ************************************/
public interface TicketService {
    List<Ticket> getAllTickets();
    Ticket getTicketById(int numero);
    TicketResponse createTicket(TicketRequest ticket);
    TicketResponse modifyTicket(int numero,TicketRequest newTicket);
    TicketResponse deleteTicketById(int numero);

    Ticket addMeal(int numero, Met met);

    //The most ordered plate at a given Date (le plat le plus acheté dans une période "date" donnée)
    MetResponse getMostBoughtPlateByDate(LocalDateTime date);

    //revenue per day (revenue par jour)
    List<String> getRevenuePerDay();

    //revenue per month (revenue par mois)
    List<String> getRevenuePerMonth();

    //revenue per week (revenue par semaine)
    List<String> getRevenuePerWeek();

    //revenue for a given date (revenue par un date donnée)
    List<String> getRevenueAtGivenDate(LocalDateTime date);
}
