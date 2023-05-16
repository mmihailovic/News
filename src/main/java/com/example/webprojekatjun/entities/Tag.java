package com.example.webprojekatjun.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Tag {
    @NotNull(message = "Polje za kljucnu rec mora biti popunjeno")
    @NotEmpty(message = "Polje za kljucnu rec mora biti popunjeno")
    private String kljucnaRec;

    public Tag(String kljucnaRec) {
        this.kljucnaRec = kljucnaRec;
    }

    public String getKljucnaRec() {
        return kljucnaRec;
    }

    public void setKljucnaRec(String kljucnaRec) {
        this.kljucnaRec = kljucnaRec;
    }
}
