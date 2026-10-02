package com.app.rdv.service;

import com.app.rdv.entities.Medecin;
import com.app.rdv.repository.MedecinRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceMedecin implements IServiceMedecin {

    private MedecinRepository medecinRepository;

    @Override
    public Medecin ajouterMedecin(Medecin medecin) {
        // save() : enregistre le médecin dans la table et retourne le médecin avec son id
        return medecinRepository.save(medecin);
    }

    @Override
    public List<Medecin> getAllMedecins() {
        // findAll() : retourne la liste de tous les médecins de la table
        return medecinRepository.findAll();
    }

    @Override
    public boolean supprimerMedecin(int id) {
        // existsById() : vérifie si un médecin avec cet id existe dans la table
        if (medecinRepository.existsById(id)) {
            // Le médecin existe => on le supprime
            medecinRepository.deleteById(id);
            return true;
        }

        else
            // Aucun médecin avec cet id => rien à supprimer
            return false;
    }
}

/*

package com.app.rdv.service;

import com.app.rdv.entities.Medecin;
import com.app.rdv.repository.MedecinRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceMedecin implements IServiceMedecin {

    private MedecinRepository medecinRepository;

    @Override
    public Medecin ajouterMedecin(Medecin medecin) {
        return medecinRepository.save(medecin);
    }

    @Override
    public List<Medecin> getAllMedecins() {
        return medecinRepository.findAll();
    }
}


 */