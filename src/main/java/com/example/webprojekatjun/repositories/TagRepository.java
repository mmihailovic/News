package com.example.webprojekatjun.repositories;

import com.example.webprojekatjun.entities.Tag;

import java.util.List;

public interface TagRepository {
    Tag addTag(Tag tag);
    List<Tag> allTags();
    Tag findTag(Integer id);
    List<Tag> allTagsForNews(Integer vest_id);
    List<Tag> updateTags(Integer vest_id, List<Tag> tags);
    void deleteTag(Integer id);
}
