package com.example.ClinicalCenter.service;

import com.example.ClinicalCenter.dto.LekarDTO;
import com.example.ClinicalCenter.dto.OdsustvoOdmorDTO;
import com.example.ClinicalCenter.model.Lekar;
import com.example.ClinicalCenter.model.OdsustvoOdmor;
import com.example.ClinicalCenter.repository.LekarRepository;
import com.example.ClinicalCenter.repository.OdsustvoOdmorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdsustvoOdmorService {
    @Autowired
    private LekarRepository lekarRepository;
    @Autowired
    private OdsustvoOdmorRepository odsustvoOdmorRepositiry;

    public OdsustvoOdmor zahtev(OdsustvoOdmorDTO odsustvoOdmorDTO, Lekar lekar) {

        OdsustvoOdmor odsustvoOdmor = new OdsustvoOdmor(odsustvoOdmorDTO.getPocetak(),odsustvoOdmorDTO.getKraj(),odsustvoOdmorDTO.isGodisnji(),odsustvoOdmorDTO.isOdsustvo(),odsustvoOdmorDTO.getObrazlozenje());
        odsustvoOdmor.setLekar(lekar);
        lekar.getOdsustvoOdmori().add(odsustvoOdmor);

        return this.odsustvoOdmorRepositiry.save(odsustvoOdmor);
    }

    public List<OdsustvoOdmor> findAll() { return odsustvoOdmorRepositiry.findAll(); }

    public OdsustvoOdmor findById(long id) { return odsustvoOdmorRepositiry.findById(id); }
}
