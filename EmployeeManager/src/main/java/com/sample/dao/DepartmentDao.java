package com.sample.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * The Spring JPA repository to perform the CRUD for the Department entity.
 */

@Repository
public interface DepartmentDao extends CrudRepository<DepartmentJPA,Integer> {
}
