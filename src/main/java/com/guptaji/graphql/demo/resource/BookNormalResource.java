package com.guptaji.graphql.demo.resource;

import com.guptaji.graphql.demo.dto.BookAndStudentCombined;
import com.guptaji.graphql.demo.entity.Book;
import com.guptaji.graphql.demo.entity.StudentGraphQl;
import com.guptaji.graphql.demo.repository.BookRepo;
import com.guptaji.graphql.demo.repository.StudentRepo;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/bookNormal")
public class BookNormalResource {

    @Inject
    public BookRepo bookRepo;

    @Inject
    public StudentRepo studentRepo;

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

    // For below API we will pass one ID so our requirement is for the input ID we need to fetch the book details
    // and the corresponding student details who are using that book.
    @GET
    @Path("/bookAndStudentDetails/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBookAndStudentDetailsById(@PathParam("id") int id){
        // below method does n't work
//        List<StudentGraphQl> studentGraphQlList = studentRepo.list("booksList.id = ?1", id);
//        BookAndStudentCombined bookAndStudentCombined = new BookAndStudentCombined(bookRepo.findById(id), studentGraphQlList);

        //
        Book book = bookRepo.findById(id);
        // below is also not working
//        List<StudentGraphQl> studentGraphQlList = studentRepo.list("?1 in booksList", book);

        // below solution works
        /*
            How Hibernate is creating this query
            Hibernate:
            select
                distinct studentgra0_.rollNo as rollno1_3_,
                studentgra0_.name as name2_3_,
                studentgra0_.standard as standard3_3_
            from
                StudentGraphQl studentgra0_
            inner join
                StudentGraphQl_Book bookslist1_                     -- StudentGraphQl_Book table is our b
                    on studentgra0_.rollNo=bookslist1_.StudentGraphQl_rollNo
            inner join
                Book book2_
                    on bookslist1_.booksList_id=book2_.id
            where
                book2_.id=?
         */
        List<StudentGraphQl> studentGraphQlList = studentRepo.list("SELECT DISTINCT s FROM StudentGraphQl s JOIN s.booksList b WHERE b.id = ?1", id);
        BookAndStudentCombined bookAndStudentCombined = new BookAndStudentCombined(book, studentGraphQlList);
        return Response.ok(bookAndStudentCombined).build();
    }

}
