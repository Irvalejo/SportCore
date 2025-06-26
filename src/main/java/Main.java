import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {
        //Conexion a la BD
        DBConnection db = new DBConnection();
        db.abrirConexion();
        db.cerrarConexion();
    // Fin de conexion a la BD
//prueba
                ManagerDAO managerDAO = new ManagerDAO();
                JugadorDAO jugadorDAO = new JugadorDAO();
                EquipoDAO  equipoDAO   = new EquipoDAO();
                LigaDAO ligaDAO = new LigaDAO();
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
                            jugadorDAO.registrarJugador(j);
                            System.out.println("Jugador registrado.");
                            break;

                        case 2:
                            System.out.print("ID del equipo: ");
                            int equipoID = sc.nextInt();
                            List<Jugador> jugadores = jugadorDAO.listarJugadoresPorEquipo(equipoID);
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

                            equipoDAO.registrarEquipo(equipo);
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


                            categoriaDAO.registrarCategoria(categoria);
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

                            ligaDAO.registrarLiga(liga);
                            break;

                        case 6:
                            LigaDAO daoLiga = new LigaDAO();
                            List<Liga> ligas = daoLiga.listarLigas();
                            ConsolePrinter.imprimirLigas(ligas);
                            break;

                        case 7:
                            System.out.print("ID de la liga: ");
                            int idLiga = sc.nextInt();
                            sc.nextLine();

                            CategoriaDAO catDAO = new CategoriaDAO();
                            List<Categoria> categoriasPorLiga = catDAO.listarCategoriasPorLiga(idLiga);
                            ConsolePrinter.imprimirCategorias(categoriasPorLiga);
                            break;

                        case 8:
                            System.out.print("ID de la categoría: ");
                            int idCat = sc.nextInt();
                            sc.nextLine();

                            EquipoDAO eqDAO = new EquipoDAO();
                            List<Equipo> equiposPorCat = eqDAO.listarEquiposPorCategoria(idCat);
                            ConsolePrinter.imprimirEquipos(equiposPorCat);
                            break;

                        case 9:
                            System.out.print("ID del jugador a editar: ");
                            int idJugador = sc.nextInt();
                            sc.nextLine();

                            Jugador jugadorExistente = jugadorDAO.obtenerJugadorPorID(idJugador);
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

                            jugadorDAO.actualizarJugador(jugadorExistente);
                            System.out.println("Jugador actualizado.");
                            break;

                        case 10:
                            System.out.print("Nombre o apellido a buscar: ");
                            String termino = sc.nextLine();
                            List<Jugador> resultados = jugadorDAO.buscarJugadoresPorNombre(termino);
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

                            boolean eliminado = jugadorDAO.eliminarJugador(idEliminar);
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
                            managerDAO.registrarManager(m);
                            System.out.println("Manager registrado.");
                            break;

                        case 13:
                            ManagerDAO manager = new ManagerDAO();
                            System.out.print("ID del equipo: ");
                            equipoID = sc.nextInt();
                            List<Manager> managers = manager.listarManagerPorEquipo(equipoID);
                            ConsolePrinter.imprimirManager(managers);
                            break;

                        case 14:
                            System.out.print("ID del manager a editar: ");
                            int idmanager  = sc.nextInt();
                            sc.nextLine();

                            Manager managerExistente = managerDAO.obtenerManagerPorID(idmanager);
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

                            managerDAO.actualizarManager(managerExistente);
                            System.out.println("Manager actualizado.");
                            break;

                        case 15:
                            System.out.println("ID del equipo a editar: ");
                            int idequipo  = sc.nextInt();
                            sc.nextLine();
                            Equipo equipoExistente = equipoDAO.obtenerEquipoPorID(idequipo);
                            if ( equipoExistente == null) {
                                System.out.println("Equipo no encontrado.");
                                break;
                            }
                            System.out.println("Nombre actual: " + equipoExistente.getNombre());
                            System.out.print("Nuevo nombre (dejar vacío para mantener): ");
                            String nuevoNombreE = sc.nextLine();
                            if (!nuevoNombreE.isEmpty()) equipoExistente.setNombre(nuevoNombreE);

                            equipoDAO.actualizarEquipo(equipoExistente);
                            System.out.println("Equipo actualizado.");
                            break;

                        case 16:
                            System.out.printf("Id del equipo a eliminar: ");
                            int idEliminarE = sc.nextInt();
                            sc.nextLine(); // limpiar buffer

                            boolean eliminadoE = equipoDAO.eliminarEquipo(idEliminarE);
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
                            Liga ligaExistente = ligaDAO.obtenerLigaPorID(idLigaM);
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
                            ligaDAO.actualizarLiga(ligaExistente);
                            System.out.println("Liga actualizada.");
                            break;

                            case 18:
                                System.out.print("ID de la liga a eliminar: ");
                                int idEliminarL = sc.nextInt();
                                sc.nextLine(); // limpiar buffer

                                boolean eliminadoL = ligaDAO.eliminarLiga(idEliminarL);
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

                                boolean eliminadoM = managerDAO.eliminarManager(idEliminarM);
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
                                Categoria categoriaExistente = categoriaDAO.obtenerCategoriaPorId(idCategoria);
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
                                categoriaDAO.actualizarCategoria(categoriaExistente);
                                System.out.println("Categoria actualizada.");
                                break;

                            case 21:
                                System.out.print("ID de la categoria a eliminar: ");
                                int idEliminarC = sc.nextInt();
                                sc.nextLine(); // limpiar buffer

                                boolean eliminadoC = categoriaDAO.eliminarCategoria(idEliminarC);
                                if (eliminadoC) {
                                    System.out.println("Categoria eliminada correctamente.");
                                } else {
                                    System.out.println("No se pudo eliminar la categoria. Verifica el ID.");
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