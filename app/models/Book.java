package models;


import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Book extends Model{

    @Id
    @GeneratedValue
    public Integer id;

    public Integer ISBN;

    public String name;
    public String author;
    public String domain;

    public boolean availability;


}
