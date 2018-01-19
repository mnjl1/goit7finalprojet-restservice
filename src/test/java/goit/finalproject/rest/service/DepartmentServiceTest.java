package goit.finalproject.rest.service;

import goit.finalproject.rest.Service.DepartmentService;
import goit.finalproject.rest.model.Department;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("testing") //application-testing.properties
public class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @Before
    public void beforeTest(){
        departmentService.deleteAll();
    }

    @Test
    public void saveTest(){
        Department department = departmentService.save(new Department("department1"));
        Department found = departmentService.findById(department.getId());

        assertEquals(department, found);
    }

    @Test
    public void findAllTest(){
        List<Department> departmentList = new ArrayList<>();

        departmentList.add(new Department("dep1"));
        departmentList.add(new Department("dep2"));
        departmentList.add(new Department("dep3"));

        for (Department d: departmentList){
            departmentService.save(d);
        }

        List<Department> found = departmentService.findAllDepartment();

        assertEquals(departmentList, found);
    }
}
