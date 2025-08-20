import java.sql.SQLException;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {
        //Conexion a la BD
        DBConnection db = new DBConnection();
       /* db.abrirConexion();
        db.cerrarConexion();
        */
    // Fin de conexion a la BD
//prueba
                ManagerDAO managerDAO = new ManagerDAO();
                JugadorDAO jugadorDAO = new JugadorDAO();
                EquipoDAO  equipoDAO   = new EquipoDAO();
                LigaDAO ligaDAO = new LigaDAO();
                ArbitroDAO arbDAO = new ArbitroDAO();
                CategoriaDAO categoriaDAO = new CategoriaDAO();
                Scanner sc = new Scanner(System.in);

                while (true) {
                    System.out.println("1.  Registrar jugador");
                    System.out.println("2.  Ver jugadores por equipo");
                    System.out.println("3.  Registrar equipo");
                    System.out.println("4.  Registrar categoría");
                    System.out.println("5.  Registrar liga");
                    System.out.println("6.  Ver ligas");
                    System.out.println("7.  Ver categorías por liga");
                    System.out.println("8.  Ver equipos por categoría");
                    System.out.println("9.  Editar jugador");
                    System.out.println("10. Buscar jugador por nombre o apellido");
                    System.out.println("11. Eliminar jugador");
                    System.out.println("12. Registrar manager");
                    System.out.println("13. Ver Manager");
                    System.out.println("14. Editar manager");
                    System.out.println("15. Editar Equipo");
                    System.out.println("16. Eliminar equipo");
                    System.out.println("17. Editrar Liga");
                    System.out.println("18. Eliminar Liga");
                    System.out.println("19. Eliminar Manager");
                    System.out.println("20. Editar Categoria");
                    System.out.println("21. Eliminar Categoria");
                    System.out.println("22. Registrar árbitro");
                    System.out.println("23. Listar árbitros");
                    System.out.println("24. Buscar árbitro por ID");
                    System.out.println("25. Actualizar árbitro");
                    System.out.println("26. Eliminar árbitro");
                    System.out.println("0. Salir");
                    int opcion = sc.nextInt();
                    sc.nextLine(); // limpiar buffer

                    switch (opcion) {
                        case 1:
                            Jugador j = new Jugador();
                            System.out.print("Nombre: ");
                            j.setNombre(sc.nextLine());
                            System.out.print("Apellido: ");
                            j.setApellido(sc.nextLine());
                            System.out.print("Fecha de Nacimiento (YYYY-MM-DD): ");
                            String fechaNacimientoStr = sc.nextLine(); //leer la fecha como string
                            LocalDate fechaNacimiento = null; // fecha inicia vacia
                                try{
                                    fechaNacimiento = LocalDate.parse(fechaNacimientoStr); // convierte a localdate
                                    j.setFechaNacimiento(fechaNacimiento);
                                } catch (DateTimeParseException e){
                                    System.out.println("Formato de fecha incorrecto");
                                }
                            System.out.print("Correo: ");
                            j.setCorreo(sc.nextLine());
                            System.out.print("Ruta de foto: ");
                            j.setFoto(sc.nextLine());
                            System.out.print("ID del equipo: ");
                            j.setEquipoID(sc.nextInt());
                            try {
                                jugadorDAO.registrarJugador(j);
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            System.out.println("Jugador registrado.");
                            break;

                        case 2:
                            System.out.print("ID del equipo: ");
                            int equipoID = sc.nextInt();
                            List<Jugador> jugadores = null;
                            try {
                                jugadores = jugadorDAO.listarJugadoresPorEquipo(equipoID);
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            ConsolePrinter.imprimirJugadores(jugadores);
                            System.out.println("");

                            break;

                        case 3:
                            System.out.print("Nombre del equipo: ");
                            String nombreEquipo = sc.nextLine();

                            System.out.print("ID de la categoría: ");
                            int categoriaID = sc.nextInt();
                            sc.nextLine();  // limpiar buffer

                            Equipo equipo = new Equipo();
                            equipo.setNombre(nombreEquipo);
                            equipo.setCategoriaID(categoriaID);

                            try {
                                equipoDAO.registrarEquipo(equipo);
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            break;

                        case 4:
                            System.out.print("Nombre de la categoría: ");
                            String nombreCategoria = sc.nextLine();

                            System.out.print("Restricción edad mínima: ");
                            int edadMin = sc.nextInt();

                            System.out.print("Restricción edad máxima: ");
                            int edadMax = sc.nextInt();

                            System.out.print("ID de la liga: ");
                            int ligaID = sc.nextInt();
                            sc.nextLine();  // limpiar buffer

                            Categoria categoria = new Categoria();
                            categoria.setNombre(nombreCategoria);
                            categoria.setRestriccionEdadMin(edadMin);
                            categoria.setRestriccionEdadMax(edadMax);
                            categoria.setLigaID(ligaID);


                            try {
                                categoriaDAO.registrarCategoria(categoria);
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            break;


                        case 5:
                            System.out.print("Nombre de la liga: ");
                            String nombreLiga = sc.nextLine();

                            System.out.print("Año de la liga: ");
                            int añoLiga = sc.nextInt();
                            sc.nextLine();  // limpiar buffer

                            Liga liga = new Liga();
                            liga.setNombre(nombreLiga);
                            liga.setAño(añoLiga);

                            try {
                                ligaDAO.registrarLiga(liga);
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            break;

                        case 6:
                            LigaDAO daoLiga = new LigaDAO();
                            List<Liga> ligas = null;
                            try {
                                ligas = daoLiga.listarLigas();
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            ConsolePrinter.imprimirLigas(ligas);
                            break;

                        case 7:
                            System.out.print("ID de la liga: ");
                            int idLiga = sc.nextInt();
                            sc.nextLine();

                            CategoriaDAO catDAO = new CategoriaDAO();
                            List<Categoria> categoriasPorLiga = null;
                            try {
                                categoriasPorLiga = catDAO.listarCategoriasPorLiga(idLiga);
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            ConsolePrinter.imprimirCategorias(categoriasPorLiga);
                            break;

                        case 8:
                            System.out.print("ID de la categoría: ");
                            int idCat = sc.nextInt();
                            sc.nextLine();

                            EquipoDAO eqDAO = new EquipoDAO();
                            List<Equipo> equiposPorCat = null;
                            try {
                                equiposPorCat = eqDAO.listarEquiposPorCategoria(idCat);
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            ConsolePrinter.imprimirEquipos(equiposPorCat);
                            break;

                        case 9:
                            System.out.print("ID del jugador a editar: ");
                            int idJugador = sc.nextInt();
                            sc.nextLine();

                            Jugador jugadorExistente = null;
                            try {
                                jugadorExistente = jugadorDAO.obtenerJugadorPorID(idJugador);
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            if (jugadorExistente == null) {
                                System.out.println("Jugador no encontrado.");
                                break;
                            }

                            System.out.println("Nombre actual: " + jugadorExistente.getNombre());
                            System.out.print("Nuevo nombre (dejar vacío para mantener): ");
                            String nuevoNombre = sc.nextLine();
                            if (!nuevoNombre.isEmpty()) jugadorExistente.setNombre(nuevoNombre);

                            System.out.println("Apellido actual: " + jugadorExistente.getApellido());
                            System.out.print("Nuevo apellido (dejar vacío para mantener): ");
                            String nuevoApellido = sc.nextLine();
                            if (!nuevoApellido.isEmpty()) jugadorExistente.setApellido(nuevoApellido);

                            System.out.println("Correo actual: " + jugadorExistente.getCorreo());
                            System.out.print("Nuevo correo (dejar vacío para mantener): ");
                            String nuevoCorreo = sc.nextLine();
                            if (!nuevoCorreo.isEmpty()) jugadorExistente.setCorreo(nuevoCorreo);


                            String fechaActualStr = (jugadorExistente.getFechaNacimiento() !=null) ?
                                                     jugadorExistente.getFechaNacimiento().toString() : "No especificada";
                            System.out.println("Fecha de nacimiento actual: " + fechaActualStr );
                            System.out.println("Nueva fecha de nacimiento (YYYY-MM-DD, vacío para mantener): ");
                            String nuevaFechaStr = sc.nextLine();
                            if(!nuevaFechaStr.isEmpty()){
                                try{
                                LocalDate nuevaFechaLD = LocalDate.parse(nuevaFechaStr);  // convierte string a fecha
                                jugadorExistente.setFechaNacimiento(nuevaFechaLD); // asigna la fecha al jugador
                                } catch (DateTimeParseException e){
                                    System.out.println("Formato de fecha incorrecto");
                                }
                            }

                            System.out.println("Foto actual: " + jugadorExistente.getFoto());
                            System.out.print("Nueva ruta de foto (vacío para mantener): ");
                            String nuevaFoto = sc.nextLine();
                            if (!nuevaFoto.isEmpty()) jugadorExistente.setFoto(nuevaFoto);

                            System.out.println("Equipo actual ID: " + jugadorExistente.getEquipoID());
                            System.out.print("Nuevo ID de equipo (0 para mantener): ");
                            int nuevoEquipoID = sc.nextInt();
                            sc.nextLine();
                            if (nuevoEquipoID != 0) jugadorExistente.setEquipoID(nuevoEquipoID);

                            try {
                                jugadorDAO.actualizarJugador(jugadorExistente);
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            System.out.println("Jugador actualizado.");
                            break;

                        case 10:
                            System.out.print("Nombre o apellido a buscar: ");
                            String termino = sc.nextLine();
                            List<Jugador> resultados = null;
                            try {
                                resultados = jugadorDAO.buscarJugadoresPorNombre(termino);
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            if (resultados.isEmpty()) {
                                System.out.println("No se encontraron jugadores.");
                            } else {
                                for (Jugador jugador : resultados) {
                                    ConsolePrinter.imprimirJugador(jugador);
                                }
                            }
                            break;

                        case 11:
                            System.out.print("ID del jugador a eliminar: ");
                            int idEliminar = sc.nextInt();
                            sc.nextLine(); // limpiar buffer

                            boolean eliminado = false;
                            try {
                                eliminado = jugadorDAO.eliminarJugador(idEliminar);
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            if (eliminado) {
                                System.out.println("Jugador eliminado correctamente.");
                            } else {
                                System.out.println("No se pudo eliminar el jugador. Verifica el ID.");
                            }
                            break;

                        case 12:
                            Manager m = new Manager();
                            System.out.print("Nombre Completo: ");
                            m.setNombreCompleto(sc.nextLine());
                           System.out.print("Fecha de Nacimiento (YYYY-MM-DD): ");
                            String fechaNacimientoMStr = sc.nextLine(); //leer la fecha como string
                            LocalDate fechaNacimientoM = null; // fecha inicia vacia
                            try{
                                fechaNacimientoM = LocalDate.parse(fechaNacimientoMStr); // convierte a localdate
                                m.setFechaNacimiento(fechaNacimientoM);
                            } catch (DateTimeParseException e){
                                System.out.println("Formato de fecha incorrecto");
                            }


                            System.out.print("Correo: ");
                            m.setCorreo(sc.nextLine());
                            System.out.print("Telefono: ");
                            m.setTelefono(sc.nextLine());
                            System.out.print("ID del equipo: ");
                            m.setEquipoID(sc.nextInt());
                            try {
                                managerDAO.registrarManager(m);
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            System.out.println("Manager registrado.");
                            break;

                        case 13:
                            ManagerDAO manager = new ManagerDAO();
                            System.out.print("ID del equipo: ");
                            equipoID = sc.nextInt();
                            List<Manager> managers = null;
                            try {
                                managers = manager.listarManagerPorEquipo(equipoID);
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            ConsolePrinter.imprimirManager(managers);
                            break;

                        case 14:
                            System.out.print("ID del manager a editar: ");
                            int idmanager  = sc.nextInt();
                            sc.nextLine();

                            Manager managerExistente = null;
                            try {
                                managerExistente = managerDAO.obtenerManagerPorID(idmanager);
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            if ( managerExistente == null) {
                                System.out.println("Manager no encontrado.");
                                break;
                            }
                            System.out.println("Nombre actual: " + managerExistente.getNombreCompleto());
                            System.out.print("Nuevo nombre (dejar vacío para mantener): ");
                            String nuevoNombreCompleto = sc.nextLine();
                            if (!nuevoNombreCompleto.isEmpty()) managerExistente.setNombreCompleto(nuevoNombreCompleto);

                            System.out.println("Fecha de nacimiento actual: " + managerExistente.getFechaNacimiento());
                            System.out.print("Nueva fecha de nacimiento (YYYY-MM-DD, vacío para mantener): ");
                            String nuevaFechaMStr = sc.nextLine();
                            if(!nuevaFechaMStr.isEmpty()){
                                try{
                                    LocalDate nuevaFechaLD = LocalDate.parse(nuevaFechaMStr);  // convierte string a fecha
                                    managerExistente.setFechaNacimiento(nuevaFechaLD); // asigna la fecha al manager
                                } catch (DateTimeParseException e){
                                    System.out.println("Formato de fecha incorrecto");
                                }
                            }

                            System.out.println("Correo actual: " + managerExistente.getCorreo());
                            System.out.print("Nuevo correo (dejar vacío para mantener): ");
                            String nuevoCorreoM = sc.nextLine();
                            if (!nuevoCorreoM.isEmpty()) managerExistente.setCorreo(nuevoCorreoM);

                            System.out.println("Telefono actual: " + managerExistente.getTelefono());
                            System.out.print("Nuevo telefono (dejar vacío para mantener): ");
                            String nuevoTelefono = sc.nextLine();
                            if (!nuevoTelefono.isEmpty()) managerExistente.setTelefono(nuevoTelefono);

                            System.out.println("Equipo actual ID: " + managerExistente.getEquipoID());
                            System.out.print("Nuevo ID de equipo (0 para mantener): ");
                            int nuevoEquipoIDM = sc.nextInt();
                            sc.nextLine();
                            if (nuevoEquipoIDM != 0) managerExistente.setEquipoID(nuevoEquipoIDM);

                            try {
                                managerDAO.actualizarManager(managerExistente);
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            System.out.println("Manager actualizado.");
                            break;

                        case 15:
                            System.out.println("ID del equipo a editar: ");
                            int idequipo  = sc.nextInt();
                            sc.nextLine();
                            Equipo equipoExistente = null;
                            try {
                                equipoExistente = equipoDAO.obtenerEquipoPorID(idequipo);
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            if ( equipoExistente == null) {
                                System.out.println("Equipo no encontrado.");
                                break;
                            }
                            System.out.println("Nombre actual: " + equipoExistente.getNombre());
                            System.out.print("Nuevo nombre (dejar vacío para mantener): ");
                            String nuevoNombreE = sc.nextLine();
                            if (!nuevoNombreE.isEmpty()) equipoExistente.setNombre(nuevoNombreE);

                            try {
                                equipoDAO.actualizarEquipo(equipoExistente);
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            System.out.println("Equipo actualizado.");
                            break;

                        case 16:
                            System.out.printf("Id del equipo a eliminar: ");
                            int idEliminarE = sc.nextInt();
                            sc.nextLine(); // limpiar buffer

                            boolean eliminadoE = false;
                            try {
                                eliminadoE = equipoDAO.eliminarEquipo(idEliminarE);
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            if (eliminadoE) {
                                System.out.println("Equipo eliminado correctamente.");
                            } else {
                                System.out.println("No se pudo eliminar el equipo. Verifica el ID.");
                            }
                            break;

                        case 17:
                            System.out.println("Editar Liga");
                            System.out.print("ID de la liga a editar: ");
                            int idLigaM = sc.nextInt();
                            sc.nextLine();
                            Liga ligaExistente = null;
                            try {
                                ligaExistente = ligaDAO.obtenerLigaPorID(idLigaM);
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            if (ligaExistente == null) {
                                System.out.println("Liga no encontrada.");
                                break;
                            }

                            System.out.println("Nombre actual: " + ligaExistente.getNombre());
                            System.out.print("Nuevo nombre (dejar vacío para mantener): ");
                            String nuevoNombreL = sc.nextLine();
                            if (!nuevoNombreL.isEmpty()) ligaExistente.setNombre(nuevoNombreL);
                            System.out.println("Año actual: " + ligaExistente.getAño());
                            System.out.print("Nuevo anio (0 para mantener): ");
                            int nuevoAnio = sc.nextInt();
                            if (nuevoAnio != 0) ligaExistente.setAño(nuevoAnio);
                            try {
                                ligaDAO.actualizarLiga(ligaExistente);
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            System.out.println("Liga actualizada.");
                            break;

                            case 18:
                                System.out.print("ID de la liga a eliminar: ");
                                int idEliminarL = sc.nextInt();
                                sc.nextLine(); // limpiar buffer

                                boolean eliminadoL = false;
                                try {
                                    eliminadoL = ligaDAO.eliminarLiga(idEliminarL);
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                                if (eliminadoL) {
                                    System.out.println("liga eliminada correctamente.");
                                } else {
                                    System.out.println("No se pudo eliminar la liga. Verifica el ID.");
                                }
                                break;

                            case 19:
                                System.out.println("Eliminar Manager");
                                System.out.print("ID del manager a eliminar: ");
                                int idEliminarM = sc.nextInt();
                                sc.nextLine(); // limpiar buffer

                                boolean eliminadoM = false;
                                try {
                                    eliminadoM = managerDAO.eliminarManager(idEliminarM);
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                                if (eliminadoM) {
                                    System.out.println("Manager eliminado correctamente.");
                                } else {
                                    System.out.println("No se pudo eliminar al Manager. Verifica el ID.");
                                }
                                break;

                            case 20:
                                System.out.print("ID de la categoria a editar: ");
                                int idCategoria = sc.nextInt();
                                sc.nextLine();
                                Categoria categoriaExistente = null;
                                try {
                                    categoriaExistente = categoriaDAO.obtenerCategoriaPorId(idCategoria);
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                                if (categoriaExistente == null) {
                                    System.out.println("Categoria no encontrada.");
                                    break;
                                }
                                System.out.println("Nombre actual: " + categoriaExistente.getNombre());
                                System.out.print("Nuevo nombre (dejar vacío para mantener): ");
                                String nuevoNombreC = sc.nextLine();
                                if (!nuevoNombreC.isEmpty()) categoriaExistente.setNombre(nuevoNombreC);
                                System.out.println("Restriccion Edad Minima actual: " + categoriaExistente.getRestriccionEdadMin());
                                System.out.print("Nueva Restriccion Edad Minima (0 para mantener): ");
                                int nuevaRestriccionEdadMin = sc.nextInt();
                                if (nuevaRestriccionEdadMin != 0) categoriaExistente.setRestriccionEdadMin(nuevaRestriccionEdadMin);
                                System.out.println("Restriccion Edad Maxima actual: " + categoriaExistente.getRestriccionEdadMax());
                                System.out.print("Nueva Restriccion Edad Maxima (0 para mantener): ");
                                int nuevaRestriccionEdadMax = sc.nextInt();
                                if (nuevaRestriccionEdadMax != 0) categoriaExistente.setRestriccionEdadMax(nuevaRestriccionEdadMax);
                                try {
                                    categoriaDAO.actualizarCategoria(categoriaExistente);
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                                System.out.println("Categoria actualizada.");
                                break;

                            case 21:
                                System.out.print("ID de la categoria a eliminar: ");
                                int idEliminarC = sc.nextInt();
                                sc.nextLine(); // limpiar buffer

                                boolean eliminadoC = false;
                                try {
                                    eliminadoC = categoriaDAO.eliminarCategoria(idEliminarC);
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                                if (eliminadoC) {
                                    System.out.println("Categoria eliminada correctamente.");
                                } else {
                                    System.out.println("No se pudo eliminar la categoria. Verifica el ID.");
                                }
                                break;

                        case 22: // Registrar árbitro
                            System.out.println("=== Registrar Árbitro ===");
                            System.out.print("Nombre: ");
                            String nombreA = sc.nextLine();
                            System.out.print("Apellido paterno: ");
                            String apPatA = sc.nextLine();
                            System.out.print("Apellido materno: ");
                            String apMatA = sc.nextLine();
                            System.out.print("Fecha nacimiento (YYYY-MM-DD): ");
                            LocalDate fnA = LocalDate.parse(sc.nextLine());
                            System.out.print("Correo: ");
                            String correoA = sc.nextLine();
                            System.out.print("Teléfono: ");
                            String telA = sc.nextLine();

                            Arbitro nuevoArbitro = new Arbitro(nombreA, apPatA, apMatA, fnA, correoA, telA);
                            ArbitroDAO arbitroDAO = new ArbitroDAO();
                            try {
                                arbitroDAO.registrarArbitro(nuevoArbitro);
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            System.out.println("Árbitro registrado con ID: " + nuevoArbitro.getId());
                            break;

                        case 23: // Listar árbitros
                            System.out.println("=== Lista de Árbitros ===");
                            try {
                                for (Arbitro a : new ArbitroDAO().listarArbitros()) {
                                    System.out.println(a.getId() + " - " + a.getNombre() + " " + a.getApellidoPaterno() + " " + a.getApellidoMaterno());
                                }
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            break;

                        case 24: // Obtener árbitro por ID
                            System.out.print("Ingrese ID del árbitro: ");
                            int idArbitro = sc.nextInt(); sc.nextLine();
                            Arbitro buscado = null;
                            try {
                                buscado = new ArbitroDAO().obtenerArbitroPorId(idArbitro);
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            if (buscado != null) {
                                System.out.println(buscado.getId() + " - " + buscado.getNombre() + " " + buscado.getApellidoPaterno() +
                                        " " + buscado.getApellidoMaterno() + ", correo: " + buscado.getCorreo() + ", tel: " + buscado.getTelefono());
                            } else {
                                System.out.println("No se encontró el árbitro con ID " + idArbitro);
                            }
                            break;

                        case 25: // Actualizar árbitro
                            System.out.print("ID del árbitro a actualizar: ");
                            int idUp = sc.nextInt(); sc.nextLine();

                            Arbitro arbExist = null;
                            try {
                                arbExist = arbDAO.obtenerArbitroPorId(idUp);
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }

                            if (arbExist != null) {
                                System.out.println("Nuevo nombre (actual: " + arbExist.getNombre() + "): ");
                                arbExist.setNombre(sc.nextLine());
                                System.out.println("Nuevo apellido paterno (actual: " + arbExist.getApellidoPaterno() + "): ");
                                arbExist.setApellidoPaterno(sc.nextLine());
                                System.out.println("Nuevo apellido materno (actual: " + arbExist.getApellidoMaterno() + "): ");
                                arbExist.setApellidoMaterno(sc.nextLine());
                                System.out.println("Nueva fecha nacimiento (YYYY-MM-DD, actual: " + arbExist.getFechaNacimiento() + "): ");
                                arbExist.setFechaNacimiento(LocalDate.parse(sc.nextLine()));
                                System.out.println("Nuevo correo (actual: " + arbExist.getCorreo() + "): ");
                                arbExist.setCorreo(sc.nextLine());
                                System.out.println("Nuevo teléfono (actual: " + arbExist.getTelefono() + "): ");
                                arbExist.setTelefono(sc.nextLine());

                                try {
                                    arbDAO.actualizarArbitro(arbExist);
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                                System.out.println("Árbitro actualizado correctamente.");
                            } else {
                                System.out.println("No existe árbitro con ese ID.");
                            }
                            break;

                        case 26: // Eliminar árbitro
                            System.out.print("ID del árbitro a eliminar: ");
                            int idDel = sc.nextInt(); sc.nextLine();
                            try {
                                if (new ArbitroDAO().eliminarArbitro(idDel)) {
                                    System.out.println("Árbitro eliminado con éxito.");
                                } else {
                                    System.out.println("No se encontró árbitro con ese ID.");
                                }
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            break;

                        case 0:
                            System.out.println("Saliendo...");
                            sc.close();
                            System.out.println("Programa finalizado.");
                            System.out.printf("Gracias por usar el programa.%n");
                            System.exit(0);
                        default:
                            throw new IllegalStateException("Unexpected value: " + opcion);
                    }
                }
            }
        }