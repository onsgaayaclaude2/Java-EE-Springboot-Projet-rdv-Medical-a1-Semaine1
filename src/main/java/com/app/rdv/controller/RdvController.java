package com.app.rdv.controller;

import com.app.rdv.entities.Rdv;
import com.app.rdv.service.IServiceRdv;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/rdv")
@AllArgsConstructor
public class RdvController {

    private IServiceRdv iServiceRdv;

    @GetMapping
    public List<Rdv> findAll() {
        return iServiceRdv.getAllRdv();
    }

    //Etape 8
    // Notion ResponseEntity : on choisit nous-mêmes le code HTTP de la réponse
    @PostMapping("add")
    @ApiResponses(value = {
            // 201 : le Rdv a été créé, la réponse contient un objet Rdv en JSON
            @ApiResponse(responseCode = "201", description = "Rdv Added",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Rdv.class))),

            // 409 : chevauchement, la réponse contient un simple message texte
            @ApiResponse(responseCode = "409", description = "Rdv Not Added",
                    content = @Content(mediaType = "text/plain",
                            schema = @Schema(type = "string")))
    })
    public ResponseEntity<?> addRdv(@RequestBody Rdv rdv) {

        // On appelle le service UNE seule fois et on garde le résultat
        Rdv rdv1 = iServiceRdv.ajouterRdv(rdv);

        // rdv1 n'est pas null => le Rdv a été enregistré => 201 CREATED
        if (rdv1 != null)
            return new ResponseEntity<>(rdv1, HttpStatus.CREATED);

        else
            // rdv1 est null => chevauchement => 409 CONFLICT avec un message
            return new ResponseEntity<>("Le Rdv ne peut pas être créé", HttpStatus.CONFLICT);
    }

    //Etape 7 et 8
    // Recherche des Rdv d'une date donnée
    @GetMapping("date")
    @ApiResponses(value = {
            // 200 : des Rdv existent à cette date, la réponse contient une liste de Rdv en JSON
            @ApiResponse(responseCode = "200", description = "Rdv Found",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Rdv.class)))),

            // 404 : aucun Rdv à cette date, la réponse contient un message texte
            @ApiResponse(responseCode = "404", description = "Rdv Not Found",
                    content = @Content(mediaType = "text/plain",
                            schema = @Schema(type = "string")))
    })
    public ResponseEntity<?> findByDate(@RequestParam String date) {

        // Le paramètre arrive en String (ex : "2026-10-12") => on le convertit en LocalDate
        LocalDate date1 = LocalDate.parse(date);

        // On appelle le service une seule fois et on garde le résultat
        List<Rdv> rdvs = iServiceRdv.getRdvByDate(date1);

        // La liste n'est pas vide => des Rdv existent à cette date => 200 OK
        if (!rdvs.isEmpty())
            return new ResponseEntity<>(rdvs, HttpStatus.OK);

        else
            // La liste est vide => aucun Rdv à cette date => 404 NOT_FOUND
            return new ResponseEntity<>("Aucun Rdv à cette date", HttpStatus.NOT_FOUND);
    }
}