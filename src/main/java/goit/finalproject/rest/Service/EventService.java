package goit.finalproject.rest.Service;


import goit.finalproject.rest.model.Event;

import java.util.List;

public interface EventService {

    Event findById(Long id);
    List<Event> findByType(String type);
    List<Event> findAllEvent();

    Event save(Event event);
    void update(Event event);
    void delete(Long id);
}
