package com.sample.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * The Spring JPA repository to perform the CRUD for the Employee entity.
 */
@Repository
public interface EmployeeDao extends CrudRepository<EmployeeJPA,Integer> {

    EmployeeJPA findOne(Integer id);
    EmployeeJPA findByFirstName(String firstName);


}
