public class Liga {
    private int id;
    private String nombre;
    private int año;

    public Liga() {}

    public Liga(int id, String nombre, int año) {
        this.id = id;
        this.nombre = nombre;
        this.año = año;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getAño() { return año; }
    public void setAño(int año) { this.año = año; }
}
