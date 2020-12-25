package com.Tekup.ApiRestaurantItalien.Endpoints;

import com.Tekup.ApiRestaurantItalien.DTO.TableRequest;
import com.Tekup.ApiRestaurantItalien.DTO.TableResponse;
import com.Tekup.ApiRestaurantItalien.Models.Client;
import com.Tekup.ApiRestaurantItalien.Models.Table;
import com.Tekup.ApiRestaurantItalien.Models.Ticket;
import com.Tekup.ApiRestaurantItalien.Services.TableService;
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
@RequestMapping("/api/tables")
public class TableRest {

    private TableService tableService;

    @Autowired
    public TableRest(TableService tableService) {
        super();
        this.tableService = tableService;
    }

    @GetMapping
    public List<Table> getAllTables()
    {
        return tableService.getAllTables();
    }

    @GetMapping("/{numero}")
    public Table getTableById(@PathVariable int numero)
    {
        return tableService.getTableById(numero);
    }

    @PostMapping
    public TableResponse creatTable(@RequestBody TableRequest table)
    {
        return tableService.createTable(table);
    }

    @PutMapping("/{numero}")
    public TableResponse modifyTable(@PathVariable int numero, @RequestBody TableRequest newTable)
    {
        return tableService.modifyTable(numero,newTable);
    }

    @DeleteMapping("/{numero}")
    public TableResponse deleteTable(@PathVariable int numero)
    {
        return tableService.deleteTableById(numero);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e)
    {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addTicket/{numero}")
    public Table addTicketToTable(@PathVariable int numero, @RequestBody Ticket ticket)
    {
        return tableService.addTicket(numero,ticket);
    }
}
