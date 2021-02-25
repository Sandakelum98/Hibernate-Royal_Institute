package lk.ijse.studentManagement.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Course implements SuperEntity{
    @Id
    private String code;
    private String courseName;
    private String duration;
    private double courseFee;
    @OneToMany(mappedBy = "course")
    private List<Registration> registrations;


    public Course() {
    }

    public Course(String code, String courseName, String duration, double courseFee) {
        this.code = code;
        this.courseName = courseName;
        this.duration = duration;
        this.courseFee = courseFee;
    }

    public Course(String code, String courseName, String duration, double courseFee, List<Registration> registrations) {
        this.code = code;
        this.courseName = courseName;
        this.duration = duration;
        this.courseFee = courseFee;
        this.registrations = registrations;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getCourseFee() {
        return courseFee;
    }

    public void setCourseFee(double courseFee) {
        this.courseFee = courseFee;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }

    @Override
    public String toString() {
        return "Course{" +
                "code='" + code + '\'' +
                ", courseName='" + courseName + '\'' +
                ", duration='" + duration + '\'' +
                ", courseFee=" + courseFee +
                ", registrations=" + registrations +
                '}';
    }
}
