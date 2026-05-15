package joc;
import java.sql.*;
import java.util.ArrayList;

public class bd {
    private static final String db_url = "jdbc:mysql://localhost:3306/laberynth";
    private static final String user = "seda";
    private static final String pswd = "seda";



    public static int insertUser(String nom) {
        String sql = "INSERT INTO jugadors (nom) VALUES (?)";
        int idGenerat = -1;
        try (Connection con = DriverManager.getConnection(db_url, user, pswd);
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, nom);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                idGenerat = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error al inserir jugador: " + e.getMessage());
        }
        return idGenerat;
    }

    public static ArrayList<String> obtenirTop5() {
        ArrayList<String> llista = new ArrayList<>();
        String sql = "SELECT j.nom, MIN(t.temps) AS best_score " +
                "FROM jugadors j " +
                "INNER JOIN temps t ON j.id_jugador = t.id_jugador " +
                "GROUP BY j.id_jugador, j.nom " +
                "ORDER BY best_score ASC LIMIT 5";

        try (Connection con = DriverManager.getConnection(db_url, user, pswd);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                llista.add(rs.getString("nom") + ": " + rs.getInt("best_score") + "s");
            }
        } catch (SQLException e) {
            System.out.println("Error SQL Top 5");
        }
        return llista;
    }

    public static void insertTemps(int temps, int idJugador)  {
        String sql = "INSERT INTO temps (temps, id_jugador) VALUES (?, ?)";
        try {
            Connection con = DriverManager.getConnection(db_url, user, pswd);
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, temps);
            ps.setInt(2, idJugador);
            ps.executeUpdate();
        }catch (Exception e){
            System.out.println("Erro ao inserir temps: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static int getUserId (String userName){
      String sql = "select id_jugador from jugadors where nom = (?)";
      int id = -1;
      try  {
          Connection con = DriverManager.getConnection(db_url, user, pswd);
          PreparedStatement ps = con.prepareStatement(sql);

          ps.setString(1 , userName);
          ResultSet rs = ps.executeQuery();
          if (rs.next()) {
              id = rs.getInt("id_jugador");
          }
          rs.close();
          ps.close();
          con.close();
          }catch (Exception e){
          System.out.println("Erro ao inserir jugador: " + e.getMessage());
          e.printStackTrace();
      }
      return id;
    }
}