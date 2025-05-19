import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JugadorDAO {

    public void registrarJugador(Jugador jugador) {
        DBConnection db = new DBConnection();
        db.abrirConexion();
        Connection conn = db.getConnection();

        if (conn == null) {
            System.out.println("Conexión fallida. No se pudo registrar al jugador.");
            return;
        }


        String sql = "INSERT INTO jugador (Nombre, Apellido, FechaNacimiento, Correo, Foto, EquipoID) VALUES (?, ?, ?, ?, ?, ?)";


        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, jugador.getNombre());
            stmt.setString(2, jugador.getApellido());
            stmt.setString(3, jugador.getFechaNacimiento());
            stmt.setString(4, jugador.getCorreo());
            stmt.setString(5, jugador.getFoto());
            stmt.setInt(6, jugador.getEquipoID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al registrar jugador: " + e.getMessage());
        } finally {
            db.cerrarConexion();
        }
    }

    public List<Jugador> listarJugadoresPorEquipo(int equipoID) {
        List<Jugador> jugadores = new ArrayList<>();
        DBConnection db = new DBConnection();
        db.abrirConexion();
        Connection conn = db.getConnection();

        if (conn == null) {
            System.out.println("Conexión fallida. No se pudo listar jugadores.");
            return jugadores;
        }


        String sql = "SELECT * FROM jugador WHERE EquipoID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, equipoID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Jugador j = new Jugador(
                        rs.getInt("ID"),
                        rs.getString("Nombre"),
                        rs.getString("Apellido"),
                        rs.getString("FechaNacimiento"),
                        rs.getString("Correo"),
                        rs.getString("Foto"),
                        rs.getInt("EquipoID")
                );
                jugadores.add(j);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar jugadores: " + e.getMessage());
        } finally {
            db.cerrarConexion();
        }

        return jugadores;
    }
}

