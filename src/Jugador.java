import java.time.LocalDate;

public class Jugador {
    private int id;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String correo;
    private String foto;
    private int equipoID;

    // Constructor, getters y setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getEquipoID() {
        return equipoID;
    }

    public void setEquipoID(int equipoID) {
        this.equipoID = equipoID;
    }

    public Jugador() {}

    public Jugador(int id, String nombre, String apellido, LocalDate fechaNacimiento, String correo, String foto, int equipoID) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.correo = correo;
        this.foto = foto;
        this.equipoID = equipoID;
    }
}
