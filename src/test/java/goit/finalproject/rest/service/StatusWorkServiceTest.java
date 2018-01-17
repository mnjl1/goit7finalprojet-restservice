package goit.finalproject.rest.service;


import goit.finalproject.rest.Application;
import goit.finalproject.rest.Service.StatusWorkService;
import goit.finalproject.rest.Service.StatusWorkServiceImpl;
import goit.finalproject.rest.model.StatusWork;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
//@DataJpaTest
@SpringBootTest(classes = Application.class)
//@WebAppConfiguration
//@SpringBootTest(
//        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
//        classes = Application.class)
////@AutoConfigureMockMvc
//@TestPropertySource(
//        locations = "classpath:resources/application-integrationtest.properties")
public class StatusWorkServiceTest {

    @Autowired
    private StatusWorkService statusWorkService;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void saveTest() {
        StatusWork statusWork = new StatusWork("work");
        testEntityManager.persist(statusWork);
        testEntityManager.flush();

        StatusWork found = statusWorkService.findByStatusWork("work");

        assertThat(found.getName())
                .isEqualTo(statusWork.getName());

//        assertNotNull(statusWorkService.save(new StatusWork("work")));
    }

}
