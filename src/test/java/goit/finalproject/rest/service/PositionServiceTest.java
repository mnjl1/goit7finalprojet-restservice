package goit.finalproject.rest.service;

import goit.finalproject.rest.Service.PositionService;
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
public class PositionServiceTest {

    @Autowired
    private PositionService positionService;

    @Before
    public void beforeTest(){
        positionService.deleteAll();
    }

    @Test
    public void saveTest(){
        Position position = new Position("sheff", 1000f);

        positionService.save(position);
        Position found = positionService.findById(position.getId());

        assertEquals(position, found);
    }

    @Test
    public void findAllTest(){
        List<Position> positionList = new ArrayList<>();
        Position p1 = new Position("director", 100f);
        positionList.add(p1);
        Position p2 = new Position("sheff", 70f);
        positionList.add(p2);
        Position p3 = new Position("sub-sheff", 70f);
        positionList.add(p3);
        Position p4 = new Position("master", 50f);
        positionList.add(p4);
        Position p5 = new Position("developer Java", 20f);
        positionList.add(p5);
        Position p6 = new Position("developer C#", 20f);
        positionList.add(p6);

        for (Position p: positionList){
            positionService.save(p);
        }

        List<Position> found = positionService.findAllPosition();

        assertEquals(positionList, found);
    }


}
