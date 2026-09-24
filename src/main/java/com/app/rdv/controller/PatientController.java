package com.app.rdv.controller;

import com.app.rdv.entities.Patient;
import com.app.rdv.service.IServicePatient;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;          // ✅ correct one

import java.util.List;

@RestController
@RequestMapping( "/api/patient")
@AllArgsConstructor



public class PatientController {

    private IServicePatient iServicePatient;

    @GetMapping
    public List<Patient> findAll(){
        return iServicePatient.getAllPatients();
    }

    @PostMapping( "add")
    public Patient addPatient(@RequestBody Patient patient){
        return iServicePatient.ajouterPatient(patient);

    }


}
