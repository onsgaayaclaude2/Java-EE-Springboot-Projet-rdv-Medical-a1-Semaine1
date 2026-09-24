package com.app.rdv.service;

import com.app.rdv.entities.Rdv;

import java.util.List;

public interface IServiceRdv {

    Rdv ajouterRdv(Rdv rdv);      // ✅ Rdv instead of Patient
    List<Rdv> getAllRdv();

}