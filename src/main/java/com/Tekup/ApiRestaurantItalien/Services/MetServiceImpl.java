package com.Tekup.ApiRestaurantItalien.Services;

import com.Tekup.ApiRestaurantItalien.DTO.ClientResponse;
import com.Tekup.ApiRestaurantItalien.DTO.MetRequest;
import com.Tekup.ApiRestaurantItalien.DTO.MetResponse;
import com.Tekup.ApiRestaurantItalien.Models.Client;
import com.Tekup.ApiRestaurantItalien.Models.Met;
import com.Tekup.ApiRestaurantItalien.Repositories.MetRepository;
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
public class MetServiceImpl implements MetService {

    private MetRepository metRepo;
    private ModelMapper mapper = new ModelMapper();

    @Autowired
    public MetServiceImpl(MetRepository metRepo) {
        super();
        this.metRepo = metRepo;
    }

    @Override
    public List<Met> getAllMets() {
        return metRepo.findAll();
    }

    @Override
    public Met getMetByName(String nom) {
        Optional<Met> opt = metRepo.findById(nom);
        Met met;
        if (opt.isPresent())
        {
            met = opt.get();
        }
        else
        {
            throw new NoSuchElementException("il n'y a pas un met de ce nom");
        }
        return met;
    }

    @Override
    public MetResponse createMet(MetRequest met)
    {
        Met metRequest = mapper.map(met, Met.class);
        metRepo.save(metRequest);
        return mapper.map(metRequest, MetResponse.class);
    }

    @Override
    public MetResponse modifyMet(String nom, MetRequest newMet)
    {
        Met metRequest = mapper.map(newMet, Met.class);
        Met thisMet = this.getMetByName(nom);
        if (metRequest.getNom()!=null)
        {
            thisMet.setNom(metRequest.getNom());
        }
        if (metRequest.getPrix()>0)
        {
            thisMet.setPrix(metRequest.getPrix());
        }
        metRepo.save(thisMet);
        return mapper.map(thisMet, MetResponse.class);
    }

    @Override
    public MetResponse deleteMetByName(String nom) {
        Met met = this.getMetByName(nom);
        metRepo.deleteById(nom);
        return mapper.map(met, MetResponse.class);
    }
}
