package com.example.webprojekatjun.resources;

import com.example.webprojekatjun.entities.Comment;
import com.example.webprojekatjun.services.CommentService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/comments")
public class CommentResource {
    @Inject
    private CommentService commentService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() {
        return Response.ok(commentService.allComments()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findComment(@PathParam("id") Integer id) {
        return Response.ok(commentService.findComment(id)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid Comment comment) {
        return Response.ok(commentService.addComment(comment)).build();
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) {
        commentService.deleteComment(id);
    }

    @GET
    @Path("/news/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allCommentsForNews(@PathParam("id") Integer vest_id) {
        return Response.ok(commentService.allCommentsForNews(vest_id)).build();
    }

}
