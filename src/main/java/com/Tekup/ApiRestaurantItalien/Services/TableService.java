package com.Tekup.ApiRestaurantItalien.Services;

import com.Tekup.ApiRestaurantItalien.DTO.TableRequest;
import com.Tekup.ApiRestaurantItalien.DTO.TableResponse;
import com.Tekup.ApiRestaurantItalien.Models.Table;
import com.Tekup.ApiRestaurantItalien.Models.Ticket;

import java.util.List;

/************************************
 ********* author : Khaled ***********
 *** last update : december 22, 2020**
 ************************************/
public interface TableService {

    List<Table> getAllTables();
    Table getTableById(int numero);
    TableResponse createTable(TableRequest table);
    TableResponse modifyTable(int numero,TableRequest newTable);
    TableResponse deleteTableById(int numero);

    Table addTicket(int numero, Ticket ticket);
}
