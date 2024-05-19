package org.example.coursemanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@ToString

@Entity
public class CourseRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    User user;
    @ManyToOne
    Course course;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "schedule_id")
    Schedule schedule;

    int status;


    @ManyToOne
    CourseClass courseClass ;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @PrePersist
    protected void onCreate() {

        created = new Date();
    }
    public String getFormattedCreated() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return sdf.format(this.created);
    }


    public CourseRegister() {
    }

    public CourseRegister(Course course,
                          User user,
                          Schedule schedule,
                          int status) {
        this.course = course;
        this.user = user;
        this.schedule = schedule;
        this.status = status;
    }
}
