package goit.finalproject.rest.repository;

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
public class DateEventRepositoryTest {

    @Autowired
    private DateEventRepository dateEventRepository;

    @Autowired
    private EventRepository eventRepository;

    @Before
    public void beforeTest(){
        dateEventRepository.deleteAll();
    }

    @Test
    public void saveTest(){
        Event event = eventRepository.save(new Event("workDay"));
        DateEvent dateEvent = dateEventRepository.save(new DateEvent("18 01 2018", event));

        DateEvent found = dateEventRepository.findOne(dateEvent.getId());
        assertEquals(dateEvent.getEvent(), found.getEvent());
    }

    @Test
    public void findAllTest(){
        Event event = eventRepository.save(new Event("workDay"));
        List<DateEvent> dateEvent = new ArrayList<>();
        dateEvent.add(dateEventRepository.save(new DateEvent("18 01 2018", event)));
        dateEvent.add(dateEventRepository.save(new DateEvent("19 01 2018", event)));

        List<DateEvent> found = dateEventRepository.findAll();
        assertEquals(dateEvent.size(), found.size());
    }

}
