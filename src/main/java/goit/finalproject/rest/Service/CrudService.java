package goit.finalproject.rest.Service;

import java.util.List;

public interface CrudService<E> {

    E save(E entity);

    E getById(long id);

    List<E> getAll();

    void delete(long id);
}
