package com.example.ClinicalCenter.service;

import com.example.ClinicalCenter.model.Sestra;
import com.example.ClinicalCenter.repository.SestraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SestraService {

    @Autowired
    private SestraRepository sestraRepository;

    public Sestra FindByUsername(String username){return sestraRepository.findByUsername(username);}

    public Sestra save(Sestra sestra) {return  sestraRepository.save(sestra);}

    public Sestra FindByEmail(String email){return  sestraRepository.findByEmail(email);}
}
