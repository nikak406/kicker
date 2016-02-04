package ofy;

import com.googlecode.objectify.ObjectifyService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class OfyHelper implements ServletContextListener {
    public void contextInitialized(ServletContextEvent event) {
        // This will be invoked as part of a warmup request, or the first user request if no warmup
        // request.
        ObjectifyService.register(Text.class);
    }

    public void contextDestroyed(ServletContextEvent event) {
        // App Engine does not currently invoke this method.
    }

    public static void save(String string) {
        Text text = new Text();
        text.content = string;
        ObjectifyService.ofy().save().entity(text).now();
    }

    public static String get(){
        Text text = ObjectifyService.ofy().load().type(Text.class).id(1l).now();
        return text.content;
    }
}
