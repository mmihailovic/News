package com.example.webprojekatjun.repositories;

import com.example.webprojekatjun.entities.News;
import com.example.webprojekatjun.requests.NewsUpdateRequest;

import java.util.List;

public interface NewsRepository {
    News addNews(News news);
    List<News> allNews();
    List<News> allNewsWithPagination(Integer page);
    List<News> allNewsForCategory(String ime);

    List<News> allNewsForCategoryWithPagination(String ime, Integer page);

    Integer allNewsForCategoryWithPaginationNumberOfPages(String ime);
    List<News> allNewsForTag(String kljucna_rec);
    List<News> allNewsForTagWithPagination(String kljucna_rec, Integer page);
    News findNews(Integer id);
    boolean updateNews(Integer id, NewsUpdateRequest news);
    void increment(Integer id);
    void deleteNews(Integer id);
}
