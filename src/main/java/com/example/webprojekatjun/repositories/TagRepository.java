package com.example.webprojekatjun.repositories;

import com.example.webprojekatjun.entities.Tag;

import java.util.List;

public interface TagRepository {
    Tag addTag(Tag tag);
    List<Tag> allTags();
    Tag findTag(String kljucna_rec);
    void deleteTag(String kljucna_rec);
}
