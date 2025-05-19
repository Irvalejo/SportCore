public class Equipo {
    private int id;
    private String nombre;
    private int categoriaID;

    public Equipo() {}

    public Equipo(String nombre, int categoriaID) {
        this.nombre = nombre;
        this.categoriaID = categoriaID;
    }


    public Equipo(int id, String nombre, int categoriaID) {
        this.id = id;
        this.nombre = nombre;
        this.categoriaID = categoriaID;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getCategoriaID() { return categoriaID; }
    public void setCategoriaID(int categoriaID) { this.categoriaID = categoriaID; }
}

