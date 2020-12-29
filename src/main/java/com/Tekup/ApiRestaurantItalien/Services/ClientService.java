package com.Tekup.ApiRestaurantItalien.Services;

import com.Tekup.ApiRestaurantItalien.DTO.ClientRequest;
import com.Tekup.ApiRestaurantItalien.DTO.ClientResponse;
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
    ClientResponse createClient(ClientRequest client);
    ClientResponse modifyClient(long id,ClientRequest newclient);
    ClientResponse deleteClientById(long id);
    Client addTicket(long id, Ticket ticket);

    ClientResponse getClientInfosById(long id);

    // get most loyal client (le client le plus fidèle au restaurant)
    ClientResponse getMostLoyalClient();

    String getMostReservedDayByClient(Client client);

}
