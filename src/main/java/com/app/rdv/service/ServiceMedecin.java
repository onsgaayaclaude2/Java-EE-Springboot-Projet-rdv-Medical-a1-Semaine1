package com.app.rdv.service;

import com.app.rdv.entities.Medecin;
import com.app.rdv.repository.MedecinRepository;   // ✅ was PatientRepository
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceMedecin implements IServiceMedecin {

    private final MedecinRepository medecinRepository;   // ✅ was PatientRepository

    @Override
    public Medecin ajouterMedecin(Medecin medecin) {    // ✅ returns Medecin, not Patient
        return medecinRepository.save(medecin);
    }

    @Override
    public List<Medecin> getAllMedecins() {
        return medecinRepository.findAll();
    }
}