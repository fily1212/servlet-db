package it.unito.serverdb1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/taass";
    private static final String USER = "taass";
    private static final String PASSWORD = "1234";

    // Metodo per ottenere la connessione
    private Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL non trovato", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Metodo per ottenere tutti gli studenti
    public List<Studente> getStudents() {
        String query = "SELECT id, nome, cognome, email FROM Studente";
        List<Studente> studenti = new ArrayList<Studente>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String cognome = rs.getString("cognome");
                String email = rs.getString("email");
                studenti.add(new Studente(id, nome, cognome, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studenti;
    }

    // Metodo main per testare

}
