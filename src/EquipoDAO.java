import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipoDAO {

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
}
