package com.example.webprojekatjun.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Tag {
    private Integer id;
    @NotNull(message = "Polje za kljucnu rec mora biti popunjeno")
    @NotEmpty(message = "Polje za kljucnu rec mora biti popunjeno")
    private String kljucnaRec;

    @NotNull(message = "Polje za vest mora biti popunjeno")
    @NotEmpty(message = "Polje za vest mora biti popunjeno")
    private Integer vest_id;

    public Tag() {
    }

    public Tag(String kljucnaRec, Integer vest_id) {
        this.kljucnaRec = kljucnaRec;
        this.vest_id = vest_id;
    }

    public Tag(Integer id, String kljucnaRec, Integer vest_id) {
        this.id = id;
        this.kljucnaRec = kljucnaRec;
        this.vest_id = vest_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKljucnaRec() {
        return kljucnaRec;
    }

    public void setKljucnaRec(String kljucnaRec) {
        this.kljucnaRec = kljucnaRec;
    }

    public Integer getVest_id() {
        return vest_id;
    }

    public void setVest_id(Integer vest_id) {
        this.vest_id = vest_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(kljucnaRec.toLowerCase(), tag.kljucnaRec.toLowerCase()) && Objects.equals(vest_id, tag.vest_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kljucnaRec, vest_id);
    }
}
