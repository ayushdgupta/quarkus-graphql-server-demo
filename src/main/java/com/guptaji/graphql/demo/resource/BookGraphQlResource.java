package com.guptaji.graphql.demo.resource;

import com.guptaji.graphql.demo.entity.Book;
import com.guptaji.graphql.demo.repository.BookRepo;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

import javax.inject.Inject;
import java.util.List;

// below annotation will indicate that this endpoint will be an graphql endpoint.
@GraphQLApi
public class BookGraphQlResource {

    @Inject
    public BookRepo bookRepo;

    // @Query annotation indicates that we can hit this API or method with 'allBookDetails' name like we define
    // @Path in get or post mapping.
    /*
    UI to fire the query - http://localhost:8086/q/graphql-ui
    Graph ql query for the below API
                    query allBookDetails{
                      allBookDetails{
                        id
                        name
                        impChapters
                      }
                    }
     */
    @Query("allBookDetails")
    @Description("Get all the book details by hitting this graphql API")
    public List<Book> getEntireBookDetails(){
        return bookRepo.listAll();
    }

    // getBookById API
    // Here if you see we've not provided any value with @Query annotation therefore the name of the query now is
    // the name of the method w/o get.
    // @Name will act as a pathParameter.
    /*
    GraphQl Query for the below API is
    query getBookDetailsById{
     bookDetailsById(bookId: 4){
        id
        name
        impChapters
       }
    }
     */
    @Query
    @Description("Get the book details on behalf of Book ID")
    public Book getBookDetailsById(@Name("bookId") int id){
        return bookRepo.findById(id);
    }

    /*
        But suppose if our client want the Book Details of id 1 and 2 then in REST API calls he need to hit
        the API twice with id 1 and 2 but with graphql this can be done within 1 request only be firing below
        graphql query -
        query getBookDetailsById{
          Book0 : bookDetailsById(bookId: 1){
            id
            name
            impChapters
          }
          Book1 : bookDetailsById(bookId: 2){
            id
            name
            impChapters
          }
        }
     */
}
