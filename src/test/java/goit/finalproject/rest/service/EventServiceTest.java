package goit.finalproject.rest.service;

import goit.finalproject.rest.Service.EventService;
import goit.finalproject.rest.model.Event;
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
public class EventServiceTest {


    @Autowired
    private EventService eventService;

    @Before
    public void beforeTest(){
        eventService.deleteAll();
    }

    @Test
    public void saveTest(){
        Event event = eventService.save(new Event("workDay"));
        Event found = eventService.findById(event.getId());

        assertEquals(event, found);
    }

    @Test
    public void findAllTest(){
        eventService.save(new Event("workDay"));
        eventService.save(new Event("workDay2"));

        List<Event> found = eventService.findAllEvent();

        assertEquals(2, found.size());
    }
}
