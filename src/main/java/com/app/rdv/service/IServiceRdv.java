package com.app.rdv.service;

import com.app.rdv.entities.Rdv;

import java.time.LocalDate;
import java.util.List;

public interface IServiceRdv {

    //Etape 7
    // Ajouter un Rdv (retourne null s'il y a un chevauchement)
    Rdv ajouterRdv(Rdv rdv);

    // Lister tous les Rdv
    List<Rdv> getAllRdv();

    //Etape 7
    // Lister les Rdv d'une date donnée
    List<Rdv> getRdvByDate(LocalDate date);
}