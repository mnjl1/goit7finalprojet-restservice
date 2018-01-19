package goit.finalproject.rest.service;

import goit.finalproject.rest.Service.DateEventService;
import goit.finalproject.rest.Service.EventService;
import goit.finalproject.rest.model.DateEvent;
import goit.finalproject.rest.model.Event;
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
public class DateEventServiceTest {

    @Autowired
    private DateEventService dateEventService;

    @Autowired
    private EventService eventService;

    @Before
    public void beforeTest(){
        dateEventService.deleteAll();
    }

    @Test
    public void saveTest(){
        Event event = eventService.save(new Event("workDay"));
        DateEvent dateEvent = dateEventService.save(new DateEvent("18 01 2018", event));

        DateEvent found = dateEventService.findById(dateEvent.getId());
        assertEquals(dateEvent.getEvent(), found.getEvent());
    }

    @Test
    public void findAllTest(){
        Event event = eventService.save(new Event("workDay"));
        List<DateEvent> dateEvent = new ArrayList<>();
        dateEvent.add(dateEventService.save(new DateEvent("18 01 2018", event)));
        dateEvent.add(dateEventService.save(new DateEvent("19 01 2018", event)));

        List<DateEvent> found = dateEventService.findAllEvents();
        assertEquals(dateEvent.size(), found.size());
    }

}
