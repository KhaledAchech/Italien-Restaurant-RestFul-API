package com.Tekup.ApiRestaurantItalien.Services;

import com.Tekup.ApiRestaurantItalien.DTO.TicketRequest;
import com.Tekup.ApiRestaurantItalien.DTO.TicketResponse;
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
    TicketResponse createTicket(TicketRequest ticket);
    TicketResponse modifyTicket(int numero,TicketRequest newTicket);
    TicketResponse deleteTicketById(int numero);

    Ticket addMeal(int numero, Met met);
}
