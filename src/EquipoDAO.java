import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class    EquipoDAO {
    private DBConnection dbConnection = new DBConnection();

    public void registrarEquipo(Equipo equipo) throws SQLException {

        String sql = "INSERT INTO equipo (Nombre, CategoriaID) VALUES (?, ?)";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, equipo.getNombre());
            stmt.setInt(2, equipo.getCategoriaID());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw e;
        }
    }

    public List<Equipo> listarEquiposPorCategoria(int categoriaID) throws SQLException {
        List<Equipo> equipos = new ArrayList<>();
        String sql = "SELECT * FROM equipo WHERE CategoriaID = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, categoriaID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                equipos.add(new Equipo(
                        rs.getInt("ID"),
                        rs.getString("Nombre"),
                        rs.getInt("CategoriaID")
                ));
            }

        } catch (SQLException e) {
            throw e;
        }

        return equipos;
    }


     public void actualizarEquipo(Equipo equipo) throws SQLException {
        String sql = "UPDATE equipo SET Nombre = ?, CategoriaID = ? WHERE ID = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, equipo.getNombre());
            stmt.setInt(2, equipo.getCategoriaID());
            stmt.setInt(3, equipo.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw e;
        }
    }

    public boolean eliminarEquipo(int id) throws SQLException {
        String sql = "DELETE FROM equipo WHERE id = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int filasAfectadas = stmt.executeUpdate(); // Guarda el numero de filas afectadas
            return filasAfectadas > 0;
/*
            if (filasAfectadas > 0) {   // Verifica si se eliminó al menos una fila
                return true; // Si sí, devuelve true
            } else {
                return false; // Si no, devuelve false (por ejemplo, si el ID no existe)
            }
*/
        } catch (SQLException e) {
            throw e;
        }
    }


    public Equipo obtenerEquipoPorID(int idequipo) throws SQLException {

            String sql = "SELECT * FROM equipo WHERE ID = ?";
            try (Connection conn = dbConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1,  idequipo);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return new Equipo(
                            rs.getInt("ID"),
                            rs.getString("Nombre"),
                            rs.getInt("CategoriaID")
                    );
                }
            } catch (SQLException e) {
                throw e;
            }
            return null;
        }
    }

