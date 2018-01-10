package goit.finalproject.rest.repository;

import goit.finalproject.rest.model.StatusWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusWorkRepository extends JpaRepository<StatusWork, Long> {

    StatusWork findByName(String name);
}
