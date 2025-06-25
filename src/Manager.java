import java.time.LocalDate;

public class Manager {
    private int id;
    private String nombreCompleto;
    private String correo;
    private LocalDate FechaNacimiento;
    private String telefono;
    private int equipoID;

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombre) {
        this.nombreCompleto = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public LocalDate getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate FechaNacimiento) {
        this.FechaNacimiento = FechaNacimiento;
    }

    public int getEquipoID() {
        return equipoID;
    }

    public void setEquipoID(int equipoID) {
        this.equipoID = equipoID;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Manager(int id,String nombreCompleto, String correo, LocalDate FechaNacimiento, String telefono, int equipoID) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.FechaNacimiento = FechaNacimiento;
        this.telefono = telefono;
        this.equipoID = equipoID;

    }

    public Manager() {

    }
}
