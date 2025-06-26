import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.sql.Date;

public class ManagerDAO {

    private DBConnection dbConnection = new DBConnection();


    public void registrarManager(Manager manager) throws SQLException {
    String sql = "INSERT INTO manager (NombreCompleto, FechaNacimiento,Correo,Telefono,EquipoID ) VALUES (?, ?, ?, ?, ?)";

    try (Connection conn = dbConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        stmt.setString(1, manager.getNombreCompleto());
        stmt.setDate(2, Date.valueOf(manager.getFechaNacimiento()));
        stmt.setString(3, manager.getCorreo());
        stmt.setString(4, manager.getTelefono());
        stmt.setInt(5, manager.getEquipoID());
        stmt.executeUpdate();

        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            manager.setId(rs.getInt(1));
        }

    } catch (SQLException e) {
        throw e;
    }
}

    public List<Manager> listarManagerPorEquipo(int equipoID) throws SQLException {
        List<Manager> manager = new ArrayList<>();
        String sql = "SELECT * FROM manager WHERE EquipoID = ?";

        try ( Connection conn = dbConnection.getConnection();
              PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, equipoID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Manager m = new Manager(
                        rs.getInt("ID"),
                        rs.getString("NombreCompleto"),
                        rs.getString("Correo"),
                        (rs.getDate("FechaNacimiento") !=null) ? rs.getDate("FechaNacimiento").toLocalDate() : null,
                        rs.getString("Telefono"),
                        rs.getInt("EquipoID")
                );

                manager.add(m);
            }
        } catch (SQLException e) {
            throw e;
        }
        return manager;
    }

    public void actualizarManager(Manager manager) throws SQLException {
        String sql = "UPDATE manager SET NombreCompleto = ?, FechaNacimiento = ?, Correo = ?, Telefono = ?, EquipoID = ? WHERE ID = ?";
        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, manager.getNombreCompleto());
            stmt.setDate(2, Date.valueOf(manager.getFechaNacimiento()));
            stmt.setString(3, manager.getCorreo());
            stmt.setString(4, manager.getTelefono());
            stmt.setInt(5, manager.getEquipoID());
            stmt.setInt(6, manager.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw e;
        }
    }

    public Manager obtenerManagerPorID(int id) throws SQLException {
        String sql = "SELECT * FROM manager WHERE ID = ?";
        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
               return new Manager(
                       rs.getInt("ID"),
                       rs.getString("NombreCompleto"),
                       rs.getString("Correo"),
                       (rs.getDate("FechaNacimiento") !=null) ? rs.getDate("FechaNacimiento").toLocalDate() : null,
                       rs.getString("Telefono"),
                       rs.getInt("EquipoID")
               );
            }
        } catch (SQLException e) {
            throw e;
        }
        return null;
    }

    public boolean eliminarManager(int id) throws SQLException {
        String sql = "DELETE FROM manager WHERE id = ?";
        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int filasAfectadas = stmt.executeUpdate(); // Guarda el numero de filas afectadas
            return filasAfectadas > 0;


           /* if (filasAfectadas > 0) {   // Verifica si se eliminó al menos una fila
                return true; // Si sí, devuelve true
            } else {
                return false; // Si no, devuelve false (por ejemplo, si el ID no existe)
            }*/

        } catch (SQLException e) {
            throw e;
        }
    }
    }




