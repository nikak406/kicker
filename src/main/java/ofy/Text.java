package ofy;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Text{
    @Id public Long id = 1l;
    public String content;
}
