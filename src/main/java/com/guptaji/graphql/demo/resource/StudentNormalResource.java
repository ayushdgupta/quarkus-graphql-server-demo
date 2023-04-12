package com.guptaji.graphql.demo.resource;

import com.guptaji.graphql.demo.entity.StudentGraphQl;
import com.guptaji.graphql.demo.repository.StudentRepo;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/studentNormal")
public class StudentNormalResource {

    @Inject
    public StudentRepo studentRepo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentDetails(){
        return Response.ok(studentRepo.listAll()).build();
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertStudent(StudentGraphQl student){
        studentRepo.persist(student);
        if (studentRepo.isPersistent(student)){
            return Response.ok("Done dana done done").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentDetailsById(@PathParam("id") int rollNo){
        return Response.ok(studentRepo.findById(rollNo)).build();
    }
}
