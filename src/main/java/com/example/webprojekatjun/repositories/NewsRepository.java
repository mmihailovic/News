package com.example.webprojekatjun.repositories;

import com.example.webprojekatjun.entities.News;
import com.example.webprojekatjun.requests.NewsUpdateRequest;

import java.util.List;

public interface NewsRepository {
    News addNews(News news);
    List<News> allNews();
    List<News> allNewsForCategory(String ime);
    List<News> allNewsForTag(String kljucna_rec);
    News findNews(Integer id);
    boolean updateNews(Integer id, NewsUpdateRequest news);
    void deleteNews(Integer id);
}
