package com.example.webprojekatjun.resources;

import com.example.webprojekatjun.entities.Tag;
import com.example.webprojekatjun.services.TagService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findTag(@PathParam("id") Integer id) {
        return Response.ok(tagService.findTag(id)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid Tag tag) {
        return Response.ok(tagService.addTag(tag)).build();
    }

    @POST
    @Path("/news/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTags(@PathParam("id") Integer vest_id, List<Tag> tag) {
        return Response.ok(tagService.updateTags(vest_id, tag)).build();
    }

    @GET
    @Path("/news/{vest_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allTagsForNews(@PathParam("vest_id") Integer vest_id) {
        return Response.ok(tagService.allTagsForNews(vest_id)).build();
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) {
        tagService.deleteTag(id);
    }
}
