package com.example.ClinicalCenter.repository;

import com.example.ClinicalCenter.model.Lekar;
import com.example.ClinicalCenter.model.Termin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LekarRepository extends JpaRepository<Lekar, Long> {
    Lekar findByKorIme(String KorIme);

    Lekar findByEmail(String email);

    Lekar findByTermin(Termin t);
}
