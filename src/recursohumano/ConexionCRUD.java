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
// Método para actualizar y eliminar los registros de la base de datos 
public void actualizarEliminarRegistros(String tabla, String valoresCamposNuevos, String condicion){
    // Cargar la conexión 
    ConexionCRUD conectar = new ConexionCRUD();
    Connection cone = conectar.getConnection();
    try{
        Statement stmt;
        String sqlQueryStmt;
        // Verificar que los valoresCamposNuevos venga vacia y asi seleccionar si es borrar o actualizar registro
        if(valoresCamposNuevos.isEmpty()){
            sqlQueryStmt = "DELETE FROM " + tabla + " WHERE " + condicion + ";";
        }else{
            sqlQueryStmt = "UPDATE " + tabla + " SET " + valoresCamposNuevos + " WHERE " + condicion + ";";
        }
        stmt = cone.createStatement();
        stmt.executeUpdate(sqlQueryStmt);
        stmt.close();
        cone.close();
    }catch(SQLException ex){
        System.out.println("Ha ocurrido el siguiente error: " + ex.getMessage());
    }
}
  // Método para hacer las consultas en los registros de la base de datos 
public void desplegarRegistros(String tablaBuscar, String camposBuscar, String condicionBuscar) throws SQLException{
    // Cargar la conexión 
    ConexionCRUD conectar = new ConexionCRUD();
    Connection cone = conectar.getConnection();
    try {
        Statement stmt; 
        String sqlQueryStmt; 
        if(condicionBuscar.equals("")){
            sqlQueryStmt = "SELECT " + camposBuscar + " FROM " + tablaBuscar + ";";
        }else{
            sqlQueryStmt = "SELECT " + camposBuscar + " FROM " + tablaBuscar + " WHERE " + condicionBuscar;
        }
        stmt = cone.createStatement();
        stmt.executeQuery(sqlQueryStmt);
        // Le indicamos que se ejecute la consulta de la tabla y le pasamos por argumentos nuestra sentencia 
        try(ResultSet miResultSet = stmt. executeQuery(sqlQueryStmt)){
            if (miResultSet.next()){ // Ubica el cursos en la primera fila de la tabla de resultado
                ResultSetMetaData metaData = miResultSet.getMetaData();
                int numColumnas = metaData.getColumnCount(); // Obtiene el número de columnas de la consulta 
                System.out.println(" << REGISTROS ALMACENADOS >> ");
                System.out.println("");
                for (int i = 1; i <= numColumnas; i++){
                    // Muestra los titulos de la columnas y %-20s\t indica la seáración entre columnas 
                    System.out.printf("%-20s\t", metaData.getColumnName(i));
                }
                System.out.println("");
                do {
                    for (int i = 1; i <= numColumnas; i++) {
                        System.out.printf("%-20s\t", miResultSet.getObject(i));
                    }
                    System.out.println("");
                } while (miResultSet.next());
                System.out.println("");
        } else {
                System.out.println("No se han encontrado registros");
            }
            miResultSet.close();   // Crear el ResultSet
        }finally{
        // Craear el Statement y la conexión; se cierra en orden inverso de como se han abierto 
        stmt.close();
        cone.close();
    } 
    } catch (SQLException ex) {
        System.out.println("Ha ocurrido el siguiente error: " + ex.getMessage());
    }
}
}