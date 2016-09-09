package com.sample.rest;

import com.sample.dao.DepartmentDao;
import com.sample.dao.DepartmentJPA;
import com.sample.dao.EmployeeDao;
import com.sample.dao.EmployeeJPA;
import com.sample.pojo.DepartmentPojo;
import com.sample.pojo.EmployeePojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * REST endpoint for Employee and Department resource.
 */
@Component
@Path("/employeeService")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class EmployeeRestController {

    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private DepartmentDao departmentDao;

    protected final Log logger = LogFactory.getLog(getClass());

    @GET
    @Path("/employee/{id}")
    public EmployeePojo getEmployee(@PathParam("id") String id) throws Exception{
        logger.debug("Request to get the Employee");
        if(id != null && !id.isEmpty()) {
            EmployeeJPA employee = employeeDao.findOne(new Integer(id));
            return convertToPojo(employee);

        } else {
            throw new Exception(Response.Status.BAD_REQUEST.toString() + " id must not be null. ");
        }

    }

    @GET
    @Path("/employeeName/{name}")
    public EmployeePojo getEmployeeByName(@PathParam("name") String name) {
        EmployeeJPA employee = employeeDao.findByFirstName(name);
        return convertToPojo(employee);
    }


    @DELETE
    @Path("/employee/{id}")
    public Response removeEmployee(@PathParam("id") String id ) throws Exception{
        if(id == null) {
            throw new Exception(Response.Status.BAD_REQUEST.toString() + " id must not be null. ");
        }
        logger.debug("Request for DELETE......");
        employeeDao.delete(new Integer(id));
        return Response.status(200).entity("Deleted Employee").build();
    }

    @POST
    @Path("/employee")
    public EmployeePojo saveEmployee(EmployeePojo employeePojo ) throws Exception{
        logger.debug("Request for POST......");
        EmployeeJPA employeeJPA = createJPA(employeePojo);
        try {
            employeeJPA = employeeDao.save(employeeJPA);
        } catch (Exception e) {
            throw new Exception(Response.Status.INTERNAL_SERVER_ERROR.toString() + e.getMessage());
        }
        return convertToPojo(employeeJPA);

    }

    @PUT
    @Path("/employee")
    public EmployeePojo updateEmployee(EmployeePojo employeePojo) throws Exception{
        logger.debug("Request to update the Employee");
        if(employeePojo.getId() == null) {
            throw new Exception(Response.Status.BAD_REQUEST.toString() + " id must not be null. ");
        }
        EmployeeJPA employeeJPA = createJPA(employeePojo);
        try {
            employeeJPA = employeeDao.save(employeeJPA);
        } catch (Exception e) {
            throw new Exception(Response.Status.INTERNAL_SERVER_ERROR.toString() + e.getMessage());
        }
        return convertToPojo(employeeJPA);
    }

    /**
     * Rest calls for Department
     *
     */

    @GET
    @Path("/department/{id}")
    public DepartmentPojo getDepartment(@PathParam("id") String id) throws Exception{
        logger.debug("Request to get the Department");
        if(id != null && !id.isEmpty()) {
            DepartmentJPA departmentJPA = departmentDao.findOne(new Integer(id));
            return convertToDepartmentPojo(departmentJPA);

        } else {
            throw new Exception(Response.Status.BAD_REQUEST.toString() + " id must not be null. ");
        }

    }



    @DELETE
    @Path("/department/{id}")
    public Response removeDepartment(@PathParam("id") String id ) throws Exception{
        if(id == null) {
            throw new Exception(Response.Status.BAD_REQUEST.toString() + " id must not be null. ");
        }
        logger.debug("Request for DELETE......");
        departmentDao.delete(new Integer(id));
        return Response.status(200).entity("Deleted Department").build();
    }

    @POST
    @Path("/department")
    public DepartmentPojo saveDepartment(DepartmentPojo departmentPojo ) throws Exception{
        logger.debug("Request for POST......");
        DepartmentJPA departmentJPA = createDepartmentJPA(departmentPojo);
        try {
            departmentJPA = departmentDao.save(departmentJPA);
        } catch (Exception e) {
            throw new Exception(Response.Status.INTERNAL_SERVER_ERROR.toString() + e.getMessage());
        }
        return convertToDepartmentPojo(departmentJPA);

    }



    private EmployeePojo convertToPojo(EmployeeJPA employee) {
        EmployeePojo employeePojo =  new EmployeePojo(employee.getId().toString(),employee.getEmailAddress()
                , employee.getFirstName(), employee.getLastName(),employee.getPhone());
        DepartmentJPA department = employee.getDepartmentJPA();
        if(department != null ) {
            DepartmentPojo departmentPojo = new DepartmentPojo();
            departmentPojo.setId(department.getId().toString());
            departmentPojo.setName(department.getName());
            employeePojo.setDepartmentPojo(departmentPojo);
        }
        return employeePojo;
    }

    private EmployeeJPA createJPA (EmployeePojo employeePojo) {
        EmployeeJPA employeeJPA = new EmployeeJPA();
        if(employeePojo.getId() != null) { //For Update
            employeeJPA.setId(Integer.parseInt(employeePojo.getId()));
        }
        employeeJPA.setEmailAddress(employeePojo.getEmailAddress());
        employeeJPA.setFirstName(employeePojo.getFirstName());
        employeeJPA.setLastName(employeePojo.getLastName());
        employeeJPA.setPhone(employeePojo.getPhone());
        DepartmentPojo departmentPojo = employeePojo.getDepartmentPojo();
        if(departmentPojo != null) {
            DepartmentJPA departmentJPA = new DepartmentJPA();
            if(departmentPojo.getId() != null) { //For Updating the employee to a different department
                departmentJPA.setId(Integer.parseInt(departmentPojo.getId()));
                departmentJPA.setName(departmentPojo.getName());
            }
            employeeJPA.setDepartmentJPA(departmentJPA);
        }

       return employeeJPA;
    }

    private DepartmentPojo convertToDepartmentPojo(DepartmentJPA departmentJPA ) {
        DepartmentPojo department = new DepartmentPojo(departmentJPA.getId().toString(), departmentJPA.getName());
        List<EmployeePojo> employeePojos =  new ArrayList<EmployeePojo>();
        if(departmentJPA.getEmployees() != null && !departmentJPA.getEmployees().isEmpty()) {
            for(EmployeeJPA employeeJPA :departmentJPA.getEmployees()) {
                employeePojos.add(convertToPojo(employeeJPA));
            }

            department.setEmployeePojoList(employeePojos);

        }
        return department;
    }

    private DepartmentJPA createDepartmentJPA(DepartmentPojo departmentPojo) {
        DepartmentJPA department = new DepartmentJPA();
        if(departmentPojo.getId() != null) { //For Update
            department.setId(Integer.parseInt(departmentPojo.getId()));
        }
        department.setName(departmentPojo.getName());
        List<EmployeePojo> employeePojoList = departmentPojo.getEmployeePojoList();
        List<EmployeeJPA> employeeJPAs = new ArrayList<EmployeeJPA>();
        if(employeePojoList != null && !employeePojoList.isEmpty()) {
            for(EmployeePojo employeePojo : employeePojoList) {
                employeeJPAs.add(createJPA(employeePojo));
            }
        }
        department.setEmployees(employeeJPAs);
        return department;
    }
}
