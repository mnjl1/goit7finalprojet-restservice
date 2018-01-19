package goit.finalproject.rest.service;

import goit.finalproject.rest.Service.DateStatusService;
import goit.finalproject.rest.Service.DepartmentService;
import goit.finalproject.rest.Service.EmployeeService;
import goit.finalproject.rest.Service.StatusWorkService;
import goit.finalproject.rest.Service.PositionService;
import goit.finalproject.rest.model.*;
import goit.finalproject.rest.repository.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("testing") //application-testing.properties
public class DateStatusServiceTest {

    @Autowired
    private DateStatusService dateStatusService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private StatusWorkService statusWorkService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PositionService positionService;

    @Before
    public void beforeTest(){
        dateStatusService.deleteAll();
    }

    @Test
    public void saveTest(){
        Department department = departmentService.save(new Department("dep1"));
        Position position = positionService.save(new Position("sheff", 100f));

        Employee employee = employeeService.save(new Employee(1113L, "1", "2", "3",
                department, position,1f,1f,1f,0f));
        StatusWork statusWork = statusWorkService.save(new StatusWork("work"));

        DateStatus dateStatus = new DateStatus(employee, "18 01 2018", statusWork);
        dateStatusService.save(dateStatus);
        DateStatus found = dateStatusService.findById(dateStatus.getId());
        assertEquals(dateStatus.getDate(), found.getDate());
    }

    @Test
    public void findAllTest(){
        Department department = departmentService.save(new Department("dep1"));
        Position position = positionService.save(new Position("sheff", 100f));

        Employee employee = employeeService.save(new Employee(1113L, "1", "2", "3",
                department, position,1f,1f,1f,0f));

        StatusWork statusWork = statusWorkService.save(new StatusWork("work"));

        DateStatus dateStatus = new DateStatus(employee, "18 01 2018", statusWork);
        DateStatus dateStatus2 = new DateStatus(employee, "19 01 2018", statusWork);

        dateStatusService.save(dateStatus);
        dateStatusService.save(dateStatus2);

        List<DateStatus> found = dateStatusService.findByEmployee(employee);
        assertEquals(dateStatus.getDate(), found.get(0).getDate());
    }
}
