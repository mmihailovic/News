package com.example.webprojekatjun.resources;

import com.example.webprojekatjun.entities.News;
import com.example.webprojekatjun.requests.NewsUpdateRequest;
import com.example.webprojekatjun.services.NewsService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Path("/news")
public class NewsResource {
    @Inject
    private NewsService newsService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all(){
        List<News> news = newsService.allNews();
        Collections.sort(news, Comparator.comparing(News::getDate).reversed());
        return Response.ok(news).build();
    }

    @GET
    @Path("/page/{page}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allWithPagination(@PathParam("page") Integer page) {
        List<News> news = newsService.allNews();
        Collections.sort(news, Comparator.comparing(News::getDate).reversed());
        return Response.ok(news.subList((page-1)*3,Math.min(news.size(), page*3))).build();
    }

    @GET
    @Path("/top10")
    @Produces(MediaType.APPLICATION_JSON)
    public Response top10(){
        List<News> news = newsService.allNews();
        Collections.sort(news, Comparator.comparing(News::getBrojPoseta).reversed());
        List<News> top = new ArrayList<>();
        long currentDate = new Date().getTime();
        int count = 0;
        for(News n: news) {
            long diff = currentDate - n.getDate();
            if(TimeUnit.MILLISECONDS.toDays(diff) <= 30) {
                top.add(n);
                count++;
            }
            if(count == 10)
                break;
        }
        return Response.ok(top).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid News news) {
        return Response.ok(newsService.addNews(news)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") Integer id) {
        return Response.ok(newsService.findNews(id)).build();
    }

    @POST
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateNews(@PathParam("id") Integer id, NewsUpdateRequest news) {
        return Response.ok(newsService.updateNews(id, news)).build();
    }

    @GET
    @Path("/category/{ime}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allNewsForCategory(@PathParam("ime") String ime) {
        List<News> news = newsService.allNewsForCategory(ime);
        Collections.sort(news, Comparator.comparing(News::getDate).reversed());
        return Response.ok(news).build();
    }

    @GET
    @Path("/category/{ime}/page/{page}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allNewsForCategoryWithPagination(@PathParam("page") Integer page, @PathParam("ime") String ime) {
        List<News> news = newsService.allNewsForCategory(ime);
        Collections.sort(news, Comparator.comparing(News::getDate).reversed());
        return Response.ok(news.subList((page-1)*3,Math.min(news.size(), page*3))).build();
    }

    @GET
    @Path("/tag/{kljucna_rec}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allNewsForTag(@PathParam("kljucna_rec") String kljucna_rec) {
        List<News> news = newsService.allNewsForTag(kljucna_rec);
        Collections.sort(news, Comparator.comparing(News::getDate).reversed());
        return Response.ok(news).build();
    }

    @GET
    @Path("/tag/{kljucna_rec}/page/{page}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allNewsForTagWithPagination(@PathParam("page") Integer page, @PathParam("kljucna_rec") String kljucna_rec) {
        List<News> news = newsService.allNewsForTag(kljucna_rec);
        Collections.sort(news, Comparator.comparing(News::getDate).reversed());
        return Response.ok(news.subList((page-1)*3,Math.min(news.size(), page*3))).build();
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) {
        newsService.deleteNews(id);
    }
}
