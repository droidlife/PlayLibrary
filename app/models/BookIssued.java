package models;


import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.CreatedTimestamp;

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

    @CreatedTimestamp
    public Date dateOfIssue;


}
