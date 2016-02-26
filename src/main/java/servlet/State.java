package servlet;

import model.Championship;
import model.Match;
import model.Score;
import engine.XmlParser;
import ofy.OfyHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class State extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF8");
        resp.getWriter().println(Championship.getInstance().toString());
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InputStream is = req.getInputStream();
        StringBuilder sb=new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF8"));
        String read;
        while((read=br.readLine()) != null) {
            sb.append(read);
        }
        br.close();
        String s = sb.toString();
        new XmlParser().createConfig(s);
        resp.setCharacterEncoding("UTF8");
        resp.getWriter().println(s);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String score = req.getParameter("score");
        String matchName = req.getParameter("match");
        if (matchName != null && !matchName.isEmpty()){
            Match ourMatch = null;
            for (Match match : Championship.getInstance().getForecast()){
                if (matchName.trim().equals(match.toString().trim())) {
                    ourMatch = match;
                }
            }
            if (ourMatch != null && score != null && !score.isEmpty()) {
                Score matchScore = new Score(score);
                ourMatch.setScore(matchScore);
                OfyHelper.save(Championship.getInstance().toString());
            }
        }
        resp.sendRedirect("/history.jsp");
    }
}
