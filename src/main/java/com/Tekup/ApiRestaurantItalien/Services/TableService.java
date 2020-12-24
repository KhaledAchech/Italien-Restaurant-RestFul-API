package com.Tekup.ApiRestaurantItalien.Services;

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
    Table createTable(Table table);
    Table modifyTable(int numero,Table newTable);
    Table deleteTableById(int numero);

    Table addTicket(int numero, Ticket ticket);
}
