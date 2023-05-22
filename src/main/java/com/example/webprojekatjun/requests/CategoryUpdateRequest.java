package com.example.webprojekatjun.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CategoryUpdateRequest {
    @NotNull(message = "Polje za ime mora biti popunjeno")
    @NotEmpty(message = "Polje za ime mora biti popunjeno")
    private String ime;
    @NotNull(message = "Polje za novo ime mora biti popunjeno")
    @NotEmpty(message = "Polje za novo ime mora biti popunjeno")
    private String novoIme;
    @NotNull(message = "Polje za opis mora biti popunjeno")
    @NotEmpty(message = "Polje za opis mora biti popunjeno")
    private String opis;

    public CategoryUpdateRequest() {
    }

    public CategoryUpdateRequest(String ime, String novoIme, String opis) {
        this.ime = ime;
        this.novoIme = novoIme;
        this.opis = opis;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getNovoIme() {
        return novoIme;
    }

    public void setNovoIme(String novoIme) {
        this.novoIme = novoIme;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
