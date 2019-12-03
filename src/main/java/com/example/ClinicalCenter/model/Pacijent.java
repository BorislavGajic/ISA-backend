package com.example.ClinicalCenter.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pacijent {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Ime", nullable = false)
    private String ImePacijenta;

    @Column(name = "Prezime", nullable = false)
    private String PrezimePacijenta;

    @Column(name = "E_mail", nullable = false)
    private String E_mail;

    @Column(name = "Lozinka", nullable = false)
    private String Lozinka;

    @Column(name = "AdresaPrebivalista", nullable = false)
    private String Adresa;

    @Column(name = "Grad", nullable = false)
    private String Grad;

    @Column(name = "Drzava", nullable = false)
    private String Drzava;

    @Column(name = "BrojTelefona", nullable = false)
    private String BrojTelefona;

    @Column(name = "JBO", nullable = false)
    private Integer JBO;

    @Column(name = "odobren", nullable = false)
    private Boolean odobren;

    @Column(name = "Potvrdio", nullable = false)
    private Boolean Potvrdio;

    public Pacijent() {
        ImePacijenta = "";
        PrezimePacijenta = "";
        E_mail = "";
        Lozinka= "";
        Adresa = "";
        Grad = "";
        Drzava = "";
        BrojTelefona = "";
        JBO = 0;
        odobren = false;
        Potvrdio= false;
    }

    public Pacijent(Long id, String ImePacijenta, String PrezimePacijenta, String E_mail, String Lozinka, String Adresa, String Grad, String Drzava,String BrojTelefona, int JBO, boolean odobren, boolean potvrdio) {
        this.id = id;
        this.ImePacijenta = ImePacijenta;
        this.PrezimePacijenta = PrezimePacijenta;
        this.E_mail= E_mail;
        this.Lozinka = Lozinka;
        this.Adresa = Adresa;
        this.Grad = Grad;
        this.Drzava = Drzava;
        this.BrojTelefona = BrojTelefona;
        this.JBO = JBO;
        this.odobren= odobren;
        this.Potvrdio=potvrdio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImePacijenta() {
        return ImePacijenta;
    }

    public void setImePacijenta(String imePacijenta) {
        ImePacijenta = imePacijenta;
    }

    public String getPrezimePacijenta() {
        return PrezimePacijenta;
    }

    public void setPrezimePacijenta(String prezimePacijenta) {
        PrezimePacijenta = prezimePacijenta;
    }

    public String getE_mail() {
        return E_mail;
    }

    public void setE_mail(String e_mail) {
        E_mail = e_mail;
    }

    public String getLozinka() {
        return Lozinka;
    }

    public void setLozinka(String lozinka) {
        Lozinka = lozinka;
    }

    public String getAdresa() {
        return Adresa;
    }

    public void setAdresa(String adresa) {
        Adresa = adresa;
    }

    public String getGrad() {
        return Grad;
    }

    public void setGrad(String grad) {
        Grad = grad;
    }

    public String getDrzava() {
        return Drzava;
    }

    public void setDrzava(String drzava) {
        Drzava = drzava;
    }

    public String getBrojTelefona() {
        return BrojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        BrojTelefona = brojTelefona;
    }

    public void setJBO(Integer JBO) {
        this.JBO = JBO;
    }

    public Boolean getOdobren() {
        return odobren;
    }

    public void setOdobren(Boolean odobren) {
         odobren = odobren;
    }

    public int getJBO() {
        return JBO;
    }

    public void setJBO(int JBO) {
        this.JBO = JBO;
    }
}
