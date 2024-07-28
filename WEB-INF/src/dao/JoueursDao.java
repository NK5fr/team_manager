package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.Joueur;
import util.DS;
import util.Position;

public class JoueursDao {
    

    public List<Joueur> findAll(String login){
        List<Joueur> joueurs = new ArrayList<>();
        PreparedStatement ps = null;
        try(Connection con = DS.getConnection()){
            ps = con.prepareStatement("select * from joueurs where proprietaire = ?");
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                joueurs.add(new Joueur(rs.getInt("jno"), 
                                        rs.getString("nom"), 
                                        rs.getString("prenom"), 
                                        rs.getInt("age"),
                                        Position.valueOf(rs.getString("position")), 
                                        rs.getString("situation"), 
                                        rs.getInt("valeur"), 
                                        rs.getString("description")));
            }
        } catch (Exception e) {
            System.out.println(ps);
            System.out.println(e.getMessage());
        }
        return joueurs;
    }

    public boolean delete(int jno){
        PreparedStatement ps = null;
        try(Connection con = DS.getConnection()){
            ps = con.prepareStatement("delete from joueurs where jno = ?");
            ps.setInt(1, jno);
            int nb = ps.executeUpdate();
            if (nb == 1) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(ps);
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean insert(Joueur j, String login){
        PreparedStatement ps = null;
        try(Connection con = DS.getConnection()){
            ps = con.prepareStatement("insert into joueurs(prenom, nom, age, position, situation, valeur, description, proprietaire) values(?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, j.getPrenom());
            ps.setString(2, j.getNom());
            ps.setInt(3, j.getAge());
            ps.setString(4, j.getPosition().name());
            ps.setString(5, j.getSituation());
            ps.setInt(6, j.getValeur());
            ps.setString(7, j.getDescription());
            ps.setString(8, login);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(ps);
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Joueur findById(int jno){
        PreparedStatement ps = null;
        Joueur j = null;
        try(Connection con = DS.getConnection()){
            ps = con.prepareStatement("select * from joueurs where jno = ?");
            ps.setInt(1, jno);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                j = new Joueur(rs.getInt("jno"), 
                                rs.getString("nom"), 
                                rs.getString("prenom"), 
                                rs.getInt("age"), 
                                Position.valueOf(rs.getString("position")), 
                                rs.getString("situation"), 
                                rs.getInt("valeur"),
                                rs.getString("description"));
            }
        } catch (Exception e) {
            System.out.println(ps);
            System.out.println(e.getMessage());
        }
        return j;
    }

    public boolean update (Joueur j, int jno){
        PreparedStatement ps = null;
        try(Connection con = DS.getConnection()){
            ps = con.prepareStatement("update joueurs set nom = ?, prenom = ?, age = ?, position = ?, situation = ?, valeur = ?, description = ? where jno = ?");
            ps.setString(1, j.getNom());
            ps.setString(2, j.getPrenom());
            ps.setInt(3, j.getAge());
            ps.setString(4, j.getPosition().name());
            ps.setString(5, j.getSituation());
            ps.setInt(6, j.getValeur());
            ps.setString(7, j.getDescription());
            ps.setInt(8, jno);
            int nb = ps.executeUpdate();
            if (nb == 1) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(ps);
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean updateSituation(String situation, int jno){
        PreparedStatement ps = null;
        try(Connection con = DS.getConnection()){
            ps = con.prepareStatement("update joueurs set situation = ? where jno = ?");
            ps.setString(1, situation);
            ps.setInt(2, jno);
            int nb = ps.executeUpdate();
            if (nb == 1) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(ps);
            System.out.println(e.getMessage());
        }
        return false;
    }
}
