package com.guptaji.graphql.demo.entity;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Book {

    @Id
    private int id;
    private String name;

    @ElementCollection
    private List<Integer> impChapters;

    public Book() {
        // default constructor
    }

    public Book(int id, String name, List<Integer> impChapters) {
        this.id = id;
        this.name = name;
        this.impChapters = impChapters;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getImpChapters() {
        return impChapters;
    }

    public void setImpChapters(List<Integer> impChapters) {
        this.impChapters = impChapters;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", impChapters=" + impChapters +
                '}';
    }
}
