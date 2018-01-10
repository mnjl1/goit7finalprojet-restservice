package goit.finalproject.rest.Service;


import goit.finalproject.rest.model.Position;

import java.util.List;

public interface PositionService {

    Position findById(Long id);
    List<Position> findAllPosition();
    List<Position> findByName(String name);

    Position save(Position position);
    void update(Position position);

    void delete(Long id);

}
