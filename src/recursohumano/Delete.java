
package recursohumano;

import java.sql.SQLException;    // Libreria importada
import java.util.Scanner;        // Libreria importada

public class Delete {
    Delete() throws SQLException {
        Scanner leer = new Scanner(System.in);
        ConexionCRUD utilerias = new ConexionCRUD();
        System.out.println(" << ELIMINAR REGISTROS >> ");
        
        System.out.println("Ingresa el id del registro: ");
        String idContactoEliminar = leer.next();
        // Registro de datos para eliminar 
        String tabla = "tb_contacto";
        String campos = "*";
        String condicion = "id_contacto = " + idContactoEliminar;
        utilerias.desplegarRegistros(tabla, campos, condicion);
        
        System.out.println("Presionar << Y >> para confirmar: ");
        String confirmarBorrar = leer.next();
        
        if ("Y".equals(confirmarBorrar)){
            /*Se le deja vacio para el método actualizarEliminarRegistros
            envie solamente los parámetros de la tabla y condición y poder eliminar*/
            String valoresCamposNuevos = "";
            
            utilerias.actualizarEliminarRegistros(tabla, valoresCamposNuevos, condicion);
            System.out.println("Registro borrado satisfactoriamente!");
        }
        MenuPrincipal.desplegarMenu();
    }
}