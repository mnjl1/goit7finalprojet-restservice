package goit.finalproject.rest.Service;

import goit.finalproject.rest.model.Position;
import goit.finalproject.rest.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    public PositionRepository positionRepository;

    @Override
    public Position save(Position entity) {
        return positionRepository.save(entity) ;
    }

    @Override
    public Position getById(long id) {
        return positionRepository.findOne(id);
    }

    @Override
    public List<Position> getAll() {
        return positionRepository.findAll();
    }

    @Override
    public void delete(long id) {
        positionRepository.delete(id);
    }
}
