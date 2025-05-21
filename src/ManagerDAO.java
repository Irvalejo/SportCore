import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerDAO {

    public void registrarManager(Manager manager) {
        DBConnection db = new DBConnection();
        db.abrirConexion();
        Connection conn = db.getConnection();

        if (conn == null) {
            System.out.println("Conexión fallida. No se pudo registrar al manager.");
            return;
        }
    String sql = "INSERT INTO manager (ID,NombreCompleto, FechaNacimiento,Correo,Telefono,EquipoID ) VALUES (?, ?, ?, ?, ?, ?)";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, manager.getId());
        stmt.setString(2, manager.getNombreCompleto());
        stmt.setString(3, manager.getFechaNacimiento());
        stmt.setString(4, manager.getCorreo());
        stmt.setString(5, manager.getTelefono());
        stmt.setInt(6, manager.getEquipoID());
        stmt.executeUpdate();
    } catch (SQLException e) {
        System.out.println("Error al registrar manager: " + e.getMessage());
    } finally {
        db.cerrarConexion();
    }
}
    public List<Manager> listarManagerPorEquipo(int equipoID) {
        List<Manager> manager = new ArrayList<>();
        DBConnection db = new DBConnection();
        db.abrirConexion();
        Connection conn = db.getConnection();

        if (conn == null) {
            System.out.println("Conexión fallida. No se pudo listar Manager.");
            return manager;
        }

        String sql = "SELECT * FROM manager WHERE EquipoID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, equipoID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Manager m = new Manager(
                        rs.getInt("ID"),
                        rs.getString("NombreCompleto"),
                        rs.getString("FechaNacimiento"),
                        rs.getString("Correo"),
                        rs.getString("Telefono"),
                        rs.getInt("EquipoID")
                );

                manager.add(m);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar manager: " + e.getMessage());
        } finally {
            db.cerrarConexion();
        }

        return manager;
    }
    public void actualizarManager(Manager manager) {
        String sql = "UPDATE manager SET NombreCompleto = ?, FechaNacimiento = ?, Correo = ?, Telefono = ?, EquipoID = ? WHERE ID = ?";
        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, manager.getNombreCompleto());
            stmt.setString(3, manager.getFechaNacimiento());
            stmt.setString(4, manager.getCorreo());
            stmt.setString(5, manager.getTelefono());
            stmt.setInt(6, manager.getEquipoID());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al actualizar Manager: " + e.getMessage());
        }
    }

    public Manager obtenerManagerPorID(int id) {
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
                       rs.getString("FechaNacimiento"),
                       rs.getString("Telefono"),
                       rs.getInt("EquipoID")
               );
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener mamager: " + e.getMessage());
        }
        return null;
    }



        }
