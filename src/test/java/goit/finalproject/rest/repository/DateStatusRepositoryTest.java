package goit.finalproject.rest.repository;

import goit.finalproject.rest.model.*;
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
public class DateStatusRepositoryTest {

    @Autowired
    private DateStatusRepository dateStatusRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private StatusWorkRepository statusWorkRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Before
    public void beforeTest(){
        dateStatusRepository.deleteAll();
    }

    @Test
    public void saveTest(){
        Department department = departmentRepository.save(new Department("dep1"));
        Position position = positionRepository.save(new Position("sheff", 100f));

        Employee employee = employeeRepository.save(new Employee(1113L, "1", "2", "3",
                department, position,1f,1f,1f,0f));
        StatusWork statusWork = statusWorkRepository.save(new StatusWork("work"));

        DateStatus dateStatus = new DateStatus(employee, "18 01 2018", statusWork);
        dateStatusRepository.save(dateStatus);
        DateStatus found = dateStatusRepository.findOne(dateStatus.getId());
        assertEquals(dateStatus.getDate(), found.getDate());
    }

    @Test
    public void findAllTest(){
        Department department = departmentRepository.save(new Department("dep1"));
        Position position = positionRepository.save(new Position("sheff", 100f));

        Employee employee = employeeRepository.save(new Employee(1113L, "1", "2", "3",
                department, position,1f,1f,1f,0f));

        StatusWork statusWork = statusWorkRepository.save(new StatusWork("work"));

        DateStatus dateStatus = new DateStatus(employee, "18 01 2018", statusWork);
        DateStatus dateStatus2 = new DateStatus(employee, "19 01 2018", statusWork);

        dateStatusRepository.save(dateStatus);
        dateStatusRepository.save(dateStatus2);

        List<DateStatus> found = dateStatusRepository.findByEmployee(employee);
        assertEquals(dateStatus.getDate(), found.get(0).getDate());
    }

}
