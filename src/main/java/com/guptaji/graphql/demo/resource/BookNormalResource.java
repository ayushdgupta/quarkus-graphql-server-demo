package com.guptaji.graphql.demo.resource;

import com.guptaji.graphql.demo.entity.Book;
import com.guptaji.graphql.demo.repository.BookRepo;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/bookNormal")
public class BookNormalResource {

    @Inject
    public BookRepo bookRepo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBookDetails(){
        return Response.ok(bookRepo.listAll()).build();
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertBook(Book book){
        bookRepo.persist(book);
        if (bookRepo.isPersistent(book)){
            return Response.ok("Done dana done done").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBookDetailsById(@PathParam("id") int id){
        return Response.ok(bookRepo.findById(id)).build();
    }
}
