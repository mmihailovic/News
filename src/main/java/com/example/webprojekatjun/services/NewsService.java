package com.example.webprojekatjun.services;

import com.example.webprojekatjun.entities.News;
import com.example.webprojekatjun.repositories.NewsRepository;

import javax.inject.Inject;
import java.util.List;

public class NewsService {
    @Inject
    private NewsRepository newsRepository;

    public News addNews(News news) {
        return this.newsRepository.addNews(news);
    }

    public List<News> allNews() {
        return this.newsRepository.allNews();
    }

    public List<News> allNewsForCategory(String ime) {
        return this.newsRepository.allNewsForCategory(ime);
    }

    public List<News> allNewsForTag(String kljucna_rec) {
        return this.newsRepository.allNewsForTag(kljucna_rec);
    }

    public News findNews(Integer id) {
        return this.newsRepository.findNews(id);
    }

    public void deleteNews(Integer id) {
        this.newsRepository.deleteNews(id);
    }
}
