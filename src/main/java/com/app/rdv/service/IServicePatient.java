package com.app.rdv.service;

import com.app.rdv.entities.Patient;

import java.util.List;

public interface IServicePatient {

    Patient ajouterPatient(Patient patient);
    List<Patient> getAllPatients();

}
