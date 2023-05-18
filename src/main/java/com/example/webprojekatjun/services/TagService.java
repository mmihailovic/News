package com.example.webprojekatjun.services;

import com.example.webprojekatjun.entities.Tag;
import com.example.webprojekatjun.repositories.TagRepository;

import javax.inject.Inject;
import java.util.List;

public class TagService {
    @Inject
    private TagRepository tagRepository;

    public Tag addTag(Tag tag) {
        return tagRepository.addTag(tag);
    }

    public List<Tag> allTags() {
        return tagRepository.allTags();
    }

    public Tag findTag(String kljucna_rec) {
        return tagRepository.findTag(kljucna_rec);
    }

    public void deleteTag(String kljucna_rec) {
        tagRepository.deleteTag(kljucna_rec);
    }
}
