package controleur;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import comparator.AgeComparator;
import comparator.FirstnameComparator;
import comparator.LastnameComparator;
import comparator.PositionComparator;
import comparator.ValueComparator;
import dao.JoueursDao;
import dto.Joueur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.Position;


@WebServlet("/control")
public class Control extends HttpServlet{

    private static final JoueursDao dao = new JoueursDao();
    private static Map<String, String> situationTranslation = Map.of("possédé", "owned", "souhaité", "wished", "transferable", "transferable", "autre", "other");
    private static Map<String, Comparator<Joueur>> comparators = Map.of("prenom", new FirstnameComparator(), "nom", new LastnameComparator(), "age", new AgeComparator(), "position", new PositionComparator(), "valeur", new ValueComparator());

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String login = (String) req.getSession(true).getAttribute("nom");
        String column = (String) req.getSession(true).getAttribute("column");
        String direction = (String) req.getSession(true).getAttribute("direction");

        if(login == null){
            res.sendRedirect("login.html");
            return;
        }
        if(column == null){
            column = "position";
            direction = "asc";
            req.getSession(true).setAttribute("column", column);
            req.getSession(true).setAttribute("direction", direction);
        }

        String action = req.getParameter("action");
        String jno = req.getParameter("jno");
        String prenom = req.getParameter("prenom");
        String nom = req.getParameter("nom");
        String age = req.getParameter("age");
        String position = req.getParameter("position");
        String situation = req.getParameter("situation");
        String valeur = req.getParameter("valeur");
        String description = req.getParameter("description");
        String colonne = req.getParameter("colonne");
        String from = req.getParameter("from");

        if(description != null && description.isEmpty()) description = "Rien à redire.";

        if(valids(colonne)){
            if(colonne.equals(column)){
                direction = direction.equals("asc") ? "desc" : "asc";
            }else{
                column = colonne;
                direction = "asc";
            }
            req.getSession(true).setAttribute("column", colonne);
            req.getSession(true).setAttribute("direction", direction);
        }

        String vue = "";
        Joueur j;

        switch (action) {
            case "delete":
                if(!valids(jno, situation)){
                    res.sendError(400,"Paramètre manquant" ); 
                    return;
                }
                if(dao.delete(Integer.parseInt(jno))) {
                    req.setAttribute("joueurs", getJoueurs(column, direction, login));
                    vue = "/WEB-INF/vue/" + situationTranslation.get(situation) + ".jsp";
                }else{
                    vue = "/WEB-INF/vue/delete.jsp";
                }
                break;
            case "add":
                if(!valids(prenom, nom, age, position, situation, valeur, description)){
                    res.sendError(400,"Paramètre manquant" ); 
                    return;
                }
                j = new Joueur(Integer.parseInt(jno), 
                                        nom, 
                                        prenom, 
                                        Integer.parseInt(age), 
                                        Position.valueOf(position), 
                                        situation, 
                                        Integer.parseInt(valeur),
                                        description);
                if(dao.insert(j, login)){
                    vue = "index.jsp";
                }else{
                    vue = "/WEB-INF/vue/add.jsp";
                }
                break;
            case "one":
                if(!valids(jno)){
                    res.sendError(400,"Paramètre manquant" ); 
                    return;
                }
                req.setAttribute("joueur", dao.findById(Integer.parseInt(jno)));
                vue = "/WEB-INF/vue/one.jsp";
                break;
            case "update":
                if(!valids(jno, prenom, nom, age, position, situation, valeur, description)){
                    res.sendError(400,"Paramètre manquant" ); 
                    return;
                }
                j = new Joueur(Integer.parseInt(jno), 
                                        nom, 
                                        prenom, 
                                        Integer.parseInt(age), 
                                        Position.valueOf(position), 
                                        situation, 
                                        Integer.parseInt(valeur),
                                        description);
                if(dao.update(j, Integer.parseInt(jno))){
                    req.setAttribute("joueurs", getJoueurs(column, direction, login));
                    vue = "/WEB-INF/vue/" + situationTranslation.get(situation) + ".jsp";
                }else{
                    vue = "/WEB-INF/vue/update.jsp";
                }
                break;
            case "updateSituation":
                if(!valids(jno, situation, from)){
                    res.sendError(400,"Paramètre manquant" ); 
                    return;
                }else if(dao.updateSituation(situation, Integer.parseInt(jno))){
                    req.setAttribute("joueurs", getJoueurs(column, direction, login));
                    vue = "/WEB-INF/vue/" + from + ".jsp";
                }else{
                    vue = "/WEB-INF/vue/update.jsp";
                }
                break;
            case "transferable":
                req.setAttribute("joueurs", getJoueurs(column, direction, login));
                vue = "/WEB-INF/vue/transferable.jsp";
                break;
            case "owned":
                req.setAttribute("joueurs", getJoueurs(column, direction, login));
                vue = "/WEB-INF/vue/owned.jsp";
                break;
            case "wished":
                req.setAttribute("joueurs", getJoueurs(column, direction, login));
                vue = "/WEB-INF/vue/wished.jsp";
                break;
            case "other":
                req.setAttribute("joueurs", getJoueurs(column, direction, login));
                vue = "/WEB-INF/vue/other.jsp";
                break;
            default:
                res.sendError(404,"Action non supportée"); 
                return;
        }
        req.getRequestDispatcher(vue).forward(req, res);
    }

    private List<Joueur> getJoueurs(String column, String direction, String login){
        List<Joueur> joueurs = dao.findAll(login);
        Collections.sort(joueurs, comparators.get(column));
        if(direction.equals("desc")) Collections.reverse(joueurs);
        return joueurs;
    }

    private boolean valids(String... params){
        for(String s : params){
            if(s == null || s.isEmpty()) return false;
        }
        return true;
    }
}
