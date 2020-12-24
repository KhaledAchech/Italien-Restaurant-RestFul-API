package com.Tekup.ApiRestaurantItalien.Services;

import com.Tekup.ApiRestaurantItalien.Models.Client;
import com.Tekup.ApiRestaurantItalien.Models.Met;
import com.Tekup.ApiRestaurantItalien.Models.Ticket;

import java.util.List;
/************************************
 ********* author : Khaled ***********
 *** last update : december 22, 2020**
 ************************************/
public interface ClientService {

    List<Client> getAllClients();
    Client getClientById(long id);
    Client createClient(Client client);
    Client modifyClient(long id,Client newclient);
    Client deleteClientById(long id);
    Client addTicket(long id, Ticket ticket);
}
