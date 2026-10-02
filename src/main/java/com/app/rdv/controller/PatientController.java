package com.app.rdv.controller;

import com.app.rdv.entities.Patient;
import com.app.rdv.service.IServicePatient;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patient")
@AllArgsConstructor
public class PatientController {

    private IServicePatient iServicePatient;

    @GetMapping
    public List<Patient> findAll() {
        return iServicePatient.getAllPatients();
    }

    @PostMapping("add")
    public Patient addPatient(@RequestBody Patient patient) {
        return iServicePatient.ajouterPatient(patient);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable int id) {
        if (iServicePatient.supprimerPatient(id)) {
            return new ResponseEntity<>("Patient supprimé", HttpStatus.OK);
        }
        return new ResponseEntity<>("Patient introuvable", HttpStatus.NOT_FOUND);
    }

    // Recherche d'un patient par son numéro de téléphone
    @GetMapping("tel")
    @ApiResponses(value = {
            // 302 : patient trouvé, la réponse contient un objet Patient en JSON
            @ApiResponse(responseCode = "302", description = "Patient Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Patient.class))),

            // 404 : aucun patient avec ce numéro, la réponse contient un message texte
            @ApiResponse(responseCode = "404", description = "Patient Not Found",
                    content = @Content(mediaType = "text/plain",
                            schema = @Schema(type = "string")))
    })
    public ResponseEntity<?> findByTel(@RequestParam int tel) {

        // On appelle le service une seule fois : on reçoit une boîte Optional
        Optional<Patient> patient = iServicePatient.getPatientByTel(tel);

        // isPresent() : la boîte contient un patient => trouvé => 302 FOUND
        if (patient.isPresent()) {
            // get() : on sort le patient de la boîte pour le mettre dans la réponse
            return new ResponseEntity<>(patient.get(), HttpStatus.FOUND);
        }

        // La boîte est vide => aucun patient avec ce numéro => 404 NOT_FOUND
        return new ResponseEntity<>("Patient introuvable", HttpStatus.NOT_FOUND);
    }
}