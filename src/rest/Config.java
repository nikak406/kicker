package rest;

import model.*;

import javax.ws.rs.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

@Path("config")
public class Config {

    @GET
    @Produces({"text/xml"})
    public Championship getExpenseList(){
        return Championship.getInstance();
    }

    @PUT
    @Consumes("*/xml")
    public void createConfig(String string){
        try {
            System.out.println("here!!!");
            System.out.println(string);
            JAXBContext context = JAXBContext.newInstance(Championship.class, Match.class, Player.class, Schedule.class, Score.class, Team.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Championship championship = (Championship) unmarshaller.unmarshal(new StringReader(string));
            System.out.println(championship.getForecast().size());
            Championship.setInstance(championship);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
