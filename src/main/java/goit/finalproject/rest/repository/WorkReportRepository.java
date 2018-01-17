package goit.finalproject.rest.repository;

import goit.finalproject.rest.model.WorkReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkReportRepository extends JpaRepository<WorkReport, Long> {

    List<WorkReport> findByTabelIDAndBeginPeriodAndEndPeriod(Long tabelID, String beginPeriod, String endPeriod);
    
    List<WorkReport> findByTabelID(Long tabelID);
}
