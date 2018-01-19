package goit.finalproject.rest.service;


import goit.finalproject.rest.Application;
import goit.finalproject.rest.Service.StatusWorkService;
import goit.finalproject.rest.Service.StatusWorkServiceImpl;
import goit.finalproject.rest.model.StatusWork;
import goit.finalproject.rest.repository.StatusWorkRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("testing") //application-testing.properties
public class StatusWorkServiceTest {

    @Autowired
    private StatusWorkService statusWorkService;

    @Before
    public void before() {
        statusWorkService.deleteAll();
    }

    @Test
    public void saveTest() {
        StatusWork statusWork = new StatusWork("work");
        statusWorkService.save(statusWork);

        StatusWork found = statusWorkService.findByStatusWork("work");

        assertEquals(statusWork, found);
    }

    @Test
    public void findAllTest(){
        List<StatusWork> statusWorkList = new ArrayList<>();
        statusWorkList.add(new StatusWork("work"));
        statusWorkList.add(new StatusWork("ill"));
        statusWorkList.add(new StatusWork("holiday"));
        statusWorkList.add(new StatusWork("not work"));

        for(StatusWork s: statusWorkList){
            statusWorkService.save(s);
        }

        List<StatusWork> found = statusWorkService.findAllStatusWork();
        assertEquals(statusWorkList, found);
    }
}
