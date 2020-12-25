package com.Tekup.ApiRestaurantItalien.Endpoints;

import com.Tekup.ApiRestaurantItalien.DTO.ClientRequest;
import com.Tekup.ApiRestaurantItalien.DTO.ClientResponse;
import com.Tekup.ApiRestaurantItalien.Models.Client;
import com.Tekup.ApiRestaurantItalien.Models.Ticket;
import com.Tekup.ApiRestaurantItalien.Services.ClientService;
import com.Tekup.ApiRestaurantItalien.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ClientResponse createClient(@RequestBody@Valid ClientRequest client)
    {
        return clientService.createClient(client);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e)
    {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ClientResponse modifyClient(@PathVariable("id") long id, @RequestBody@Valid ClientRequest newClient) {
        return clientService.modifyClient(id, newClient);
    }

    @DeleteMapping("/{id}")
    public ClientResponse deleteById(@PathVariable("id") long id) {
        return clientService.deleteClientById(id);
    }

    @PostMapping("/addTicket/{id}")
    public Client addTicketToClient(@PathVariable long id, @RequestBody@Valid Ticket ticket)
    {
        return clientService.addTicket(id,ticket);
    }

    @GetMapping("/FullInfo/{id}")
    public ClientResponse getClientInfoById(@PathVariable long id)
    {
        return clientService.getClientInfosById(id);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        StringBuilder errors = new StringBuilder();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.append(error.getField() + ": "+ error.getDefaultMessage()+".\n");
        }
        return new ResponseEntity<String>(errors.toString(), HttpStatus.BAD_REQUEST);
    }

}
