package com.example.webprojekatjun.repositories;

import com.example.webprojekatjun.entities.News;

import java.util.List;

public interface NewsRepository {
    News addNews(News news);
    List<News> allNews();
    List<News> allNewsForCategory(String ime);
    List<News> allNewsForTag(String kljucna_rec);
    News findNews(Integer id);
    void deleteNews(Integer id);
}
