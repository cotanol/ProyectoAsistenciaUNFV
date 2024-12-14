
package Util;
import java.sql.*;


public class Conexion_BD {

    public static Connection getConexionBD() {
        
        Connection cn = null;
        
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            String url = "jdbc:mysql://localhost:3306/unfv_bd";
            String user = "root";
            String password = ""; 

            cn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexion exitosa a la base de datos!");

        } catch (Exception e) {
            System.out.println("No se pudo conectar a la base de datos...");
        }
        return cn;
    }
    
    public static void main(String[] args) {
        getConexionBD();
    }
    
}
