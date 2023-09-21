package com.example.webprojekatjun.resources;

import com.example.webprojekatjun.entities.Category;
import com.example.webprojekatjun.requests.CategoryUpdateRequest;
import com.example.webprojekatjun.services.CategoryService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/category")
public class CategoryResource {
    @Inject
    private CategoryService categoryService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() {
        return Response.ok(categoryService.allCategories()).build();
    }

    @GET
    @Path("/page/{page}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allWithPagination(@PathParam("page") Integer page) {
        return Response.ok(categoryService.allCategoriesWithPagination(page)).build();
    }

    @GET
    @Path("/{ime}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findCategory(@PathParam("ime") String ime) {
        return Response.ok(categoryService.findCategory(ime)).build();
    }

    @POST
    @Path("/{ime}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCategory(@PathParam("ime") String ime, @Valid CategoryUpdateRequest categoryUpdateRequest) {
        return Response.ok(categoryService.updateCategory(ime,categoryUpdateRequest.getNovoIme(),categoryUpdateRequest.getOpis())).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid Category category) {
        Category c = categoryService.addCategory(category);
        if(c == null)
            return Response.status(422,"Ta kategorija vec postoji!").build();
        return Response.ok(c).build();
    }

    @DELETE
    @Path("/{ime}")
    public void delete(@PathParam("ime") String ime) {
        categoryService.deleteCategory(ime);
    }
}
