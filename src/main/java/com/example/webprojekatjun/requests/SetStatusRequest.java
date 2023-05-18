package com.example.webprojekatjun.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SetStatusRequest {
    @NotNull(message = "Username is required")
    @NotEmpty(message = "Username is required")
    private String username;

    @NotNull(message = "Active is required")
    @NotEmpty(message = "Active is required")
    private boolean aktivan;

    public SetStatusRequest() {
    }

    public SetStatusRequest(String username, boolean aktivan) {
        this.username = username;
        this.aktivan = aktivan;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAktivan() {
        return aktivan;
    }

    public void setAktivan(boolean aktivan) {
        this.aktivan = aktivan;
    }
}
