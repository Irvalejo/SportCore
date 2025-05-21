import java.util.List;


public class ConsolePrinter {

    public static void imprimirLigas(List<Liga> ligas) {
        System.out.println("╔════════════════════════════╗");
        System.out.println("║          LIGAS            ║");
        System.out.println("╠════╦══════════════════════╣");
        System.out.printf("║ %-2s ║ %-20s ║%n", "ID", "Nombre");
        System.out.println("╠════╬══════════════════════╣");

        for (Liga l : ligas) {
            System.out.printf("║ %-2d ║ %-20s ║%n", l.getId(), l.getNombre());
        }

        System.out.println("╚════╩══════════════════════╝");
    }

    public static void imprimirCategorias(List<Categoria> categorias) {
        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║               CATEGORÍAS                       ║");
        System.out.println("╠════╦════════════════════╦════════╦═════════════╣");
        System.out.printf("║ %-2s ║ %-18s ║ %-6s ║ %-11s ║%n", "ID", "Nombre", "Edad Min", "Edad Max");
        System.out.println("╠════╬════════════════════╬════════╬═════════════╣");

        for (Categoria c : categorias) {
            System.out.printf("║ %-2d ║ %-18s ║ %-6s ║ %-11s ║%n",
                    c.getId(),
                    c.getNombre(),
                    c.getRestriccionEdadMin() != null ? c.getRestriccionEdadMin() : "-",
                    c.getRestriccionEdadMax() != null ? c.getRestriccionEdadMax() : "-");
        }

        System.out.println("╚════╩════════════════════╩════════╩═════════════╝");
    }

    public static void imprimirEquipos(List<Equipo> equipos) {
        System.out.println("╔════════════════════════════╗");
        System.out.println("║         EQUIPOS           ║");
        System.out.println("╠════╦══════════════════════╣");
        System.out.printf("║ %-2s ║ %-20s ║%n", "ID", "Nombre");
        System.out.println("╠════╬══════════════════════╣");

        for (Equipo e : equipos) {
            System.out.printf("║ %-2d ║ %-20s ║%n", e.getId(), e.getNombre());
        }

        System.out.println("╚════╩══════════════════════╝");
    }


    public static void imprimirJugadores(List<Jugador> jugadores) {
        if (jugadores.isEmpty()) {
            System.out.println("No hay jugadores registrados en este equipo.");
            return;
        }

        System.out.println("╔════════╦════════════════════╦════════════════════════════╦═══════════════╦════════════════════════════════════════╗");
        System.out.println("║   ID   ║ Nombre completo    ║ Correo                     ║ Fecha Nac.    ║ Foto                                   ║");
        System.out.println("╠════════╬════════════════════╬════════════════════════════╬═══════════════╬════════════════════════════════════════╣");

        for (Jugador j : jugadores) {
            String nombreCompleto = j.getNombre() + " " + j.getApellido();
            System.out.printf("║ %-6d ║ %-18s ║ %-26s ║ %-13s ║ %-38s ║\n",
                    j.getId(), nombreCompleto, j.getCorreo(), j.getFechaNacimiento(), j.getFoto());
        }

        System.out.println("╚════════╩════════════════════╩════════════════════════════╩═══════════════╩════════════════════════════════════════╝");
    }

        public static void imprimirJugador(Jugador jugador) {
            System.out.println("────────────────────────────────────────");
            System.out.println("ID:              " + jugador.getId());
            System.out.println("Nombre:          " + jugador.getNombre());
            System.out.println("Apellido:        " + jugador.getApellido());
            System.out.println("Fecha Nac.:      " + jugador.getFechaNacimiento());
            System.out.println("Correo:          " + jugador.getCorreo());
            System.out.println("Foto:            " + jugador.getFoto());
            System.out.println("ID del Equipo:   " + jugador.getEquipoID());
            System.out.println("────────────────────────────────────────");
        }

    public static void imprimirManager(List<Manager> manager) {
        if (manager.isEmpty()) {
            System.out.println("No hay managers registrado en este equipo.");
            return;
        }

        System.out.println("╔════════╦════════════════════╦════════════════════════════╦═══════════════╦════════════════════════════════════════╗");
        System.out.println("║   ID   ║ Nombre completo    ║ Correo                     ║ Fecha Nac.    ║ Telefono                               ║");
        System.out.println("╠════════╬════════════════════╬════════════════════════════╬═══════════════╬════════════════════════════════════════╣");

        for (Manager m : manager) {
            String nombreCompleto = m.getNombreCompleto();
            System.out.printf("║ %-6d ║ %-18s ║ %-26s ║ %-13s ║ %-38s ║\n",
                    m.getId(), nombreCompleto, m.getCorreo(), m.getFechaNacimiento(), m.getTelefono());
        }

        System.out.println("╚════════╩════════════════════╩════════════════════════════╩═══════════════╩════════════════════════════════════════╝");
    }

    }



