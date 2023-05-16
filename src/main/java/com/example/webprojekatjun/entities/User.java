package com.example.webprojekatjun.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class User {
    @NotNull(message = "Polje za email mora biti popunjeno")
    @NotEmpty(message = "Polje za email mora biti popunjeno")
    private String email;
    @NotNull(message = "Polje za sifru mora biti popunjeno")
    @NotEmpty(message = "Polje za sifru mora biti popunjeno")
    private String hashedPassword;
    @NotNull(message = "Polje za ime mora biti popunjeno")
    @NotEmpty(message = "Polje za ime mora biti popunjeno")
    private String ime;
    @NotNull(message = "Polje za prezime mora biti popunjeno")
    @NotEmpty(message = "Polje za prezime mora biti popunjeno")
    private String prezime;
    @NotNull(message = "Polje za tip mora biti popunjeno")
    @NotEmpty(message = "Polje za tip mora biti popunjeno")
    private String tip;
    @NotNull(message = "Polje za aktivan mora biti popunjeno")
    @NotEmpty(message = "Polje za aktivan mora biti popunjeno")
    private boolean aktivan;

    public User() {
    }

    public User(String email, String hashedPassword, String ime, String prezime, String tip, boolean aktivan) {
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.ime = ime;
        this.prezime = prezime;
        this.tip = tip;
        this.aktivan = aktivan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public boolean isAktivan() {
        return aktivan;
    }

    public void setAktivan(boolean aktivan) {
        this.aktivan = aktivan;
    }
}
