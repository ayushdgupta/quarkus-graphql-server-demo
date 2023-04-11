package com.guptaji.graphql.demo.repository;

import com.guptaji.graphql.demo.entity.Book;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BookRepo implements PanacheRepositoryBase<Book, Integer> {
}
