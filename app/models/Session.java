package models;


import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.CreatedTimestamp;
import com.avaje.ebean.annotation.UpdatedTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Session extends Model{

    @Id
    public Integer id;
    public String sessionId;

    @CreatedTimestamp
    public Date createdOn  ;

    @UpdatedTimestamp
    public Date updatedOn;

    public boolean isActive;

    public  Integer userId;

    Finder<Integer,Session> finder = new Finder<Integer, Session>(Session.class);

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }
}
