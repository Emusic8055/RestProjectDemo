package com.sample.test;

import com.sample.config.ApplicationConfig;
import com.sample.pojo.DepartmentPojo;
import com.sample.pojo.EmployeePojo;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * Created by schawla on 9/8/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationConfig.class)
@WebAppConfiguration
@IntegrationTest("server.port=8080")
public class EmployeeRestControllerTest {


    RestTemplate restTemplate = new TestRestTemplate();


    @Test
    public void testPostEndPointEmployee() {
        ResponseEntity<EmployeePojo> entity = restTemplate.postForEntity("http://localhost:8080/employeeService/employee",
                new EmployeePojo("test@mail.com","John", "Doe","46768778"), EmployeePojo.class);
        Assert.assertNotNull(entity);

        Assert.assertEquals("John",entity.getBody().getFirstName());

    }

    @Test
    public void testPostEndPointEmployeeWithDepartment() {
        EmployeePojo employeePojo = new EmployeePojo("test@mail.com","John", "Doe","46768778");
        employeePojo.setDepartmentPojo(new DepartmentPojo("1","Maths"));
        ResponseEntity<EmployeePojo> entity = restTemplate.postForEntity("http://localhost:8080/employeeService/employee",
                employeePojo, EmployeePojo.class);
        Assert.assertNotNull(entity);

        Assert.assertEquals("John",entity.getBody().getFirstName());
        Assert.assertEquals("1",entity.getBody().getDepartmentPojo().getId());

    }

    @Test
    public void testPutEndPointEmployeeWithDepartment() {
        EmployeePojo employeePojo = new EmployeePojo("test@mail.com","John", "Doe","46768778");
        employeePojo.setDepartmentPojo(new DepartmentPojo("1","Maths"));
        ResponseEntity<EmployeePojo> entity = restTemplate.postForEntity("http://localhost:8080/employeeService/employee",
                employeePojo, EmployeePojo.class);
        Assert.assertNotNull(entity);

        Assert.assertEquals("John",entity.getBody().getFirstName());
        Assert.assertEquals("1",entity.getBody().getDepartmentPojo().getId());

        //Now update the employee name and his department
        employeePojo.setFirstName("Jane");
        employeePojo.setDepartmentPojo(new DepartmentPojo("2", "Physics"));

        restTemplate.put("http://localhost:8080/employeeService/employee", employeePojo, EmployeePojo.class);
        ResponseEntity<EmployeePojo> entityUpdated = restTemplate.getForEntity("http://localhost:8080/employeeService/employee/1", EmployeePojo.class);
        Assert.assertNotNull(entityUpdated);

        Assert.assertEquals("Jane",entityUpdated.getBody().getFirstName());
        Assert.assertEquals("2",entityUpdated.getBody().getDepartmentPojo().getId());

    }

    @Test
    public void testGetEndPointDepartmentAfterSave() {

        DepartmentPojo departmentPojo = new DepartmentPojo();
        departmentPojo.setName("Maths");

        ResponseEntity<DepartmentPojo> entity = restTemplate.postForEntity("http://localhost:8080/employeeService/department",
                departmentPojo, DepartmentPojo.class);
        Assert.assertNotNull(entity);

        ResponseEntity<DepartmentPojo> entityGet = restTemplate.getForEntity("http://localhost:8080/employeeService/department/" +entity.getBody().getId(), DepartmentPojo.class);
        Assert.assertNotNull(entityGet);
        Assert.assertEquals("Maths",entityGet.getBody().getName());

    }

    @Test
    public void testPostEndPointDepartment() {

        DepartmentPojo departmentPojo = new DepartmentPojo();
        departmentPojo.setName("Physics");
        EmployeePojo employeePojo = new EmployeePojo("test@mail.com","John", "Doe","46768778");
        departmentPojo.setEmployeePojoList(Arrays.asList(employeePojo));
        ResponseEntity<DepartmentPojo> entity = restTemplate.postForEntity("http://localhost:8080/employeeService/department",
                departmentPojo, DepartmentPojo.class);
        Assert.assertNotNull(entity);

        Assert.assertEquals("Physics",entity.getBody().getName());
        Assert.assertEquals("John",entity.getBody().getEmployeePojoList().get(0).getFirstName());

    }


}
