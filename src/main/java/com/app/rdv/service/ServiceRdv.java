package com.app.rdv.service;

import com.app.rdv.entities.Rdv;
import com.app.rdv.repository.RdvRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServiceRdv implements IServiceRdv {

    private RdvRepository rdvRepository;

    @Override
    public Rdv ajouterRdv(Rdv rdv) {
        //Etape 7
        // rdv1 : le Rdv que ce patient a déjà à cette date (boîte vide s'il n'en a pas)
        Optional<Rdv> rdv1 = rdvRepository.findByPatientIdAndDateRdv(
                rdv.getPatient().getId(), rdv.getDateRdv());

        // rdv2 : le Rdv que ce médecin a déjà à cette date (boîte vide s'il n'en a pas)
        Optional<Rdv> rdv2 = rdvRepository.findByMedecinIdAndDateRdv(
                rdv.getMedecin().getId(), rdv.getDateRdv());

        // Les deux boîtes sont vides => créneau libre => on enregistre le Rdv
        if (rdv1.isEmpty() && rdv2.isEmpty())
            return rdvRepository.save(rdv);

        else
            // Sinon : chevauchement => Rdv refusé
            return null;
    }

    @Override
    public List<Rdv> getAllRdv() {
        // findAll() : retourne la liste de tous les Rdv de la table
        return rdvRepository.findAll();
    }

    @Override
    public List<Rdv> getRdvByDate(LocalDate date) {
        //Etape 7
        // La liste qui va contenir les Rdv de la date demandée
        List<Rdv> resultat = new ArrayList<>();

        // On parcourt tous les Rdv de la table
        for (Rdv rdv : rdvRepository.findAll()) {

            // toLocalDate() : on extrait la date (sans l'heure) de l'attribut dateRdv
            // equals() : on compare cette date avec la date demandée
            if (rdv.getDateRdv().toLocalDate().equals(date))
                resultat.add(rdv);
        }

        return resultat;
    }
}