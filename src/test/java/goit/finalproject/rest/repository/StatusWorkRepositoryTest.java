package goit.finalproject.rest.repository;


import goit.finalproject.rest.model.StatusWork;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("testing") //application-testing.properties
public class StatusWorkRepositoryTest {

    @Autowired
    private StatusWorkRepository statusWorkRepository;

    @Before
    public void before() {
        statusWorkRepository.deleteAll();
    }

    @Test
    public void saveTest() {
        StatusWork statusWork = new StatusWork("work");
        statusWorkRepository.save(statusWork);

        StatusWork found = statusWorkRepository.findByName("work");

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
            statusWorkRepository.save(s);
        }

        List<StatusWork> found = statusWorkRepository.findAll();
        assertEquals(statusWorkList, found);
    }

}
