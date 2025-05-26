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

    public void actualizarCategoria(Categoria categoria) {
        String sql = "UPDATE categoria SET Nombre = ?, RestriccionEdadMin = ?, RestriccionEdadMax = ? WHERE ID = ?";

        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, categoria.getNombre());
            stmt.setInt(2, categoria.getRestriccionEdadMin());
            stmt.setInt(3, categoria.getRestriccionEdadMax());
            stmt.setInt(4, categoria.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al actualizar categoría: " + e.getMessage());
        }
    }


    public boolean eliminarCategoria(int id) {              // Funcionaba pero no me dejaba eliminar por ID siempre( incluso si de vdd funcionaba ,
                                                           // por eso es diferente a los demas metodos eliminar, hay que hacerlos igual que ete
        String sql = "DELETE FROM categoria WHERE ID = ?";
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

    public Categoria obtenerCategoriaPorId(int id) {
        String sql = "SELECT * FROM categoria WHERE id = ?";

        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Integer edadMin = rs.getObject("RestriccionEdadMin") != null ? rs.getInt("RestriccionEdadMin") : null;
                    Integer edadMax = rs.getObject("RestriccionEdadMax") != null ? rs.getInt("RestriccionEdadMax") : null;

                    return new Categoria(
                            rs.getInt("ID"),
                            rs.getString("Nombre"),
                            edadMin,
                            edadMax,
                            rs.getInt("LigaID")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener categoría: " + e.getMessage());
        }

        return null;
    }

}