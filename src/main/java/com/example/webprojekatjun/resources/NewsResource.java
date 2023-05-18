package com.example.webprojekatjun.resources;

import com.example.webprojekatjun.entities.News;
import com.example.webprojekatjun.services.NewsService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/news")
public class NewsResource {
    @Inject
    private NewsService newsService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all(){
        return Response.ok(newsService.allNews()).build();
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

    @GET
    @Path("/category/{ime}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allNewsForCategory(@PathParam("ime") String ime) {
        return Response.ok(newsService.allNewsForCategory(ime)).build();
    }

    @GET
    @Path("/tag/{kljucna_rec}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allNewsForTag(@PathParam("kljucna_rec") String kljucna_rec) {
        return Response.ok(newsService.allNewsForTag(kljucna_rec)).build();
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) {
        newsService.deleteNews(id);
    }
}
