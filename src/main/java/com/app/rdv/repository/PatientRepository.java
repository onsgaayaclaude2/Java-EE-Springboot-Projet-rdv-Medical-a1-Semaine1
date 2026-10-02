package com.app.rdv.repository;

import com.app.rdv.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    // Requête dérivée : SELECT * FROM patient WHERE tel = ?
    // Optional car il se peut qu'aucun patient n'ait ce numéro
    Optional<Patient> findByTel(int tel);
}