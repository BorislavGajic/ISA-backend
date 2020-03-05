package com.example.ClinicalCenter.controller;

import com.example.ClinicalCenter.dto.KlinikaTipDTO;
import com.example.ClinicalCenter.dto.LekarPacDTO;
import com.example.ClinicalCenter.dto.PacijentEditDTO;
import com.example.ClinicalCenter.model.Klinika;
import com.example.ClinicalCenter.model.Pacijent;
import com.example.ClinicalCenter.service.PacijentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PacijentControllerTest<ExaminationService, PatientService> {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private PacijentService pacijentService;

    @Test
    public void getLekarOdTipaTest() {
        ResponseEntity<List> responseEntity;
        responseEntity = testRestTemplate.getForEntity("/api/pacijenti/getLekarOdTipa/Ginekologija,2600", List.class);

        List<LekarPacDTO> tmp = responseEntity.getBody();
        assertEquals(tmp.size(), 1);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void getTipPregledaKlinikaTest() {
        ResponseEntity<List> responseEntity;
        responseEntity = testRestTemplate.getForEntity("/api/pacijenti/getKlinPac", List.class);

        List<KlinikaTipDTO> tmp = responseEntity.getBody();
        assertEquals(tmp.size(), 2);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void getVremeOdLekaraFTDatumaTest() {
        ResponseEntity<List> responseEntity;
        responseEntity = testRestTemplate.getForEntity("/api/pacijenti/vremeLekaraFTDatum/2020-02-13,marko@gmail.com", List.class);

        List<KlinikaTipDTO> tmp = responseEntity.getBody();
        assertEquals(tmp.size(), 1);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }




}
