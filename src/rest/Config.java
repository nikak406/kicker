package rest;

import engine.Randomizer;
import model.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.ws.rs.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Path("manage")
public class Config {

    private Championship championship;

    @GET
    @Produces({"text/xml"})
    public Championship getExpenseList() {
        return Championship.getInstance();
    }

    @PUT
    @Consumes("*/xml")
    public void createConfig(String xml) {
        championship = new Championship();

        Document doc = createDoc(xml);
        if (doc == null) {
            return;
        }

        championship.setPlayers(getPlayers(doc));
        Schedule matches = new Schedule();
        matches.addAll(getMatches(doc, "history"));
        matches.addAll(getMatches(doc, "forecast"));
        championship.setMatches(matches);

        if (matches.isEmpty()){
            championship.setMatches(new Randomizer().randomize());
        }

        Championship.setInstance(championship);
    }

    private Document createDoc(String xml) {
        Document doc = null;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));
            doc = db.parse(is);
            doc.getDocumentElement().normalize();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

    private Collection<? extends Match> getMatches(Document doc, String tag) {
        NodeList nodes = doc.getElementsByTagName(tag);
        Schedule schedule = new Schedule();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            Match match = getMatch(node);
            schedule.add(match);
        }
        return schedule;
    }

    private Set<Player> getPlayers(Document doc) {
        NodeList nodes = doc.getElementsByTagName("players");
        Set<Player> result = new HashSet<>();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            Player player = getPlayer(node);
            result.add(player);
        }
        return result;
    }

    private Match getMatch(Node node) {
        String p1t1, p2t1, p1t2, p2t2, s1, s2;
        Element scoreElement = ((Element) ((Element) node).getElementsByTagName("score").item(0));
        if (scoreElement != null) {
            s1 = scoreElement.getElementsByTagName("score1").item(0).getChildNodes().item(0).getNodeValue();
            s2 = scoreElement.getElementsByTagName("score2").item(0).getChildNodes().item(0).getNodeValue();
        } else {
            s1 = "";
            s2 = "";
        }

        p1t1 = ((Element) ((Element) node).getElementsByTagName("team1").item(0))
                .getElementsByTagName("name").item(0).getChildNodes().item(0).getNodeValue();
        p2t1 = ((Element) ((Element) node).getElementsByTagName("team1").item(1))
                .getElementsByTagName("name").item(0).getChildNodes().item(0).getNodeValue();

        p1t2 = ((Element) ((Element) node).getElementsByTagName("team2").item(0))
                .getElementsByTagName("name").item(0).getChildNodes().item(0).getNodeValue();
        p2t2 = ((Element) ((Element) node).getElementsByTagName("team2").item(1))
                .getElementsByTagName("name").item(0).getChildNodes().item(0).getNodeValue();

        Player p1, p2;
        Team t1, t2;
        p1 = championship.findPlayer(p1t1);
        p2 = championship.findPlayer(p2t1);
        t1 = new Team(p1, p2);

        p1 = championship.findPlayer(p1t2);
        p2 = championship.findPlayer(p2t2);
        t2 = new Team(p1, p2);
        Match match = new Match(t1, t2);
        Score score = null;
        if (!s1.isEmpty()) {
            try {
                int score1 = Integer.parseInt(s1);
                int score2 = Integer.parseInt(s2);
                score = new Score(score1, score2);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if (score != null) {
            match.setScore(score);
        }
        return match;
    }

    private Player getPlayer(Node node) {
        String name = ((Element) node).getElementsByTagName("name").item(0).getChildNodes().item(0).getNodeValue();
        return new Player(name);
    }
}
