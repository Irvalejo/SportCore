import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        //Conexion a la BD
        DBConnection db = new DBConnection();
        db.abrirConexion();
        db.cerrarConexion();
    // Fin de conexion a la BD

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

                        case 0:
                            System.exit(0);
                    }
                }
            }
        }


