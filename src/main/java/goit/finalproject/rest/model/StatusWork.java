package goit.finalproject.rest.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="statuses")
public class StatusWork implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated status ID")
    private Long id;

    @Column(name="status")
    @ApiModelProperty(notes = "The status name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "statusWork")
    @ApiModelProperty(notes = "List status with date and this statusWork")
    private List<DateStatus> dateStatuses;

    public StatusWork() {
    }

    public StatusWork(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  name;
    }
}
