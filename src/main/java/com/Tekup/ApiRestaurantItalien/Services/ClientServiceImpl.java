package com.Tekup.ApiRestaurantItalien.Services;

import com.Tekup.ApiRestaurantItalien.DTO.ClientRequest;
import com.Tekup.ApiRestaurantItalien.DTO.ClientResponse;
import com.Tekup.ApiRestaurantItalien.Models.Client;
import com.Tekup.ApiRestaurantItalien.Models.Ticket;
import com.Tekup.ApiRestaurantItalien.Repositories.ClientRepoistory;
import com.Tekup.ApiRestaurantItalien.Repositories.TicketRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/************************************
 ********* author : Khaled ***********
 *** last update : december 22, 2020**
 ************************************/
@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepoistory clientRepo;
    private TicketRepository ticketRepo;
    private ModelMapper mapper = new ModelMapper();

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
    public ClientResponse createClient(ClientRequest client)
    {
        Client clientRequest = mapper.map(client, Client.class);
        clientRepo.save(clientRequest);
        return mapper.map(clientRequest, ClientResponse.class);
    }

    @Override
    public ClientResponse modifyClient(long id, ClientRequest newclient) {
        Client clientRequest = mapper.map(newclient, Client.class);
        Client thisclient = this.getClientById(id);
        if (clientRequest.getNom()!=null)
        {
            thisclient.setNom(clientRequest.getNom());
        }
        if (clientRequest.getPrenom()!=null)
        {
            thisclient.setPrenom(clientRequest.getPrenom());
        }
        if (clientRequest.getDateDeNaissance()!=null)
        {
            thisclient.setDateDeNaissance(clientRequest.getDateDeNaissance());
        }
        if (clientRequest.getCourriel()!=null)
        {
            thisclient.setCourriel(clientRequest.getCourriel());
        }
        if (clientRequest.getTelephone()!=null)
        {
            thisclient.setTelephone(clientRequest.getTelephone());
        }
        clientRepo.save(thisclient);
        return mapper.map(thisclient, ClientResponse.class);
    }

    @Override
    public ClientResponse deleteClientById(long id) {
        Client client = this.getClientById(id);
        clientRepo.deleteById(id);
        return mapper.map(client, ClientResponse.class);
    }

    @Override
    public Client addTicket(long id, Ticket ticket) {
        //adding the ticket to the client
        ticket.setDate(LocalDateTime.of(LocalDateTime.now().getYear(),LocalDateTime.now().getMonth(),
        LocalDateTime.now().getDayOfMonth(), LocalDateTime.now().getHour(), LocalDateTime.now().getMinute()));
        Client client = this.getClientById(id);
        client.getTickets().add(ticket);

        //making the reference with the ticket entity
        ticket.setClient(client);

        //saving the changes
        ticketRepo.save(ticket);
        return clientRepo.save(client);
    }

    @Override
    public ClientResponse getClientInfosById(long id) {
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
        return mapper.map(client,ClientResponse.class);
    }

    @Override
    public ClientResponse getMostLoyalClient() {
        List<Client> clients = clientRepo.findAll();
        Client saveClient = null;
        int max = 0;
        for (Client client:clients)
        {
            if (client.getTickets().size() > max)
            {
                max = client.getTickets().size();
                saveClient = client;
            }
        }
        return mapper.map(saveClient,ClientResponse.class);
    }

    @Override
    public String getMostReservedDayByClient(Client client) {
        List<String> days = clientRepo.GetMostReservedDaysByClient(client);
        String MostReservedDay =
                days.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().max((o1, o2) -> o1.getValue().compareTo(o2.getValue()))
                .map(Map.Entry::getKey).orElse(null);

        return MostReservedDay;
    }

}
