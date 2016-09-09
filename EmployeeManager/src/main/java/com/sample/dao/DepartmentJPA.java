package com.sample.dao;

/**
 * JPA entity for Department. This is the parent entity in the relationship One to Many with Employee
 */

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;


@Entity
@Table(name = "Department")
public class DepartmentJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String name;

    public DepartmentJPA() {
    }

    public DepartmentJPA(String name) {
        this.name = name;
    }

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "departmentJPA",fetch = FetchType.EAGER)
    private List<EmployeeJPA> employees;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EmployeeJPA> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeJPA> employees) {
        this.employees = employees;
    }
}
