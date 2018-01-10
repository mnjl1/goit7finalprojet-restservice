package goit.finalproject.rest.service;


import goit.finalproject.rest.Service.StatusWorkService;
import goit.finalproject.rest.config.RepositoryConfiguration;
import goit.finalproject.rest.model.StatusWork;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RepositoryConfiguration.class})
public class StatusWorkServiceTest {

    @Autowired
    private StatusWorkService statusWorkService;


    @Test
    public void saveTest() {
        assertNotNull(statusWorkService.save(new StatusWork("work")));
    }

}
