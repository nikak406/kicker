package rest;

import model.Championship;

import javax.ws.rs.*;

@Path("config")
public class Config {

    @GET
    @Produces({"text/xml"})
    public Championship getExpenseList(){
        return Championship.getInstance();
    }

    @PUT
    @Consumes("application/xml")
    public void createConfig(Championship championship){
        Championship.setInstance(championship);
    }
}
