package models;


import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.CreatedTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class BookIssued extends Model{

    @Id
    @GeneratedValue
    public Integer id;

    @ManyToOne
    public Student student;

    @OneToOne
    public LibraryAdmin libraryAdmin;

    @ManyToOne
    public Books books;


    public Date dateOfSubmission;

    @CreatedTimestamp
    public Date dateOfIssue;


}
