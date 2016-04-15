package models;


import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
public class BookIssued extends Model{

    @Id
    @GeneratedValue
    public Integer id;

    @OneToOne
    public Student student;

    @OneToOne
    public LibraryAdmin libraryAdmin;

    @OneToOne
    public Books books;


    public Date dateOfSubmission;

    public Date dateOfIssue;


}
