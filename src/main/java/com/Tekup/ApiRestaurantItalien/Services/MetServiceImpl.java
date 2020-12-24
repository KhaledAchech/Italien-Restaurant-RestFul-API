package com.Tekup.ApiRestaurantItalien.Services;

import com.Tekup.ApiRestaurantItalien.Models.Client;
import com.Tekup.ApiRestaurantItalien.Models.Met;
import com.Tekup.ApiRestaurantItalien.Repositories.MetRepository;
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
    public Met createMet(Met met) {
        return metRepo.save(met);
    }

    @Override
    public Met modifyMet(String nom, Met newMet) {
        Met thisMet = this.getMetByName(nom);
        if (newMet.getNom()!=null)
        {
            thisMet.setNom(newMet.getNom());
        }
        if (newMet.getPrix()>0)
        {
            thisMet.setPrix(newMet.getPrix());
        }

        return metRepo.save(thisMet);
    }

    @Override
    public Met deleteMetByName(String nom) {
        Met met = this.getMetByName(nom);
        metRepo.deleteById(nom);
        return met;
    }
}
