package recursohumano;

import java.sql.SQLException; // Libreria Agregada 
import java.util.Scanner;    // Libreria Importada 

public class Create {
    Create() throws SQLException {
        Scanner leer = new Scanner(System.in);
        Persona person = new Persona(); // Objeto person de la clase Persona
        System.out.println(" << CREAR REGISTROS >>");
        
        System.out.println("Nombre: ");
        person.setNomPersona(leer.nextLine());
        
        System.out.println("Correo electrónico: ");
        person.setEmailPersona(leer.nextLine());
        
        System.out.println("Teléfono: ");
        person.setTelPersona(leer.nextLine());
        
        String tabla = "tb_contacto";
        String camposTabla = "nom_contacto, email_contacto, tel_contacto";
        // Esta linea es continua a la anterior
        String valoresCampos = "'" + person.getNomPersona() + "','" + person.getEmailPersona() + "','" + person.getTelPersona() + "'"; 
        // Intancia u objeto de la clase ConexionCRUD
        ConexionCRUD utilerias = new ConexionCRUD();
        // Se envia los parametros necesarios para guardar el registro al método guardarRegistros
        utilerias.guardarRegistros(tabla, camposTabla, valoresCampos);
        // Llama el método del menú principal
        MenuPrincipal.desplegarMenu();
}
} 