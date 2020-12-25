package com.Tekup.ApiRestaurantItalien.Endpoints;

import com.Tekup.ApiRestaurantItalien.DTO.TicketRequest;
import com.Tekup.ApiRestaurantItalien.DTO.TicketResponse;
import com.Tekup.ApiRestaurantItalien.Models.Client;
import com.Tekup.ApiRestaurantItalien.Models.Met;
import com.Tekup.ApiRestaurantItalien.Models.Ticket;
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
@RequestMapping("/api/tickets")
public class TicketRest {

    private TicketService ticketService;

    @Autowired
    public TicketRest(TicketService ticketService) {
        super();
        this.ticketService = ticketService;
    }

    @GetMapping
    public List<Ticket> getAllTickets()
    {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{numero}")
    public Ticket getTicketById(@PathVariable int numero) //numero c'est l'identifiant d'un ticket
    {
        return ticketService.getTicketById(numero);
    }

    @PostMapping
    public TicketResponse createTicket(@RequestBody@Valid TicketRequest ticket)
    {
        return ticketService.createTicket(ticket);
    }

    @PutMapping("/{numero}")
    public TicketResponse modifyTicket(@PathVariable("numero") int numero, @RequestBody@Valid TicketRequest newTicket)
    {
        return ticketService.modifyTicket(numero,newTicket);
    }

    @DeleteMapping("/{numero}")
    public TicketResponse deleteTicketById(@PathVariable("numero") int numero)
    {
        return ticketService.deleteTicketById(numero);
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e)
    {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addMeal/{numero}")
    public Ticket addMeal(@PathVariable int numero, @RequestBody@Valid Met met)
    {
        return ticketService.addMeal(numero,met);
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
