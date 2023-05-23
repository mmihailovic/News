package com.example.webprojekatjun.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NewsUpdateRequest {
    @NotNull(message = "Polje za naslov mora biti popunjeno")
    @NotEmpty(message = "Polje za naslov mora biti popunjeno")
    private String naslov;
    @NotNull(message = "Polje za tekst mora biti popunjeno")
    @NotEmpty(message = "Polje za tekst mora biti popunjeno")
    private String tekst;
    @NotNull(message = "Polje za kategoriju mora biti popunjeno")
    @NotEmpty(message = "Polje za kategoriju mora biti popunjeno")
    private String kategorija;

    public NewsUpdateRequest() {
    }

    public NewsUpdateRequest(String naslov, String tekst, String kategorija) {
        this.naslov = naslov;
        this.tekst = tekst;
        this.kategorija = kategorija;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }
}
