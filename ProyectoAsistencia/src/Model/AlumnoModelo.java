package Model;
import java.sql.*;
import Util.Conexion_BD;
import java.util.ArrayList;
import View.VentanaPrincipal;

public class AlumnoModelo {
    private int codigoAlumno;
    private String nombres;
    private String apellidos;
    
    Connection cn = null;
    PreparedStatement pt = null;
    ResultSet rs = null;
    
    VentanaPrincipal objVentReg;
    
    public AlumnoModelo(VentanaPrincipal objventReg) {
        this.objVentReg = objVentReg;
    }
    
    public AlumnoModelo(){
        
    }
    public int insertarAlumnoModelo(AlumnoModelo alumnoModelo) {
        int estado = 0;
        try {
            
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("INSERT INTO alumno (nombres, apellidos) VALUES (?,?);");
            pt.setString(1, alumnoModelo.getNombres());
            pt.setString(2, alumnoModelo.getApellidos());
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
        } catch (Exception e){
            
        }
        
        return estado;
    }
    
    public int modificarAlumnoModelo(AlumnoModelo alumnoModelo) {
        int estado = 0;
        
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("UPDATE alumno SET nombres = ?, apellidos = ? WHERE codigo_alumno = ?;");
            pt.setString(1, alumnoModelo.getNombres());
            pt.setString(2, alumnoModelo.getApellidos());
            pt.setInt(3, alumnoModelo.getCodigoAlumno());
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
            
        } catch (Exception e) {
            
        }
        
        return estado;
    }
    
    public int eliminarAlumnoModelo(AlumnoModelo alumnoModelo) {
        int estado = 0;
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("DELETE FROM alumno WHERE codigo_alumno = ?;");
            pt.setInt(1, alumnoModelo.getCodigoAlumno());
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
            
        } catch(Exception e) {
            
        }
        
        return estado;
    }
    
    public ArrayList<AlumnoModelo> enlistarAlumnoModelo () {
        
        ArrayList<AlumnoModelo> listaAlumnos = new ArrayList<>();
        
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("SELECT * FROM alumno;");
            rs = pt.executeQuery();
            
            while (rs.next()) {
                AlumnoModelo alumnoModelo = new AlumnoModelo();
                alumnoModelo.setCodigoAlumno(rs.getInt("codigo_alumno"));
                alumnoModelo.setNombres(rs.getString("nombres"));
                alumnoModelo.setApellidos(rs.getString("apellidos"));
                listaAlumnos.add(alumnoModelo);
            }
            
            cn.close();
            pt.close();
            rs.close();
            
        } catch (Exception e){
            
        }
        
        
        return listaAlumnos;
    }
    
    public int ultimoId() {
        int id = 0; // Inicializa el ID con un valor predeterminado
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("SELECT MAX(codigo_alumno) AS max_id FROM alumno;");
            rs = pt.executeQuery();

            if (rs.next()) {
                id = rs.getInt("max_id"); // Obtén el máximo ID de la columna 'max_id'
            }

            // Cerrar recursos
            rs.close();
            pt.close();
            cn.close();

        } catch (Exception e) {  
        }

        return id; 
    }
    
    public int getCodigoAlumno() {
        return codigoAlumno;
    }

    public void setCodigoAlumno(int codigoAlumno) {
        this.codigoAlumno = codigoAlumno;
    }
    
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }    
}

