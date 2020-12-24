package com.Tekup.ApiRestaurantItalien.Services;

import com.Tekup.ApiRestaurantItalien.Models.Client;
import com.Tekup.ApiRestaurantItalien.Models.Met;
import com.Tekup.ApiRestaurantItalien.Models.Table;
import com.Tekup.ApiRestaurantItalien.Models.Ticket;
import com.Tekup.ApiRestaurantItalien.Repositories.MetRepository;
import com.Tekup.ApiRestaurantItalien.Repositories.TableRepository;
import com.Tekup.ApiRestaurantItalien.Repositories.TicketRepository;
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
    public Ticket createTicket(Ticket ticket) {
        return ticketRepo.save(ticket);
    }

    @Override
    public Ticket modifyTicket(int numero, Ticket newTicket) {
        Ticket thisTicket = this.getTicketById(numero);
        if (newTicket.getDate()!=null)
        {
            thisTicket.setDate(newTicket.getDate());
        }
        if (newTicket.getNbCouvert()!=0)
        {
            thisTicket.setNbCouvert(newTicket.getNbCouvert());
        }
        if (!(newTicket.getAddition()<0))
        {
            thisTicket.setAddition(newTicket.getAddition());
        }
        return ticketRepo.save(thisTicket);
    }

    @Override
    public Ticket deleteTicketById(int numero) {
        Ticket ticket = this.getTicketById(numero);
        ticketRepo.deleteById(numero);
        return ticket;
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
