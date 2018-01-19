package goit.finalproject.rest.Service;


import goit.finalproject.rest.model.Event;
import goit.finalproject.rest.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService{
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(EventServiceImpl.class);

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event findById(Long id) {
        log.info("Find Event by ID {}", id);
        return eventRepository.findOne(id);
    }

    @Override
    public List<Event> findByType(String type) {
        log.info("Find Event by type {}", type);
        return eventRepository.findByType(type);
    }

    @Override
    public List<Event> findAllEvent() {
        List<Event> list = eventRepository.findAll();
        if(list!=null){
            log.info("Find all Events");
            return list;
        }
        return null;
    }

    @Override
    public Event save(Event event) {
        log.info("Save Event ID {}", event.getId());
        Event saveEvent =  eventRepository.save(event);
        return saveEvent;
    }

    @Override
    public void update(Event event) {
        Event event1 = eventRepository.findOne(event.getId());
        if(event1!=null){
            log.info("Update Event ID {}", event1.getId());
            eventRepository.delete(event.getId());
        }
        eventRepository.save(event);
    }

    @Override
    public void delete(Long id) {
        Event event1 = eventRepository.findOne(id);
        if(event1!=null){
            log.info("Delete Event ID {}", id);
            eventRepository.delete(id);
        }
    }

    @Override
    public void deleteAll() {
        log.info("Clear table events");
        eventRepository.deleteAll();
    }
}
