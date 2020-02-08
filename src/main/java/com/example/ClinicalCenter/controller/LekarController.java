package com.example.ClinicalCenter.controller;

import com.example.ClinicalCenter.dto.*;
import com.example.ClinicalCenter.model.*;
import com.example.ClinicalCenter.service.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/lekar")
public class LekarController {

    @Autowired
    private LekarService lekarService;

    @Autowired
    private PacijentService pacijentService;

    @Autowired
    private PregledService pregledService;

    @Autowired
    private TerminService terminService;

    @Autowired
    private ReceptService receptService;

    @Autowired
    private IzvestajService izvestajService;


    @GetMapping(value = "/all")
    public ResponseEntity<List<LekarDTO>> getAllLekari() {

        List<Lekar> lekari = lekarService.findAll();

        // convert students to DTOs
        List<LekarDTO> lekariDTO = new ArrayList<>();
        for (Lekar l : lekari) {
            lekariDTO.add(new LekarDTO(l));
        }

        return new ResponseEntity<>(lekariDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/licni_profil/getLekar/{id}")
    public ResponseEntity<LekarDTO> getLekar(@PathVariable Long id) {

        Lekar lekar = lekarService.findOne(id);

        // Proverava da li lekar postoji
        if (lekar == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new LekarDTO(lekar), HttpStatus.OK);
    }
    //'/pacijenti/getAll'
    @GetMapping(path = "/pacijenti/getAll")
    public ResponseEntity<List<PacijentDTO>> getAll(){
        List<Pacijent> pacijenti=pacijentService.findAll();
        List<PacijentDTO> pacijentDTOS= new ArrayList<>();
        for (Pacijent p: pacijenti) {
            if (p.isObrisan() == false) {
                System.out.println(p.getEmail());
                pacijentDTOS.add(new PacijentDTO(p));
            }
        }
        return new ResponseEntity<>(pacijentDTOS, HttpStatus.OK);
    }

    @PostMapping(path = "/izmeniProfil")
    public ResponseEntity<LekarDTO> izmeniLekara(@RequestBody LekarDTO noviLekar) {
        System.out.println(noviLekar.getId());
        System.out.println(noviLekar.getKorIme());

        Lekar lekar = lekarService.save(noviLekar);

        return new ResponseEntity<>(new LekarDTO(lekar), HttpStatus.OK);
    }

    @PostMapping(path = "/zakaziPregled/{id}")
    public ResponseEntity<?> zakaziPregled(@RequestBody PregledDTO pregledDTO, @PathVariable Long id) {
        this.pregledService.zakaziPregled(pregledDTO, id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/obrisi{id}")
    public ResponseEntity<Void> deletePacijent(@PathVariable Long id) {
        Pacijent pacijent = pacijentService.findOne(id);
        pacijentService.delete(pacijent);
        pacijentService.save(pacijent);

        return new ResponseEntity<>((HttpStatus.OK));

    }

    @GetMapping(value = "/getPacijent{id}")
    public Pacijent getPacijent(@PathVariable Long id) {

        return this.pacijentService.findOne(id);
    }
    /*
    @GetMapping(value = "/getDijagPacijent{id}")
    public List<Dijagnoza> getDijagnoze(@PathVariable Long id) {

        return this.dijagnozaService.dijagnozePacijenta(pacijentService.findOne(id));
    }

    */
    @PostMapping(path = "/getTermin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IzvestajLekarDTO> getTime(@RequestBody  UserDateDTO userDateDTO) throws ParseException {
        Lekar lekar = lekarService.findByUsername(userDateDTO.getUsername());
        Set<Termin> termini = lekar.getTermin();
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d=sm.parse(userDateDTO.getDate());
        System.out.println(d);
        for(Termin t : termini){
            Date min = sm.parse(t.getPocetak().toString());
            Date max = sm.parse(t.getKraj().toString());
            if(d.after(min) && d.before(max)){
                Pregled p = t.getPregled();
                Pacijent pac = p.getPacijent();
                Izvestaj i = p.getIzvestaj();
                String text = "";
                Set<Lek> lekovi = new HashSet<>();
                Set<Dijagnoza> dijagnoze = new HashSet<>();
                if(i != null){
                    text = i.getText();
                    lekovi = i.getRecept().getLekovi();
                    dijagnoze = i.getDijagnoze();
                }
                IzvestajLekarDTO dto = new IzvestajLekarDTO(t.getId(),pac.getJbo(),pac.getImePacijenta(),pac.getPrezimePacijenta(),
                       dijagnoze,lekovi,text);
                return new ResponseEntity<>(dto, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/setIzvestaj", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> setIzvestaj(@RequestBody IzvestajLekarDTO izvestajLekarDTO) throws ParseException {
        Termin t = terminService.getById(izvestajLekarDTO.getId());
        Pregled p = t.getPregled();
        Pacijent pac = p.getPacijent();
        Izvestaj i = p.getIzvestaj();
        if(i == null) {
            System.out.print(izvestajLekarDTO.getLekovi().isEmpty());
            Lekar l = lekarService.findByTermin(t);
            Recept r = new Recept(p.getIme_pacijenta()+p.getPrezime_pacijenta(),izvestajLekarDTO.getJbo(), l.getIme()+l.getPrezime(),
                    l.getKlinika().getNazivKlinike(), izvestajLekarDTO.getLekovi(), false);
            Recept rec = receptService.save(r);
            i = new Izvestaj(izvestajLekarDTO.getDijagnoze(),izvestajLekarDTO.getText(),rec);
            Izvestaj izv = izvestajService.save(i);
            p.setIzvestaj(izv);
        }
        else{
            Recept r = i.getRecept();
            r.setLekovi(izvestajLekarDTO.getLekovi());
            Recept rec = receptService.save(r);
            i.setDijagnoze(izvestajLekarDTO.getDijagnoze());
            i.setText(izvestajLekarDTO.getText());
            Izvestaj izv = izvestajService.save(i);
            p.setIzvestaj(izv);
        }
        pregledService.save(p);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
