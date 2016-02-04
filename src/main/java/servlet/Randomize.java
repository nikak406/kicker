package servlet;

import engine.Randomizer;
import model.Championship;
import model.Match;
import model.Schedule;
import ofy.OfyHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Randomize extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Championship championship = Championship.getInstance();
        Schedule schedule = new Randomizer().randomize();
        for (Match match : schedule) {
            championship.addMatch(match);
        }
        OfyHelper.save(championship.toString());
        resp.sendRedirect("forecast.jsp");
    }
}
