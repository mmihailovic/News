package com.example.webprojekatjun.filters;

import com.example.webprojekatjun.services.UserService;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class AuthFilter implements ContainerRequestFilter {
    @Inject
    private UserService userService;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if (requestContext.getMethod().equals("OPTIONS")) {
            requestContext.getHeaders().add("Access-Control-Allow-Origin", "*");
            requestContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
            requestContext.getHeaders().add("Access-Control-Allow-Headers", "Content-Type, Authorization");
            requestContext.abortWith(Response.status(Response.Status.OK).build());
            return;
        }
        requestContext.getHeaders().add("Access-Control-Allow-Origin", "*");
        if (!this.isAuthRequired(requestContext)) {
            return;
        }

        try {
            String token = requestContext.getHeaderString("Authorization");
            if(token != null && token.startsWith("Bearer ")) {
                token = token.replace("Bearer ", "");
            }

            if (!this.userService.isAuthorized(token, requestContext)) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            }
        } catch (Exception exception) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    private boolean isAuthRequired(ContainerRequestContext req) {
        if (req.getUriInfo().getPath().contains("login")) {
            return false;
        }

        String path = req.getUriInfo().getPath();
        String method = req.getMethod();
        if(path.contains("/comments/news") || (path.endsWith("comments")  && method.equals("POST")) ||
                (path.contains("news/") && method.equals("GET")) || (path.contains("news/") && method.equals("PUT")) ||(path.contains("tag/news") && method.equals("GET"))
            || (path.endsWith("category")  && method.equals("GET")))
            return false;

        return true;
    }
}
