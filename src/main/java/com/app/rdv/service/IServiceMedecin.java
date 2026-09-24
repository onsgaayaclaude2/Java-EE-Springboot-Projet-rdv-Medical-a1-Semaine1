package com.app.rdv.service;

import com.app.rdv.entities.Medecin;
import com.app.rdv.entities.Patient;

import java.util.List;

public interface IServiceMedecin {

    Medecin ajouterMedecin(Medecin medecin);
    List<Medecin> getAllMedecins();

}
