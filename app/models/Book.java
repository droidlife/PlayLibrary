package models;


import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Book extends Model{

    @Id
    @GeneratedValue
    public Integer id;

    @ManyToOne
    public Student student;

    @ManyToOne
    public LibraryAdmin libraryAdmin;

    public Integer ISBN;

    public String name;
    public String author;
    public String domain;

    public boolean availability;

    public boolean isIssued;

    public Date dateOfSubmission;

    public Date dateOfIssue;


}
