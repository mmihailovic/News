package com.example.webprojekatjun.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RegisterRequest {
    @NotNull(message = "Username is required")
    @NotEmpty(message = "Username is required")
    private String username;

    @NotNull(message = "Password is required")
    @NotEmpty(message = "Password is required")
    private String password;
    @NotNull(message = "Polje za ime mora biti popunjeno")
    @NotEmpty(message = "Polje za ime mora biti popunjeno")
    private String ime;

    @NotNull(message = "Polje za prezime mora biti popunjeno")
    @NotEmpty(message = "Polje za prezime mora biti popunjeno")
    private String prezime;
    @NotNull(message = "Polje za tip korisnika mora biti popunjeno")
    @NotEmpty(message = "Polje za tip korisnika mora biti popunjeno")
    private String tip;

    public RegisterRequest() {
    }

    public RegisterRequest(String username, String password, String ime, String prezime, String tip) {
        this.username = username;
        this.password = password;
        this.ime = ime;
        this.prezime = prezime;
        this.tip = tip;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
