package com.sample.pojo;

import java.util.List;

/**
 * Created by schawla on 9/9/2016.
 */
public class DepartmentPojo {

    private String id;
    private String name;
    private List<EmployeePojo> employeePojoList;

    public DepartmentPojo() {
    }

    public DepartmentPojo(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EmployeePojo> getEmployeePojoList() {
        return employeePojoList;
    }

    public void setEmployeePojoList(List<EmployeePojo> employeePojoList) {
        this.employeePojoList = employeePojoList;
    }
}
