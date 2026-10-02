package com.app.rdv.service;

import com.app.rdv.entities.Patient;
import com.app.rdv.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServicePatient implements IServicePatient {

    private PatientRepository patientRepository;

    @Override
    public Patient ajouterPatient(Patient patient) {
        // save() : enregistre le patient dans la table et retourne le patient avec son id
        return patientRepository.save(patient);
    }

    @Override
    public List<Patient> getAllPatients() {
        // findAll() : retourne la liste de tous les patients de la table
        return patientRepository.findAll();
    }

    @Override
    public boolean supprimerPatient(int id) {
        // existsById() : vérifie si un patient avec cet id existe dans la table
        if (patientRepository.existsById(id)) {
            // Le patient existe => on le supprime
            patientRepository.deleteById(id);
            return true;
        }

        else
            // Aucun patient avec cet id => rien à supprimer
            return false;
    }

    @Override
    public Optional<Patient> getPatientByTel(int tel) {
        //Application
        // findByTel() : retourne une boîte Optional
        // => elle contient le patient s'il existe, sinon elle est vide
        return patientRepository.findByTel(tel);
    }
}