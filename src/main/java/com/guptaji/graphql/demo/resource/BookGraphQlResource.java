package com.guptaji.graphql.demo.resource;

import com.guptaji.graphql.demo.entity.Book;
import com.guptaji.graphql.demo.entity.StudentGraphQl;
import com.guptaji.graphql.demo.repository.BookRepo;
import com.guptaji.graphql.demo.repository.StudentRepo;
import org.eclipse.microprofile.graphql.*;

import javax.inject.Inject;
import java.util.List;

// below annotation will indicate that this endpoint will be an graphql endpoint.
@GraphQLApi
public class BookGraphQlResource {

    @Inject
    public BookRepo bookRepo;

    @Inject
    public StudentRepo studentRepo;

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
        System.out.println("getBookDetailsById Hit");
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

    // below method will change the graphql schema so that we can fetch the book details on behalf of some ID
    // that we can achieved by ABOVE API (getBookDetailsById) along with the student data who are associated with
    // that book. If we comment below method then the below query will throw an error.
    /*
            Graphql query after applying below method is
            query getBookAndStudentDetails{
              bookDetailsById(bookId: 3){
                    id
                    name
                    impChapters
                        fetchBookAndStudentDetailsById{
                      rollNo
                      name
                      standard
                      booksList {
                        id
                      }
                    }
                   }
            }

            Below Query will also work
            query getBookAndStudentDetails{
              allBookDetails{
                                    id
                                    name
                                    impChapters
                        fetchBookAndStudentDetailsById{
                      rollNo
                      name
                      standard
                      booksList {
                        id
                      }
                    }
                   }
            }
     */
    // @Source -- Enable List<StudentGraphQl> data to be added to queries that respond with Book
    public List<StudentGraphQl> fetchBookAndStudentDetailsById(@Source Book book){
        System.out.println("fetchBookAndStudentDetailsById Hit");
        return studentRepo.list("SELECT DISTINCT s FROM StudentGraphQl s JOIN s.booksList b WHERE b.id = ?1", book.getId());
    }
}
