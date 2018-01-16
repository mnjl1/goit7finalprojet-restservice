package goit.finalproject.rest.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="workreports")
public class WorkReport implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated report ID")
    private Long id;

    @Column(name="employee")
    @ApiModelProperty(notes = "Tabel ID of employee which report")
    private Long tabelID;

    @Column(name="beginperiod")
    @ApiModelProperty(notes = "Begin date of reports period")
    private String beginPeriod;

    @Column(name="endperiod")
    @ApiModelProperty(notes = "End date of reports period")
    private String endPeriod;

    @Column(name="countday")
    @ApiModelProperty(notes = "Count days of reports period")
    private Integer countDay;

    @Column(name="salaryperiod")
    @ApiModelProperty(notes = "Employees salary of reports period")
    private Float salaryPeriod;

    public WorkReport() {
    }

    public WorkReport(Long tabelID, String beginPeriod, String endPeriod) {
        this.tabelID = tabelID;
        this.beginPeriod = beginPeriod;
        this.endPeriod = endPeriod;
    }

    //    public WorkReport(Employee employee, String beginPeriod, String endPeriod) {
//        this.employee = employee;
//        this.beginPeriod = beginPeriod;
//        this.endPeriod = endPeriod;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Employee getEmployee() {
//        return employee;
//    }
//
//    public void setEmployee(Employee employee) {
//        this.employee = employee;
//    }


    public Long getTabelID() {
        return tabelID;
    }

    public void setTabelID(Long tabelID) {
        this.tabelID = tabelID;
    }

    public String getBeginPeriod() {
        return beginPeriod;
    }

    public void setBeginPeriod(String beginPeriod) {
        this.beginPeriod = beginPeriod;
    }

    public String getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(String endPeriod) {
        this.endPeriod = endPeriod;
    }

    public Integer getCountDay() {
        return countDay;
    }

    public void setCountDay(Integer countDay) {
        this.countDay = countDay;
    }

    public Float getSalaryPeriod() {
        return salaryPeriod;
    }

    public void setSalaryPeriod(Float salaryPeriod) {
        this.salaryPeriod = salaryPeriod;
    }

    @Override
    public String toString() {
        return "WorkReport{" +
                "id=" + id +
                ", tabelID=" + tabelID +
                ", beginPeriod='" + beginPeriod + '\'' +
                ", endPeriod='" + endPeriod + '\'' +
                ", countDay=" + countDay +
                ", salaryPeriod=" + salaryPeriod +
                '}';
    }
}
