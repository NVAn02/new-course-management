package org.example.coursemanager.config;

import org.example.coursemanager.model.CourseClass;
import org.example.coursemanager.model.User;
import org.springframework.context.ApplicationEvent;
import java.util.Set;


public class SendMailEvent extends ApplicationEvent {
    private  Set<User> users;
    private CourseClass courseClass;
    public SendMailEvent(Object source, CourseClass courseClass, Set<User> users) {
        super(source);
        this.courseClass = courseClass;
        this.users = users;
    }

    public CourseClass getCourseClass() {
        return courseClass;
    }

    public void setCourseClass(CourseClass courseClass) {
        this.courseClass = courseClass;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
