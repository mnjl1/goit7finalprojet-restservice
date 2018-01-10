package goit.finalproject.rest.Service;

import goit.finalproject.rest.model.StatusWork;

import java.util.List;

public interface StatusWorkService {

    StatusWork findByStatusWork(String status);
    StatusWork findById(Long id);
    List<StatusWork> findAllStatusWork();

    StatusWork save(StatusWork statusWork);
    void update(StatusWork statusWork);
    void delete(Long id);

}
