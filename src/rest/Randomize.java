package rest;

import engine.Randomizer;
import model.Championship;
import model.Match;
import model.Schedule;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("randomize")
public class Randomize {

    @GET
    @Produces({"text/xml"})
    public Championship getExpenseList() {
        Championship championship = Championship.getInstance();
        Schedule schedule = new Randomizer().randomize();
        for (Match match : schedule) {
            championship.addMatch(match);
        }
        return championship;
    }
}
