package recursohumano;

import java.sql.SQLException;    // Libreria para la conexiones con base de datos
import java.util.Scanner;        // Libreria Scanner 


public class MenuPrincipal {
     // throws especifica el tipo de exepcion que puede ocasionar 
    public static void main(String[] args) throws SQLException {
        desplegarMenu();
    }

    public static void desplegarMenu() throws SQLException {
        // Instancia para los metodos predeterminados de JAVA
       Scanner opcionSeleccionada = new Scanner(System.in); 
       String opcionMenu;
       // Menú al desplegar
        System.out.println("**********************************");
        System.out.println("   |  CRUD de JAVA en consola  |  ");
        System.out.println("**********************************");
        System.out.println("Opciones: ");
        System.out.println("   |  1. Crear registros       |    ");
        System.out.println("   |  2. Consultar registros  |   ");
        System.out.println("   |  3. Actualizar registros |    ");
        System.out.println("   |  4. Eliminar registros   |    ");
        System.out.println("   |  5. Salir                 |    ");
        System.out.println("**********************************");
        System.out.println("Seleccionar opciones:             ");
        opcionMenu = opcionSeleccionada.next();
        // Desplegar Menú basado en las opciones seleccionadas
        switch (opcionMenu){
            case "1":
                Create create = new Create();
                break;
            case "2":
                Read read = new Read();
                break;
            case "3":
                Update update = new Update();
                break;
            case "4":
                Delete delete = new Delete();
                break;
            case "5":
                System.exit(0);
                break;
            default:
                System.out.println("Selección invalida!!");
                break;
        }
    }

}