import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.sql.Date;


public class JugadorDAO {

    private DBConnection dbConnection = new DBConnection();

    public void registrarJugador(Jugador jugador) throws SQLException {

        String sql = "INSERT INTO jugador (Nombre, Apellido, FechaNacimiento, Correo, Foto, EquipoID) VALUES (?, ?, ?, ?, ?, ?)";


        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {


            stmt.setString(1, jugador.getNombre());
            stmt.setString(2, jugador.getApellido());
            stmt.setDate(3, Date.valueOf(jugador.getFechaNacimiento()));
            stmt.setString(4, jugador.getCorreo());
            stmt.setString(5, jugador.getFoto());
            stmt.setInt(6, jugador.getEquipoID());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                jugador.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw e;
        }

    }

    public List<Jugador> listarJugadoresPorEquipo(int equipoID) throws SQLException {
        List<Jugador> jugadores = new ArrayList<>();
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM jugador WHERE EquipoID = ?");
            stmt.setInt(1, equipoID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Jugador j = new Jugador(
                        rs.getInt("ID"),
                        rs.getString("Nombre"),
                        rs.getString("Apellido"),
                        (rs.getDate("FechaNacimiento") != null) ? rs.getDate("FechaNacimiento").toLocalDate() : null,
                        rs.getString("Correo"),
                        rs.getString("Foto"),
                        rs.getInt("EquipoID")
                );
                jugadores.add(j);
            }
        } catch (SQLException e) {
            throw e;
        }
        return jugadores;
    }



    public Jugador obtenerJugadorPorID(int id) throws SQLException {
        String sql = "SELECT * FROM jugador WHERE ID = ?";


        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Jugador(
                        rs.getInt("ID"),
                        rs.getString("Nombre"),
                        rs.getString("Apellido"),
                        (rs.getDate("FechaNacimiento") !=null)? rs.getDate("FechaNacimiento").toLocalDate():null,
                        rs.getString("Correo"),
                        rs.getString("Foto"),
                        rs.getInt("EquipoID")
                );
            }
        } catch (SQLException e) {
                throw e;}
        return null;
    }

    public List<Jugador> buscarJugadoresPorNombre(String termino) throws SQLException {
        List<Jugador> jugadores = new ArrayList<>();
        String sql = "SELECT * FROM jugador WHERE Nombre LIKE ? OR Apellido LIKE ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + termino + "%");
            stmt.setString(2, "%" + termino + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                jugadores.add(new Jugador(
                        rs.getInt("ID"),
                        rs.getString("Nombre"),
                        rs.getString("Apellido"),
                        (rs.getDate("FechaNacimiento") !=null)? rs.getDate("FechaNacimiento").toLocalDate():null,
                        rs.getString("Correo"),
                        rs.getString("Foto"),
                        rs.getInt("EquipoID")));
            }

        } catch (SQLException e) {
            throw e;        }

        return jugadores;
    }


    public void actualizarJugador(Jugador jugador) throws SQLException {
        String sql = "UPDATE jugador SET Nombre = ?, Apellido = ?, FechaNacimiento = ?, Correo = ?, Foto = ?, EquipoID = ? WHERE ID = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1,jugador.getNombre());
            stmt.setString(2, jugador.getApellido());
            stmt.setDate(3,Date.valueOf(jugador.getFechaNacimiento()) );
            stmt.setString(4, jugador.getCorreo());
            stmt.setString(5, jugador.getFoto());
            stmt.setInt(6, jugador.getEquipoID());
            stmt.setInt(7, jugador.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
          throw e;
        }
    }

    public boolean eliminarJugador(int id) throws SQLException {
        String sql = "DELETE FROM jugador WHERE id = ?";
        try (Connection conn = dbConnection.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, id);
                int filasAfectadas = stmt.executeUpdate(); // Guarda el numero de filas afectadas
               return filasAfectadas > 0;

            /* if (filasAfectadas > 0) {   // Verifica si se eliminó al menos una fila
                    return true; // Si sí, devuelve true
                } else {
                    return false; // Si no, devuelve false (por ejemplo, si el ID no existe)
                }
                */
            } catch (SQLException e) {
                throw e;
            }
    }
}

