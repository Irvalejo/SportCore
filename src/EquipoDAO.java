import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class    EquipoDAO {

    public void registrarEquipo(Equipo equipo) {
        String sql = "INSERT INTO equipo (Nombre, CategoriaID) VALUES (?, ?)";

        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, equipo.getNombre());
            stmt.setInt(2, equipo.getCategoriaID());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al registrar equipo: " + e.getMessage());
        }
    }

    public List<Equipo> listarEquiposPorCategoria(int categoriaID) {
        List<Equipo> equipos = new ArrayList<>();
        String sql = "SELECT * FROM equipo WHERE CategoriaID = ?";

        try (Connection conn = new DBConnection().getConnection();
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
            System.out.println("Error al listar equipos: " + e.getMessage());
        }

        return equipos;
    }


     public void actualizarEquipo(Equipo equipo) {
        String sql = "UPDATE equipo SET Nombre = ?, CategoriaID = ? WHERE ID = ?";

        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, equipo.getNombre());
            stmt.setInt(2, equipo.getCategoriaID());
            stmt.setInt(3, equipo.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al actualizar equipo: " + e.getMessage());
        }
    }

    public boolean eliminarEquipo(int id) {
        String sql = "DELETE FROM equipo WHERE id = ?";

        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar equipo: " + e.getMessage());
            return false;
        }
    }


    public Equipo obtenerEquipoPorID(int idequipo) {

            String sql = "SELECT * FROM equipo WHERE ID = ?";
            try (Connection conn = new DBConnection().getConnection();
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
                System.out.println("Error al obtener jugador: " + e.getMessage());
            }
            return null;
        }
    }

