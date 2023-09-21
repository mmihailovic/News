package com.example.webprojekatjun.services;

import com.example.webprojekatjun.entities.News;
import com.example.webprojekatjun.repositories.NewsRepository;
import com.example.webprojekatjun.requests.NewsUpdateRequest;

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

    public List<News> allNewsWithPagination(Integer page) {
        return this.newsRepository.allNewsWithPagination(page);
    }

    public List<News> allNewsForCategoryWithPagination(String ime, Integer page) {
        return this.newsRepository.allNewsForCategoryWithPagination(ime, page);
    }

    public Integer allNewsForCategoryWithPaginationNumberOfPages(String ime) {
        return this.newsRepository.allNewsForCategoryWithPaginationNumberOfPages(ime);
    }

    public List<News> allNewsForCategory(String ime) {
        return this.newsRepository.allNewsForCategory(ime);
    }

    public List<News> allNewsForTag(String kljucna_rec) {
        return this.newsRepository.allNewsForTag(kljucna_rec);
    }
    public List<News> allNewsForTagWithPagination(String kljucna_rec, Integer page) {
        return this.newsRepository.allNewsForTagWithPagination(kljucna_rec, page);
    }
    public News findNews(Integer id) {
        this.newsRepository.increment(id);
        return this.newsRepository.findNews(id);
    }

    public boolean updateNews(Integer id, NewsUpdateRequest news) {
        return this.newsRepository.updateNews(id, news);
    }

    public void deleteNews(Integer id) {
        this.newsRepository.deleteNews(id);
    }
}
