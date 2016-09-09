package com.sample.pojo;

/**
 * POJO to be used by the REST controller
 */
public class EmployeePojo {

    private String id;
    private String emailAddress;
    private String firstName;
    private String lastName;
    private String phone;
    private DepartmentPojo departmentPojo;

    public EmployeePojo(String id, String emailAddress, String firstName, String lastName, String phone) {
        this.id = id;
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public EmployeePojo(String emailAddress, String firstName, String lastName, String phone) {
        this.id = null;
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public EmployeePojo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public DepartmentPojo getDepartmentPojo() {
        return departmentPojo;
    }

    public void setDepartmentPojo(DepartmentPojo departmentPojo) {
        this.departmentPojo = departmentPojo;
    }
}
