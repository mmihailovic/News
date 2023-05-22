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

    public Tag findTag(Integer id) {
        return tagRepository.findTag(id);
    }

    public List<Tag> allTagsForNews(Integer vest_id) {
        return tagRepository.allTagsForNews(vest_id);
    }

    public void deleteTag(Integer id) {
        tagRepository.deleteTag(id);
    }
}
