package com.Tekup.ApiRestaurantItalien.Services;

import com.Tekup.ApiRestaurantItalien.Models.Client;
import com.Tekup.ApiRestaurantItalien.Models.Met;
import com.Tekup.ApiRestaurantItalien.Models.Ticket;
import com.Tekup.ApiRestaurantItalien.Repositories.ClientRepoistory;
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
public class ClientServiceImpl implements ClientService {

    private ClientRepoistory clientRepo;
    private TicketRepository ticketRepo;

    @Autowired
    public ClientServiceImpl(ClientRepoistory clientRepo,TicketRepository ticketRepo) {
        super();
        this.clientRepo = clientRepo;
        this.ticketRepo = ticketRepo;
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepo.findAll();
    }

    @Override
    public Client getClientById(long id) {
        Optional<Client> opt = clientRepo.findById(id);
        Client client;
        if (opt.isPresent())
        {
            client = opt.get();
        }
        else
        {
            throw new NoSuchElementException("il n'y a pas de client avec l'identifiant saisi");
        }
            return client;
    }

    @Override
    public Client createClient(Client client) {
        return clientRepo.save(client);
    }

    @Override
    public Client modifyClient(long id, Client newclient) {
        Client thisclient = this.getClientById(id);
        if (newclient.getNom()!=null)
        {
            thisclient.setNom(newclient.getNom());
        }
        if (newclient.getPrenom()!=null)
        {
            thisclient.setPrenom(newclient.getPrenom());
        }
        if (newclient.getDateDeNaissance()!=null)
        {
            thisclient.setDateDeNaissance(newclient.getDateDeNaissance());
        }
        if (newclient.getCourriel()!=null)
        {
            thisclient.setCourriel(newclient.getCourriel());
        }
        if (newclient.getTelephone()!=null)
        {
            thisclient.setTelephone(newclient.getTelephone());
        }
        return clientRepo.save(thisclient);
    }

    @Override
    public Client deleteClientById(long id) {
        Client client = this.getClientById(id);
        clientRepo.deleteById(id);
        return client;
    }

    @Override
    public Client addTicket(long id, Ticket ticket) {
        //adding the ticket to the client
        Client client = this.getClientById(id);
        client.getTickets().add(ticket);

        //making the reference with the ticket entity
        ticket.setClient(client);

        //saving the changes
        ticketRepo.save(ticket);
        return clientRepo.save(client);
    }


}
