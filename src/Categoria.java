public class Categoria {
    private int id;
    private String nombre;
    private Integer restriccionEdadMin; // Usa Integer para nullable
    private Integer restriccionEdadMax;
    private int ligaID;

    public Categoria() {}

    public Categoria(int id, String nombre, Integer restriccionEdadMin, Integer restriccionEdadMax, int ligaID) {
        this.id = id;
        this.nombre = nombre;
        this.restriccionEdadMin = restriccionEdadMin;
        this.restriccionEdadMax = restriccionEdadMax;
        this.ligaID = ligaID;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getRestriccionEdadMin() { return restriccionEdadMin; }
    public void setRestriccionEdadMin(Integer restriccionEdadMin) { this.restriccionEdadMin = restriccionEdadMin; }

    public Integer getRestriccionEdadMax() { return restriccionEdadMax; }
    public void setRestriccionEdadMax(Integer restriccionEdadMax) { this.restriccionEdadMax = restriccionEdadMax; }

    public int getLigaID() { return ligaID; }
    public void setLigaID(int ligaID) { this.ligaID = ligaID; }
}



