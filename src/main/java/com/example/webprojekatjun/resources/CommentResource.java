package com.example.webprojekatjun.resources;

import com.example.webprojekatjun.entities.Comment;
import com.example.webprojekatjun.services.CommentService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Path("/comments")
public class CommentResource {
    @Inject
    private CommentService commentService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() {
        List<Comment> comments = commentService.allComments();
        Collections.sort(comments, Comparator.comparing(Comment::getDate).reversed());
        return Response.ok(comments).build();
    }

    @GET
    @Path("/page/{page}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allWithPagination(@PathParam("page") Integer page) {
        List<Comment> comments = commentService.allComments();
        Collections.sort(comments, Comparator.comparing(Comment::getDate).reversed());
        return Response.ok(comments.subList((page-1)*3,Math.min(comments.size(), page*3))).build();
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
        List<Comment> comments = commentService.allCommentsForNews(vest_id);
        Collections.sort(comments, Comparator.comparing(Comment::getDate).reversed());
        return Response.ok(comments).build();
    }

    @GET
    @Path("/news/{id}/page/{page}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allCommentsForNewsWithPagination(@PathParam("page") Integer page, @PathParam("id") Integer id) {
        List<Comment> comments = commentService.allCommentsForNews(id);
        Collections.sort(comments, Comparator.comparing(Comment::getDate).reversed());
        return Response.ok(comments.subList((page-1)*3,Math.min(comments.size(), page*3))).build();
    }

}
