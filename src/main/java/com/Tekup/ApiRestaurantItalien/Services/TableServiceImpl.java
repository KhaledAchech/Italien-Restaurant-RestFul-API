package com.Tekup.ApiRestaurantItalien.Services;

import com.Tekup.ApiRestaurantItalien.Models.Client;
import com.Tekup.ApiRestaurantItalien.Models.Table;
import com.Tekup.ApiRestaurantItalien.Models.Ticket;
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
public class TableServiceImpl implements TableService{

    private TableRepository tableRepository;
    private TicketRepository ticketRepo;

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
    public Table createTable(Table table) {
        return tableRepository.save(table);
    }

    @Override
    public Table modifyTable(int numero, Table newTable) {
        Table thisTable = this.getTableById(numero);
        if (newTable.getNbCouvert()!=0)
        {
            thisTable.setNbCouvert(newTable.getNbCouvert());
        }
        if (newTable.getSupplement()>=0)
        {
            thisTable.setSupplement(newTable.getSupplement());
        }
        if (newTable.getType()!= null)
        {
            thisTable.setType(newTable.getType());
        }
        return tableRepository.save(thisTable);
    }

    @Override
    public Table deleteTableById(int numero) {
        Table table = this.getTableById(numero);
        tableRepository.deleteById(numero);
        return table;
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
