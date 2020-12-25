package com.Tekup.ApiRestaurantItalien.Services;

import com.Tekup.ApiRestaurantItalien.DTO.ClientResponse;
import com.Tekup.ApiRestaurantItalien.DTO.TableRequest;
import com.Tekup.ApiRestaurantItalien.DTO.TableResponse;
import com.Tekup.ApiRestaurantItalien.Models.Client;
import com.Tekup.ApiRestaurantItalien.Models.Table;
import com.Tekup.ApiRestaurantItalien.Models.Ticket;
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
public class TableServiceImpl implements TableService{

    private TableRepository tableRepository;
    private TicketRepository ticketRepo;
    private ModelMapper mapper = new ModelMapper();

    @Autowired
    public TableServiceImpl(TableRepository tableRepository,TicketRepository ticketRepo) {
        super();
        this.tableRepository = tableRepository;
        this.ticketRepo = ticketRepo;
    }

    @Override
    public List<Table> getAllTables() {
        return tableRepository.findAll();
    }

    @Override
    public Table getTableById(int numero) {
        Optional<Table> opt = tableRepository.findById(numero);
        Table table;
        if (opt.isPresent())
        {
            table = opt.get();
        }
        else
        {
            throw new NoSuchElementException("il n'y a pas une table avec le numero saisi");
        }
        return table;
    }

    @Override
    public TableResponse createTable(TableRequest table)
    {
        Table tableRequest = mapper.map(table, Table.class);
        tableRepository.save(tableRequest);
        return mapper.map(tableRequest, TableResponse.class);
    }

    @Override
    public TableResponse modifyTable(int numero, TableRequest newTable)
    {
        Table tableRequest = mapper.map(newTable, Table.class);
        Table thisTable = this.getTableById(numero);
        if (tableRequest.getNbCouvert()!=0)
        {
            thisTable.setNbCouvert(tableRequest.getNbCouvert());
        }
        if (tableRequest.getSupplement()>=0)
        {
            thisTable.setSupplement(tableRequest.getSupplement());
        }
        if (tableRequest.getType()!= null)
        {
            thisTable.setType(tableRequest.getType());
        }
        tableRepository.save(thisTable);
        return mapper.map(thisTable, TableResponse.class);
    }

    @Override
    public TableResponse deleteTableById(int numero) {
        Table table = this.getTableById(numero);
        tableRepository.deleteById(numero);
        return mapper.map(table, TableResponse.class);
    }

    @Override
    public Table addTicket(int numero, Ticket ticket) {
        //adding the ticket to the table
        Table table = this.getTableById(numero);
        table.getTickets().add(ticket);
        table.setNbCouvert(ticket.getNbCouvert());

        //making the reference with the ticket entity
        ticket.setAddition(ticket.getAddition()+table.getSupplement());
        ticket.setTable(table);

        //saving the changes
        ticketRepo.save(ticket);
        return tableRepository.save(table);
    }


}
