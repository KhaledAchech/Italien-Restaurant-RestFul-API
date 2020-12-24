package com.Tekup.ApiRestaurantItalien.Services;

import com.Tekup.ApiRestaurantItalien.Models.Client;
import com.Tekup.ApiRestaurantItalien.Models.Met;
import com.Tekup.ApiRestaurantItalien.Models.Table;
import com.Tekup.ApiRestaurantItalien.Models.Ticket;
import java.util.List;
/************************************
 ********* author : Khaled ***********
 *** last update : december 22, 2020**
 ************************************/
public interface TicketService {
    List<Ticket> getAllTickets();
    Ticket getTicketById(int numero);
    Ticket createTicket(Ticket ticket);
    Ticket modifyTicket(int numero,Ticket newTicket);
    Ticket deleteTicketById(int numero);

    Ticket addMeal(int numero, Met met);
}
