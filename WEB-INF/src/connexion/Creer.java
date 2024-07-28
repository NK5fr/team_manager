package connexion;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.DS;
import util.LettersManager;


@WebServlet("/creer")
public class Creer extends HttpServlet{
    

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        
        String nom = req.getParameter("nom");
        String mdp = req.getParameter("mdp");
        String verif = req.getParameter("verif");


        PreparedStatement ps = null;
        try(Connection con = DS.getConnection()){
            if(invalide(nom) || invalide(mdp) || invalide(verif) || !mdp.equals(verif)){
                throw new Exception("nom ou mdp invalide");
            }
            nom = LettersManager.escape(nom);
            ps = con.prepareStatement("insert into utilisateurs values(?, ?)");
            ps.setString(1, nom);
            ps.setString(2, mdp);
            ps.executeUpdate();
            res.sendRedirect("connexion?nom="+nom+"&mdp="+mdp);
        }catch(Exception e){
            System.out.println(ps);
            System.out.println(e.getMessage());
        }

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>erreur</title><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH\" crossorigin=\"anonymous\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class=\"alert alert-warning w-50 mx-auto my-5 text-center\" role=\"alert\">");
        out.println("Erreur d'inscription");
        out.println("<p></p>");
        out.println("<a href='inscription.html'>retour</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

    private boolean invalide(String s){
        return s == null || s.isEmpty();
    }
}
