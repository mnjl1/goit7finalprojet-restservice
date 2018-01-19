package goit.finalproject.rest.service;

import goit.finalproject.rest.Service.InitDBService;
import goit.finalproject.rest.Service.StatusWorkService;
import goit.finalproject.rest.model.StatusWork;
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
public class InitDBServiceTest {

    @Autowired
    private InitDBService initDBService;

    @Autowired
    private StatusWorkService statusWorkService;

    @Test
    public void fullTest(){
        initDBService.full();
        List<StatusWork> statusWorkList = statusWorkService.findAllStatusWork();

        assertEquals(4, statusWorkList.size());
    }
}
