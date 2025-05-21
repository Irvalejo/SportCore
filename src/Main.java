import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        //Conexion a la BD
        DBConnection db = new DBConnection();
        db.abrirConexion();
        db.cerrarConexion();
    // Fin de conexion a la BD

                ManagerDAO managerDAO = new ManagerDAO();
                JugadorDAO jugadorDAO = new JugadorDAO();
                Scanner sc = new Scanner(System.in);

                while (true) {
                    System.out.println("1. Registrar jugador");
                    System.out.println("2. Ver jugadores por equipo");
                    System.out.println("3. Registrar equipo");
                    System.out.println("4. Registrar categoría");
                    System.out.println("5. Registrar liga");
                    System.out.println("6. Ver ligas");
                    System.out.println("7. Ver categorías por liga");
                    System.out.println("8. Ver equipos por categoría");
                    System.out.println("9. Editar jugador");
                    System.out.println("10. Buscar jugador por nombre o apellido");
                    System.out.println("11. Eliminar jugador");
                    System.out.println("12. Registrar manager");
                    System.out.println("13. Ver Manager");
                    System.out.println("14.Editar manager");
                    System.out.println();


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
                            j.setFechaNacimiento(sc.nextLine());
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

                            EquipoDAO equipoDAO = new EquipoDAO();
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

                            CategoriaDAO categoriaDAO = new CategoriaDAO();
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

                            LigaDAO ligaDAO = new LigaDAO();
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

                            System.out.println("Fecha de nacimiento actual: " + jugadorExistente.getFechaNacimiento());
                            System.out.print("Nueva fecha de nacimiento (YYYY-MM-DD, vacío para mantener): ");
                            String nuevaFecha = sc.nextLine();
                            if (!nuevaFecha.isEmpty()) jugadorExistente.setFechaNacimiento(nuevaFecha);

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
                            m.setFechaNacimiento(sc.nextLine());
                            System.out.print("Correo: ");
                            m.setCorreo(sc.nextLine());
                            System.out.println("Telefono: ");
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
                            String nuevaFechaM = sc.nextLine();
                            if (!nuevaFechaM.isEmpty()) managerExistente.setFechaNacimiento(nuevaFechaM);

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


                        case 0:
                            System.exit(0);
                    }
                }
            }
        }