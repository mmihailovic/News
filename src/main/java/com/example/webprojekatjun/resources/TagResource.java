package com.example.webprojekatjun.resources;

import com.example.webprojekatjun.entities.Tag;
import com.example.webprojekatjun.services.TagService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tag")
public class TagResource {
    @Inject
    private TagService tagService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() {
        return Response.ok(tagService.allTags()).build();
    }

    @GET
    @Path("/{kljucna_rec}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findTag(@PathParam("kljucna_rec") String kljucna_rec) {
        return Response.ok(tagService.findTag(kljucna_rec)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid Tag tag) {
        return Response.ok(tagService.addTag(tag)).build();
    }

    @DELETE
    @Path("/{kljucna_rec}")
    public void delete(@PathParam("kljucna_rec") String kljucna_rec) {
        tagService.deleteTag(kljucna_rec);
    }
}
