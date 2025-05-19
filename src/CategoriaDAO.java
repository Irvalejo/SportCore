import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    public void registrarCategoria(Categoria categoria) {
        String sql = "INSERT INTO categoria (Nombre, RestriccionEdadMin, RestriccionEdadMax, LigaID) VALUES (?, ?, ?, ?)";

        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, categoria.getNombre());

            if (categoria.getRestriccionEdadMin() != null) {
                stmt.setInt(2, categoria.getRestriccionEdadMin());
            } else {
                stmt.setNull(2, Types.INTEGER);
            }

            if (categoria.getRestriccionEdadMax() != null) {
                stmt.setInt(3, categoria.getRestriccionEdadMax());
            } else {
                stmt.setNull(3, Types.INTEGER);
            }

            stmt.setInt(4, categoria.getLigaID());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al registrar categoría: " + e.getMessage());
        }
    }

    public List<Categoria> listarCategorias() {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categoria";

        try (Connection conn = new DBConnection().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Integer edadMin = rs.getObject("RestriccionEdadMin") != null ? rs.getInt("RestriccionEdadMin") : null;
                Integer edadMax = rs.getObject("RestriccionEdadMax") != null ? rs.getInt("RestriccionEdadMax") : null;

                categorias.add(new Categoria(
                        rs.getInt("ID"),
                        rs.getString("Nombre"),
                        edadMin,
                        edadMax,
                        rs.getInt("LigaID")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar categorías: " + e.getMessage());
        }

        return categorias;
    }


    public List<Categoria> listarCategoriasPorLiga(int ligaID) {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categoria WHERE LigaID = ?";

        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ligaID);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Integer edadMin = rs.getObject("RestriccionEdadMin") != null ? rs.getInt("RestriccionEdadMin") : null;
                    Integer edadMax = rs.getObject("RestriccionEdadMax") != null ? rs.getInt("RestriccionEdadMax") : null;

                    categorias.add(new Categoria(
                            rs.getInt("ID"),
                            rs.getString("Nombre"),
                            edadMin,
                            edadMax,
                            rs.getInt("LigaID")
                    ));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al listar categorías por liga: " + e.getMessage());
        }

        return categorias;
    }

}