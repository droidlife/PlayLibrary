package models;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.CreatedTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Student extends Model {

    @Id
    @GeneratedValue
    public int id;

    public String studentName;
    public String studentRollnumber;
    public String studentEmail;
    public String branch;
    public String password;
    public Integer semester;
    public Integer year;

    @CreatedTimestamp
    public Date createdOn;

    public static Finder<Integer, Student> find = new Finder<>(Student.class);

    public void setPassword(String password) {
        this.password = password;
    }
}
