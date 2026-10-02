/* Codes Avec des plus */
/*Voir Code Original en dessous */


package com.app.rdv.controller;

import com.app.rdv.entities.Medecin;
import com.app.rdv.service.IServiceMedecin;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medecin")
@AllArgsConstructor
public class MedecinController {

    private IServiceMedecin iServiceMedecin;

    @GetMapping
    public List<Medecin> findAll() {
        return iServiceMedecin.getAllMedecins();
    }

    // Ajout d'un médecin avec ResponseEntity
    @PostMapping("add")
    @ApiResponses(value = {
            // 201 : le médecin a été créé, la réponse contient un objet Medecin en JSON
            @ApiResponse(responseCode = "201", description = "Medecin Added",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Medecin.class)))
    })
    public ResponseEntity<Medecin> addMedecin(@RequestBody Medecin medecin) {

        // On appelle le service une seule fois et on garde le résultat
        Medecin medecin1 = iServiceMedecin.ajouterMedecin(medecin);

        // Le médecin a été enregistré => 201 CREATED
        return new ResponseEntity<>(medecin1, HttpStatus.CREATED);
    }

    // Suppression d'un médecin par son id
    @DeleteMapping("delete/{id}")
    @ApiResponses(value = {
            // 200 : le médecin a été supprimé
            @ApiResponse(responseCode = "200", description = "Medecin Deleted",
                    content = @Content(mediaType = "text/plain",
                            schema = @Schema(type = "string"))),

            // 404 : aucun médecin avec cet id
            @ApiResponse(responseCode = "404", description = "Medecin Not Found",
                    content = @Content(mediaType = "text/plain",
                            schema = @Schema(type = "string")))
    })
    public ResponseEntity<String> deleteMedecin(@PathVariable int id) {
        if (iServiceMedecin.supprimerMedecin(id))
            return new ResponseEntity<>("Médecin supprimé", HttpStatus.OK);

        else
            return new ResponseEntity<>("Médecin introuvable", HttpStatus.NOT_FOUND);
    }
}


/* Code Original */

/*package com.app.rdv.controller;

import com.app.rdv.entities.Medecin;
import com.app.rdv.service.IServiceMedecin;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medecin")
@AllArgsConstructor
public class MedecinController {

    private IServiceMedecin iServiceMedecin;

    @GetMapping
    public List<Medecin> findAll() {
        return iServiceMedecin.getAllMedecins();
    }

    @PostMapping("add")
    public Medecin addMedecin(@RequestBody Medecin medecin) {
        return iServiceMedecin.ajouterMedecin(medecin);
    }
}
*/

