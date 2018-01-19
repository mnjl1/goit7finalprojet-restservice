package goit.finalproject.rest.service;

import goit.finalproject.rest.Service.DepartmentService;
import goit.finalproject.rest.Service.EmployeeService;
import goit.finalproject.rest.Service.PositionService;
import goit.finalproject.rest.model.Department;
import goit.finalproject.rest.model.Employee;
import goit.finalproject.rest.model.Position;
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
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PositionService positionService;

    @Before
    public void beforeTest(){
        employeeService.deleteAll();
    }

    @Test
    public void saveTest(){
        Department department = departmentService.save(new Department("dep1"));
        Position position = positionService.save(new Position("sheff", 100f));
        Employee employee = new Employee(1112L, "first name", "last name", "email",
                department, position, 1f,1f,1f,0f);

        Employee saveE = employeeService.save(employee);
        //Employee found = employeeService.findByTabelID(1112L);
        assertEquals(employee, saveE);
    }

    @Test
    public void findAllTest(){
        Department department = departmentService.save(new Department("dep1"));
        positionService.save(new Position("sheff", 100f));
        positionService.save(new Position("mentor", 50f));
        List<Position> positions = positionService.findAllPosition();

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1112L, "first name", "last name", "email",
                department, positions.get(0), 1f,1f,1f,0f));
        employees.add(new Employee(1122L, "first name2", "last name2", "email2",
                department, positions.get(1), 1f,1f,1f,0f));

        for (Employee e: employees){
            employeeService.save(e);
        }

        List<Employee> found = employeeService.getAll();

        assertEquals(employees.get(0).getLastName(), found.get(0).getLastName());
    }
}
