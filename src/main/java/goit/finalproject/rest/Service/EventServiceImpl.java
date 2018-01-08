package goit.finalproject.rest.Service;

import goit.finalproject.rest.model.Event;
import goit.finalproject.rest.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    public EventRepository eventRepository;

    @Override
    public Event save(Event entity) {
        return eventRepository.save(entity);
    }

    @Override
    public Event getById(long id) {
        return eventRepository.findOne(id);
    }

    @Override
    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    @Override
    public void delete(long id) {
        eventRepository.delete(id);
    }
}
