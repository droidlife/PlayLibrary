package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Fine extends Model {

    @Id
    @GeneratedValue
    public Integer id;

    @ManyToOne
    public Student student;

    public boolean isPaid;

    public Integer days;
}
