package com.example.luka.fiskalservisapp;

public class Nalog {

    private String Kvar;
    private String SerijskiBr;
    private String broj;
    private String datum;
    private String kupac;
    private String uređaj;

public Nalog()    {

}

    public String getKvar() {
        return Kvar;
    }

    public void setKvar(String kvar) {
        Kvar = kvar;
    }

    public String getSerijskiBr() {
        return SerijskiBr;
    }

    public void setSerijskiBr(String serijskiBr) {
        SerijskiBr = serijskiBr;
    }

    public String getBroj() {
        return broj;
    }

    public void setBroj(String broj) {
        this.broj = broj;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getKupac() {
        return kupac;
    }

    public void setKupac(String kupac) {
        this.kupac = kupac;
    }

    public String getUređaj() {
        return uređaj;
    }

    public void setUređaj(String uređaj) {
        this.uređaj = uređaj;
    }




    public Nalog(String kvar, String serijskiBr, String broj, String datum, String kupac, String uređaj) {
        Kvar = kvar;
        SerijskiBr = serijskiBr;
        this.broj = broj;
        this.datum = datum;
        this.kupac = kupac;
        this.uređaj = uređaj;


    }
}
