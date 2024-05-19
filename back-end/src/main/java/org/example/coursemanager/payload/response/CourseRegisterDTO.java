package org.example.coursemanager.payload.response;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.coursemanager.model.Course;
import org.example.coursemanager.model.Schedule;
import org.example.coursemanager.model.User;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseRegisterDTO {
    private Long id;
    private User user;
    private Course course;
    private Schedule schedule;
    private int status;
    private Date created;
    public String getFormattedCreated() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return sdf.format(this.created);
    }
}
