/* Codes Avec des plus */
/*Voir Code Original en dessous */

package com.app.rdv.service;

import com.app.rdv.entities.Medecin;

import java.util.List;

public interface IServiceMedecin {

    // Ajouter un médecin
    Medecin ajouterMedecin(Medecin medecin);

    // Lister tous les médecins
    List<Medecin> getAllMedecins();


    //Des exemples plus dans le cours
    // Supprimer un médecin par son id (true si supprimé, false s'il n'existe pas)
    boolean supprimerMedecin(int id);
}


/*
package com.app.rdv.service;

import com.app.rdv.entities.Medecin;

import java.util.List;

public interface IServiceMedecin {

    Medecin ajouterMedecin(Medecin medecin);

    List<Medecin> getAllMedecins();
}

*/
