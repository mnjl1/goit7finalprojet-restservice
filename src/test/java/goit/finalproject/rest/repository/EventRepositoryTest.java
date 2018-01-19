package goit.finalproject.rest.repository;

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
public class EventRepositoryTest {

    @Autowired
    private EventRepository eventRepository;

    @Before
    public void beforeTest(){
        eventRepository.deleteAll();
    }

    @Test
    public void saveTest(){
        Event event = eventRepository.save(new Event("workDay"));
        Event found = eventRepository.findOne(event.getId());

        assertEquals(event, found);
    }

    @Test
    public void findAllTest(){
        eventRepository.save(new Event("workDay"));
        eventRepository.save(new Event("workDay2"));

        List<Event> found = eventRepository.findAll();

        assertEquals(2, found.size());
    }
}
