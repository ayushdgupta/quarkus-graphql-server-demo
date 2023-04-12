package com.guptaji.graphql.demo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class StudentGraphQl {

    @Id
    private int rollNo;
    private String name;
    private int standard;

    // Here i removed cascade = CascadeType.ALL bcz my target was to achieve only foreign key constraint i.e. if
    // a book exist in BOOK table / entity then only a student should be able to add it in his list of books so
    // when i was using Cascade property it was giving me an exception because when i was providing book data
    // which was already there then cascade property was trying to insert it again that was not possible.
    /*
        Sample input which i am passing from Swagger UI is
        {
          "rollNo": 4,
          "name": "Vinay",
          "standard": 14,
          "booksList": [
            {
              "id": 2
            }
          ]
        }

        But in this oneToMany case one student can have relation with many books e.g. below
        {
          "rollNo": 6,
          "name": "Pranjal",
          "standard": 11,
          "booksList": [
            {
              "id": 3
            },
            {
              "id": 5
            }
          ]
        }

        but one book can n't be associated with many people like if after executing above response, if we will
        give another response like below -
        {
          "rollNo": 7,
          "name": "Munna bhaiya",
          "standard": 1,
          "booksList": [
            {
              "id": 7
            },
            {
              "id": 3
            }
          ]
        }

        so in above case it'll fail because book with id 3 is already associated with Pranjal so it can n't
        allign with any other student (Book is not following oneToMany annotation bcz we have not given).
     */
    @OneToMany(fetch = FetchType.EAGER)
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
