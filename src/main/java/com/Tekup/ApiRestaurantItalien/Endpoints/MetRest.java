package com.Tekup.ApiRestaurantItalien.Endpoints;

import com.Tekup.ApiRestaurantItalien.DTO.MetRequest;
import com.Tekup.ApiRestaurantItalien.DTO.MetResponse;
import com.Tekup.ApiRestaurantItalien.Models.Met;
import com.Tekup.ApiRestaurantItalien.Services.MetService;
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
@RequestMapping("/api/mets")
public class MetRest {

    private MetService metService;

    @Autowired
    public MetRest(MetService metService) {
        super();
        this.metService = metService;
    }

    @GetMapping
    public List<Met> getAllMets()
    {
        return metService.getAllMets();
    }

    @GetMapping("/{nom}")
    public Met getMetByName(@PathVariable String nom)
    {
        return metService.getMetByName(nom);
    }

    @PostMapping
    public MetResponse createMet(@RequestBody@Valid MetRequest met)
    {
        return metService.createMet(met);
    }

    @PutMapping("/{nom}")
    public MetResponse modifyMet(@PathVariable String nom, @RequestBody@Valid MetRequest newMet)
    {
        return metService.modifyMet(nom,newMet);
    }

    @DeleteMapping("/{nom}")
    public MetResponse deleteMet(@PathVariable String nom)
    {
        return metService.deleteMetByName(nom);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
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
