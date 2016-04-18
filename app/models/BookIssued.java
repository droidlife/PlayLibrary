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

    @ManyToMany
    public Student student;

    @OneToOne
    public LibraryAdmin libraryAdmin;

    @ManyToMany
    public Books books;


    public Date dateOfSubmission;

    @CreatedTimestamp
    public Date dateOfIssue;


}
