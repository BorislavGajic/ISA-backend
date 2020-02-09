package com.example.ClinicalCenter.repository;


import com.example.ClinicalCenter.model.Lekar;
import com.example.ClinicalCenter.model.Termin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TerminRepository extends JpaRepository<Termin, Long> {

    List<Termin> findAllById(long id);

import java.util.List;
import java.util.Optional;

public interface TerminRepository extends JpaRepository<Termin, Long> {

    Termin save(Termin termin);
    List<Termin> findAll();
}
