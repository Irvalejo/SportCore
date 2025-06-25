import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.sql.Date;

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
            stmt.setDate(3, Date.valueOf(jugador.getFechaNacimiento()));
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
                        (rs.getDate("FechaNacimiento") !=null)? rs.getDate("FechaNacimiento").toLocalDate():null,
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

    public Jugador obtenerJugadorPorID(int id) {
        String sql = "SELECT * FROM jugador WHERE ID = ?";
        try (Connection conn = new DBConnection().getConnection();
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
            System.out.println("Error al obtener jugador: " + e.getMessage());
        }
        return null;
    }

    public List<Jugador> buscarJugadoresPorNombre(String termino) {
        List<Jugador> jugadores = new ArrayList<>();
        String sql = "SELECT * FROM jugador WHERE Nombre LIKE ? OR Apellido LIKE ?";

        try (Connection conn = new DBConnection().getConnection();
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
            System.out.println("Error al buscar jugadores: " + e.getMessage());
        }

        return jugadores;
    }

//pite
    public void actualizarJugador(Jugador jugador) {
        String sql = "UPDATE jugador SET Nombre = ?, Apellido = ?, FechaNacimiento = ?, Correo = ?, Foto = ?, EquipoID = ? WHERE ID = ?";
        try (Connection conn = new DBConnection().getConnection();
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
            System.out.println("Error al actualizar jugador: " + e.getMessage());
        }
    }

    public boolean eliminarJugador(int id) {
        String sql = "DELETE FROM jugador WHERE id = ?";
        try (Connection conn = new DBConnection().getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, id);
                int filasAfectadas = stmt.executeUpdate(); // Guarda el numero de filas afectadas


                if (filasAfectadas > 0) {   // Verifica si se eliminó al menos una fila
                    return true; // Si sí, devuelve true
                } else {
                    return false; // Si no, devuelve false (por ejemplo, si el ID no existe)
                }

            } catch (SQLException e) {
                System.out.println("Error al eliminar categoría: " + e.getMessage());
                return false; // Si ocurre un error de SQL, la operación falló
            }
    }



}

