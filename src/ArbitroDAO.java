import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public class ArbitroDAO {

    private DBConnection dbConnection = new DBConnection();

    public void registrarArbitro(Arbitro a) throws SQLException {
        String sql = "INSERT INTO arbitro (Nombre, ApellidoPaterno, ApellidoMaterno, FechaNacimiento, Correo, Telefono) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, a.getNombre());
            stmt.setString(2, a.getApellidoPaterno());
            stmt.setString(3, a.getApellidoMaterno());

            if (a.getFechaNacimiento() != null) {
                stmt.setDate(4, Date.valueOf(a.getFechaNacimiento()));
            } else {
                stmt.setNull(4, Types.DATE);
            }

            stmt.setString(5, a.getCorreo());
            stmt.setString(6, a.getTelefono());

            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) a.setId(keys.getInt(1));
            }

        } catch (SQLException e) {
            throw e;
        }
    }

    public List<Arbitro> listarArbitros() throws SQLException {
        List<Arbitro> arbitros = new ArrayList<>();
        String sql = "SELECT * FROM arbitro";

        try (Connection conn = dbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Date fn = rs.getDate("FechaNacimiento");
                Arbitro a = new Arbitro(
                        rs.getInt("ID"),
                        rs.getString("Nombre"),
                        rs.getString("ApellidoPaterno"),
                        rs.getString("ApellidoMaterno"),
                        fn != null ? fn.toLocalDate() : null,
                        rs.getString("Correo"),
                        rs.getString("Telefono")
                );
                arbitros.add(a);
            }

        } catch (SQLException e) {
            throw e;
        }

        return arbitros;
    }

    public Arbitro obtenerArbitroPorId(int id) throws SQLException {
        String sql = "SELECT * FROM arbitro WHERE ID = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Date fn = rs.getDate("FechaNacimiento");
                    return new Arbitro(
                            rs.getInt("ID"),
                            rs.getString("Nombre"),
                            rs.getString("ApellidoPaterno"),
                            rs.getString("ApellidoMaterno"),
                            fn != null ? fn.toLocalDate() : null,
                            rs.getString("Correo"),
                            rs.getString("Telefono")
                    );
                }
            }

        } catch (SQLException e) {
            throw e;
        }

        return null;
    }

    public List<Arbitro> listarArbitrosPorNombreLike(String nombreParcial) throws SQLException {
        List<Arbitro> arbitros = new ArrayList<>();
        String sql = "SELECT * FROM arbitro WHERE Nombre LIKE ? OR ApellidoPaterno LIKE ? OR ApellidoMaterno LIKE ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String like = "%" + nombreParcial + "%";
            stmt.setString(1, like);
            stmt.setString(2, like);
            stmt.setString(3, like);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Date fn = rs.getDate("FechaNacimiento");
                    Arbitro a = new Arbitro(
                            rs.getInt("ID"),
                            rs.getString("Nombre"),
                            rs.getString("ApellidoPaterno"),
                            rs.getString("ApellidoMaterno"),
                            fn != null ? fn.toLocalDate() : null,
                            rs.getString("Correo"),
                            rs.getString("Telefono")
                    );
                    arbitros.add(a);
                }
            }

        } catch (SQLException e) {
            throw e;
        }

        return arbitros;
    }

    public void actualizarArbitro(Arbitro a) throws SQLException {
        String sql = "UPDATE arbitro SET Nombre = ?, ApellidoPaterno = ?, ApellidoMaterno = ?, "
                + "FechaNacimiento = ?, Correo = ?, Telefono = ? WHERE ID = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, a.getNombre());
            stmt.setString(2, a.getApellidoPaterno());
            stmt.setString(3, a.getApellidoMaterno());

            if (a.getFechaNacimiento() != null) {
                stmt.setDate(4, Date.valueOf(a.getFechaNacimiento()));
            } else {
                stmt.setNull(4, Types.DATE);
            }

            stmt.setString(5, a.getCorreo());
            stmt.setString(6, a.getTelefono());
            stmt.setInt(7, a.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw e;
        }
    }

    public boolean eliminarArbitro(int id) throws SQLException {
        String sql = "DELETE FROM arbitro WHERE ID = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            throw e;
        }
    }
}
