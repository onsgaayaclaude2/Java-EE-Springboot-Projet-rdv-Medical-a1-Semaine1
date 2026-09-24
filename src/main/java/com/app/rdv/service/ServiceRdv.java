package com.app.rdv.service;

import com.app.rdv.entities.Rdv;
import com.app.rdv.repository.RdvRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceRdv implements IServiceRdv {

    private final RdvRepository rdvRepository;

    @Override
    public Rdv ajouterRdv(Rdv rdv) {        // ✅ returns Rdv, not Patient
        return rdvRepository.save(rdv);
    }

    @Override
    public List<Rdv> getAllRdv() {
        return rdvRepository.findAll();
    }
}