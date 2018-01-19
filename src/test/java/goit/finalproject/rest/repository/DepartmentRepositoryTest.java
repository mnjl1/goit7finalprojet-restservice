package goit.finalproject.rest.repository;

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
public class DepartmentRepositoryTest {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Before
    public void beforeTest(){
        departmentRepository.deleteAll();
    }

    @Test
    public void saveTest(){
        Department department = departmentRepository.save(new Department("department1"));
        Department found = departmentRepository.findOne(department.getId());

        assertEquals(department, found);
    }

    @Test
    public void findAllTest(){
        List<Department> departmentList = new ArrayList<>();

        departmentList.add(new Department("dep1"));
        departmentList.add(new Department("dep2"));
        departmentList.add(new Department("dep3"));

        for (Department d: departmentList){
            departmentRepository.save(d);
        }

        List<Department> found = departmentRepository.findAll();

        assertEquals(departmentList, found);
    }
}
