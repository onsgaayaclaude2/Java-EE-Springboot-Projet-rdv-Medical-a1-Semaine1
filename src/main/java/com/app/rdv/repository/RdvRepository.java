package com.app.rdv.repository;

import com.app.rdv.entities.Medecin;
import com.app.rdv.entities.Rdv;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RdvRepository extends JpaRepository<Rdv, Integer> {
}
