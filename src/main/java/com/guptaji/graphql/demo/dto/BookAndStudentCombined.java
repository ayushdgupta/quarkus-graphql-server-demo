package com.guptaji.graphql.demo.dto;

import com.guptaji.graphql.demo.entity.Book;
import com.guptaji.graphql.demo.entity.StudentGraphQl;

import java.util.List;

/*
    We need this DTO in case of normal Rest Endpoint where we need to fetch the details of Book along with the
    students associated with it.
 */
public class BookAndStudentCombined {

    private Book book;

    // Here we will get only one student bcz book is not declare with @OneToMany annotaion but still we are
    // taking list.
    private List<StudentGraphQl> studentGraphQlList;

    public BookAndStudentCombined() {
        // Default constructor
    }

    public BookAndStudentCombined(Book book, List<StudentGraphQl> studentGraphQlList) {
        this.book = book;
        this.studentGraphQlList = studentGraphQlList;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<StudentGraphQl> getStudentGraphQlList() {
        return studentGraphQlList;
    }

    public void setStudentGraphQlList(List<StudentGraphQl> studentGraphQlList) {
        this.studentGraphQlList = studentGraphQlList;
    }

    @Override
    public String toString() {
        return "BookAndStudentCombined{" +
                "book=" + book +
                ", studentGraphQlList=" + studentGraphQlList +
                '}';
    }
}
