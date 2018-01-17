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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("testing")
public class StatusWorkServiceTest {

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
}
