package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class LibraryAdmin extends Model{

    @Id
    @GeneratedValue
    public Integer  id;

    public Integer adminId;

    public String name;

    public String password;


}
