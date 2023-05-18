package com.example.webprojekatjun.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UpdateUserRequest {
    @NotNull(message = "Polje za email mora biti popunjeno")
    @NotEmpty(message = "Polje za email mora biti popunjeno")
    private String email;

    @NotNull(message = "Polje za email mora biti popunjeno")
    @NotEmpty(message = "Polje za email mora biti popunjeno")
    private String newEmail;
    @NotNull(message = "Polje za ime mora biti popunjeno")
    @NotEmpty(message = "Polje za ime mora biti popunjeno")
    private String ime;
    @NotNull(message = "Polje za prezime mora biti popunjeno")
    @NotEmpty(message = "Polje za prezime mora biti popunjeno")
    private String prezime;
    @NotNull(message = "Polje za tip mora biti popunjeno")
    @NotEmpty(message = "Polje za tip mora biti popunjeno")
    private String tip;

    public UpdateUserRequest(String email, String newEmail, String ime, String prezime, String tip) {
        this.email = email;
        this.newEmail = newEmail;
        this.ime = ime;
        this.prezime = prezime;
        this.tip = tip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
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
}
