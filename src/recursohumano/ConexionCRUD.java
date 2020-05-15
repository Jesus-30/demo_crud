package recursohumano;

import java.sql.*;    // Librerias para la conexion de la Base de datos 

public class ConexionCRUD {
    /*Ruta de la base de datos, el servidos 127.0.0.1, el puertyo 3306
    y el nombre de la base de datos db_recurso_humano*/
    private final String servidor = "jdbc:mysql://127.0.0.1:3306/bd_recurso_humano";
    // Nombre del usuario (root por defecto) de la base de datos 
    private final String usuario = "root";
    // Clave del usuario de la base de datos 
    private final String clave = "";
    // Libreria de MySQL 
    private final String driverConector = "com.mysql.jdbc.Driver";
    // Objeto de la clase Connection del paquete java.sql
    private static Connection conexion;
    
    // Conexioón a BD con las variables
public ConexionCRUD() {
    try{
        Class.forName(driverConector);  // Levantar el Driver 
        // Establecer Conexión 
        conexion = DriverManager.getConnection(servidor, usuario, clave);
    }catch(ClassNotFoundException | SQLException e){
        System.out.println("Conexión fallida!! Error!! : " + e.getMessage());
    }
}
public Connection getConnection(){
    return conexion;    // Devuelve el objeto conexión 
}

// Creación de métodos para guardar5 los registros
public void guardarRegistros(String tabla, String camposTabla, String valoresCampos){
    // Cargar la conexión 
    ConexionCRUD conectar = new ConexionCRUD();
    Connection cone = conectar.getConnection();
    try{
        // Definir la sentencia SQL 
        String sqlQueryStmt = "INSERT INTO " + tabla + " (" + camposTabla + ") VALUES (" + valoresCampos + ");";
        // Establecemos la comunicación entre nuresta aplicación JAVA y la base de datos 
        Statement stmt; // Emvia sentencia SQL a la base de datos 
        stmt = cone.createStatement();
        stmt.executeUpdate(sqlQueryStmt); // Ejecutar la senterncia SQL 
        // Cerrar el Statement y la Conexión; se cierra en orden inverso de como se han abierto 
        stmt.close();
        cone.close();
        System.out.println("Registro guardado correctamente!");
    }catch(Exception e){
        System.out.println(e.getMessage());
    }
}