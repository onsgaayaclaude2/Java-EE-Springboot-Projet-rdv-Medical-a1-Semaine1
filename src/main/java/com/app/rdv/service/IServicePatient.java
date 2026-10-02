package com.app.rdv.service;

import com.app.rdv.entities.Patient;

import java.util.List;
import java.util.Optional;

public interface IServicePatient {

    // Ajouter un patient
    Patient ajouterPatient(Patient patient);

    // Lister tous les patients
    List<Patient> getAllPatients();

    // Supprimer un patient par son id (true si supprimé, false s'il n'existe pas)
    boolean supprimerPatient(int id);

    //Application
    // Chercher un patient par son numéro de téléphone
    Optional<Patient> getPatientByTel(int tel);
}