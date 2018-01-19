package goit.finalproject.rest.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated employee ID")
    private Long id;

    @Column(name = "tabelid")
    @ApiModelProperty(notes = "Tabel ID of employee")
    private Long tabelID;

    @Column(name = "firstname")
    @ApiModelProperty(notes = "First name of employee")
    private String firstName;

    @Column(name = "lastname")
    @ApiModelProperty(notes = "Last name of employee")
    private String lastName;

    @Column(name="email")
    @ApiModelProperty(notes = "Email of employee")
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    @ApiModelProperty(notes = "Department of employee")
    private Department department;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "position_id", referencedColumnName = "id")
    @ApiModelProperty(notes = "Position of employee")
    private Position position;

    @Column(name="koefwork")
    @ApiModelProperty(notes = "Coefficient of work")
    private Float koefWork;

    @Column(name="koefill")
    @ApiModelProperty(notes = "Coefficient of ill")
    private Float koefIll;

    @Column(name="koefholiday")
    @ApiModelProperty(notes = "Coefficient of holiday")
    private Float koefHoliday;

    @Column(name="koefnotwork")
    @ApiModelProperty(notes = "Coefficient of not work")
    private Float koefNotWork;

    @OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, mappedBy = "employee")
    List<DateStatus> statuses;

    //1
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, mappedBy = "employees")
    List<DateEvent> events;

    //2
//    @ManyToMany(mappedBy = "employees")
//    List<DateEvent> events;

    //3
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinColumn(name = "event_id", referencedColumnName = "id")
//    List<DateEvent> events;

    public Employee() {
    }

    public Employee(Long tabelID, String firstName, String lastName, String email) {
        this.tabelID = tabelID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Employee(Long tabelID, String firstName, String lastName, String email,
                    Department department, Position position,
                    Float koefWork, Float koefIll, Float koefHoliday, Float koefNotWork) {
        this.tabelID = tabelID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.department = department;
        this.position = position;
        this.koefWork = koefWork;
        this.koefIll = koefIll;
        this.koefHoliday = koefHoliday;
        this.koefNotWork = koefNotWork;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTabelID() {
        return tabelID;
    }

    public void setTabelID(Long tabelID) {
        this.tabelID = tabelID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Float getKoefWork() {
        return koefWork;
    }

    public void setKoefWork(Float koefWork) {
        this.koefWork = koefWork;
    }

    public Float getKoefIll() {
        return koefIll;
    }

    public void setKoefIll(Float koefIll) {
        this.koefIll = koefIll;
    }

    public Float getKoefHoliday() {
        return koefHoliday;
    }

    public void setKoefHoliday(Float koefHoliday) {
        this.koefHoliday = koefHoliday;
    }

    public Float getKoefNotWork() {
        return koefNotWork;
    }

    public void setKoefNotWork(Float koefNotWork) {
        this.koefNotWork = koefNotWork;
    }

//    public List<DateStatus> getStatuses() {
//        return statuses;
//    }
//
//    public void setStatuses(List<DateStatus> statuses) {
//        this.statuses = statuses;
//    }
//
//    public List<DateEvent> getEvents() {
//        return events;
//    }
//
//    public void setEvents(List<DateEvent> events) {
//        this.events = events;
//    }


    @Override
    public String toString() {
        return "Employee{" +
                "tabelID=" + tabelID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", department=" + department +
                ", position=" + position +
                ", koefWork=" + koefWork +
                ", koefIll=" + koefIll +
                ", koefHoliday=" + koefHoliday +
                ", koefNotWork=" + koefNotWork +
                '}';
    }


}
