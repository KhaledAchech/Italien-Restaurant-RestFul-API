package com.Tekup.ApiRestaurantItalien.Endpoints;

import com.Tekup.ApiRestaurantItalien.Models.Client;
import com.Tekup.ApiRestaurantItalien.Models.Ticket;
import com.Tekup.ApiRestaurantItalien.Services.ClientService;
import com.Tekup.ApiRestaurantItalien.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
/************************************
 ********* author : Khaled ***********
 *** last update : december 22, 2020**
 ************************************/
@RestController
@RequestMapping("/api/clients")
public class ClientRest {

    private ClientService clientService;
    private TicketService ticketService;


    @Autowired
    public ClientRest(ClientService clientService, TicketService ticketService) {
        super();
        this.clientService = clientService;
        this.ticketService = ticketService;
    }

    @GetMapping
    public List<Client> getAll()
    {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public Client getById(@PathVariable long id)
    {
        return clientService.getClientById(id);
    }

    @PostMapping
    public Client createClient(@RequestBody Client client)
    {
        return clientService.createClient(client);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e)
    {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public Client modifyClient(@PathVariable("id") long id, @RequestBody Client newClient) {
        return clientService.modifyClient(id, newClient);
    }

    @DeleteMapping("/{id}")
    public Client deleteById(@PathVariable("id") long id) {
        return clientService.deleteClientById(id);
    }

    @PostMapping("/addTicket/{id}")
    public Client addTicketToClient(@PathVariable long id, @RequestBody Ticket ticket)
    {
        return clientService.addTicket(id,ticket);
    }

}
