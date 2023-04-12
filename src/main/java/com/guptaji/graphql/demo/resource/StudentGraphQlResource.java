package com.guptaji.graphql.demo.resource;

import com.guptaji.graphql.demo.entity.Book;
import com.guptaji.graphql.demo.entity.StudentGraphQl;
import com.guptaji.graphql.demo.repository.StudentRepo;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

import javax.inject.Inject;
import java.util.List;

@GraphQLApi
public class StudentGraphQlResource {

    @Inject
    public StudentRepo studentRepo;

    /*
        GraphQl Query for below API
        query allStudentDetails{
            allStudentDetails{
                rollNo
                name
                standard
                booksList {
                  id
                  name
                  impChapters
                }
              }
        }
     */
    @Query("allStudentDetails")
    @Description("Get all the student details by hitting this graphql API")
    public List<StudentGraphQl> getEntireStudentDetailsInCollege(){
        return studentRepo.listAll();
    }

    /*
            Graphql API for below API
            query getStudentDetailsById{
                studentDetailsById(studentRollNo : 4){
                rollNo
                name
                standard
                booksList {
                  id
                  name
                  impChapters
                }
              }
            }
     */
    @Query
    @Description("Get the student details on behalf of student roll no")
    public StudentGraphQl getStudentDetailsById(@Name("studentRollNo") int rollNo){
        return studentRepo.findById(rollNo);
    }
}
