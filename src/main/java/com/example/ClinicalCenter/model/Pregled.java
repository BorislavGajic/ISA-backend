package com.example.ClinicalCenter.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;


@Entity
public class Pregled {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName", nullable = false)
    private String ime_pacijenta;

    @Column(name = "lastName", nullable = false)
    private String prezime_pacijenta;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "jbo", nullable = false)
    private int jbo;

    @OneToOne
    private Termin termin;

    @OneToOne
    private Lekar lekar;


    public Pregled() {
    }

    public Pregled(Long id, String ime_pacijenta, String prezime_pacijenta, String email, int jbo) {
        this.id = id;
        this.ime_pacijenta = ime_pacijenta;
        this.prezime_pacijenta = prezime_pacijenta;
        this.email = email;
        this.jbo = jbo;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme_pacijenta() {
        return ime_pacijenta;
    }

    public void setIme_pacijenta(String ime_pacijenta) {
        this.ime_pacijenta = ime_pacijenta;
    }

    public String getPrezime_pacijenta() {
        return prezime_pacijenta;
    }

    public void setPrezime_pacijenta(String prezime_pacijenta) {
        this.prezime_pacijenta = prezime_pacijenta;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getJbo() {
        return jbo;
    }

    public void setJbo(int jbo) {
        this.jbo = jbo;
    }

    public Termin getTermin() {
        return termin;
    }

    public void setTermin(Termin termin) {
        this.termin = termin;
    }

    public Lekar getLekar() {
        return lekar;
    }

    public void setLekar(Lekar lekar) {
        this.lekar = lekar;
    }
}
