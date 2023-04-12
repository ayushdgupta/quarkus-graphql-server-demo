package com.guptaji.graphql.demo.repository;

import com.guptaji.graphql.demo.entity.StudentGraphQl;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StudentRepo implements PanacheRepositoryBase<StudentGraphQl, Integer> {
}
