package com.Tekup.ApiRestaurantItalien.Services;

import com.Tekup.ApiRestaurantItalien.DTO.TicketRequest;
import com.Tekup.ApiRestaurantItalien.DTO.TicketResponse;
import com.Tekup.ApiRestaurantItalien.Models.Client;
import com.Tekup.ApiRestaurantItalien.Models.Met;
import com.Tekup.ApiRestaurantItalien.Models.Table;
import com.Tekup.ApiRestaurantItalien.Models.Ticket;
import com.Tekup.ApiRestaurantItalien.Repositories.MetRepository;
import com.Tekup.ApiRestaurantItalien.Repositories.TableRepository;
import com.Tekup.ApiRestaurantItalien.Repositories.TicketRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
/************************************
 ********* author : Khaled ***********
 *** last update : december 22, 2020**
 ************************************/
@Service
public class TicketServiceImpl implements TicketService {

    private TicketRepository ticketRepo;
    private MetRepository metRepo;
    private ModelMapper mapper = new ModelMapper();

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepo, MetRepository metRepo) {
        super();
        this.ticketRepo = ticketRepo;
        this.metRepo = metRepo;
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepo.findAll();
    }

    @Override
    public Ticket getTicketById(int numero) {
        Optional<Ticket> opt = ticketRepo.findById(numero);
        Ticket ticket;
        if (opt.isPresent())
        {
            ticket = opt.get();
        }
        else
        {
            throw new NoSuchElementException("il n'y a pas un ticket avec le numero saisi");
        }
        return ticket;
    }

    @Override
    public TicketResponse createTicket(TicketRequest ticket)
    {
        Ticket ticketRequest = mapper.map(ticket, Ticket.class);
        ticketRepo.save(ticketRequest);
        return mapper.map(ticketRequest, TicketResponse.class);
    }

    @Override
    public TicketResponse modifyTicket(int numero, TicketRequest newTicket) {
        Ticket ticketRequest = mapper.map(newTicket, Ticket.class);
        Ticket thisTicket = this.getTicketById(numero);
        if (ticketRequest.getDate()!=null)
        {
            thisTicket.setDate(ticketRequest.getDate());
        }
        if (ticketRequest.getNbCouvert()!=0)
        {
            thisTicket.setNbCouvert(ticketRequest.getNbCouvert());
        }
        if (!(ticketRequest.getAddition()<0))
        {
            thisTicket.setAddition(ticketRequest.getAddition());
        }
        ticketRepo.save(thisTicket);
        return mapper.map(thisTicket, TicketResponse.class);
    }

    @Override
    public TicketResponse deleteTicketById(int numero) {
        Ticket ticket = this.getTicketById(numero);
        ticketRepo.deleteById(numero);
        return mapper.map(ticket,TicketResponse.class);
    }

    @Override
    public Ticket addMeal(int numero, Met met) {
        //adding the meal to the ticket
        Ticket ticket = this.getTicketById(numero);
        ticket.getMets().add(met);
        ticket.setAddition(ticket.getAddition()+met.getPrix());

        //making the reference with the met entity
        met.getTickets().add(ticket);

        //saving the changes
        metRepo.save(met);
        return ticketRepo.save(ticket);
    }


}
