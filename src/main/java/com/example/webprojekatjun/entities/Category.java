package com.example.webprojekatjun.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Category {
    @NotNull(message = "Polje za ime mora biti popunjeno")
    @NotEmpty(message = "Polje za ime mora biti popunjeno")
    private String ime;
    @NotNull(message = "Polje za opis mora biti popunjeno")
    @NotEmpty(message = "Polje za opis mora biti popunjeno")
    private String opis;

    public Category(String ime, String opis) {
        this.ime = ime;
        this.opis = opis;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
