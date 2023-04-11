package com.guptaji.graphql.demo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class StudentGraphQl {

    @Id
    private int rollNo;
    private String name;
    private int standard;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Book> booksList;

    public StudentGraphQl() {
        // Default constructor
    }

    public StudentGraphQl(int rollNo, String name, int standard, List<Book> booksList) {
        this.rollNo = rollNo;
        this.name = name;
        this.standard = standard;
        this.booksList = booksList;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStandard() {
        return standard;
    }

    public void setStandard(int standard) {
        this.standard = standard;
    }

    public List<Book> getBooksList() {
        return booksList;
    }

    public void setBooksList(List<Book> booksList) {
        this.booksList = booksList;
    }

    @Override
    public String toString() {
        return "StudentGraphQl{" +
                "rollNo=" + rollNo +
                ", name='" + name + '\'' +
                ", standard=" + standard +
                ", booksList=" + booksList +
                '}';
    }
}
