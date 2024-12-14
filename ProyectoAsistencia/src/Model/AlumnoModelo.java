package Model;
import java.sql.*;
import Util.Conexion_BD;
import java.util.ArrayList;
import View.VentanaPrincipal;

public class AlumnoModelo {
    private int id_alumno;
    private String codigoAlumno;
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
            pt = cn.prepareStatement("INSERT INTO alumno (codigo_alumno, nombres, apellidos) VALUES (?,?,?);");
            pt.setString(1, alumnoModelo.getCodigoAlumno());
            pt.setString(2, alumnoModelo.getNombres());
            pt.setString(3, alumnoModelo.getApellidos());
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
        } catch (Exception e){
            System.err.println("ERROR: " + e);
        }
        
        return estado;
    }
    
    public int modificarAlumnoModelo(String nuevo, String nombre, String apellido, String antiguo) {
        int estado = 0;
        
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("UPDATE alumno SET codigo_alumno = ?, nombres = ?, apellidos = ? WHERE codigo_alumno = ?;");
            pt.setString(1, nuevo);
            pt.setString(2, nombre);
            pt.setString(3, apellido);
            pt.setString(4, antiguo);
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
            
        } catch (Exception e) {
            System.err.println("ERROR: " + e);
        }
        
        return estado;
    }
    
    public int eliminarAlumnoModelo(AlumnoModelo alumnoModelo) {
        int estado = 0;
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("DELETE FROM alumno WHERE codigo_alumno = ?;");
            pt.setString(1, alumnoModelo.getCodigoAlumno());
            
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
                alumnoModelo.setId_alumno(rs.getInt("id_alumno"));
                alumnoModelo.setCodigoAlumno(rs.getString("codigo_alumno"));
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
    
    public ArrayList<AlumnoModelo> buscarResgistroAlumno(String buscar) {
        ArrayList<AlumnoModelo> listaAlumnos = new ArrayList<>();

        try {
            
            String sql = "SELECT * FROM alumno WHERE "
                       + "codigo_alumno LIKE ? OR "
                       + "nombres LIKE ? OR "
                       + "apellidos LIKE ?;";
                    
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement(sql);

            for (int i = 1; i <= 3; i++) {
                pt.setString(i, "%" + buscar + "%");
            }

            rs = pt.executeQuery();

            while (rs.next()) {
                AlumnoModelo alumnoModelo = new AlumnoModelo();
                alumnoModelo.setCodigoAlumno(rs.getString("codigo_alumno"));
                alumnoModelo.setNombres(rs.getString("nombres"));
                alumnoModelo.setApellidos(rs.getString("apellidos"));


                listaAlumnos.add(alumnoModelo);
            }

            rs.close();
            pt.close();
            cn.close();

        } catch (Exception e) {
            System.err.println("Error: " + e);
        }

        return listaAlumnos;
    }
    
    public int ultimoId() {
        int id = 0; 
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("SELECT MAX(codigo_alumno) AS max_id FROM alumno;");
            rs = pt.executeQuery();

            if (rs.next()) {
                id = rs.getInt("max_id"); 
            }

            
            rs.close();
            pt.close();
            cn.close();

        } catch (Exception e) {  
        }

        return id; 
    }

    public int getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(int id_alumno) {
        this.id_alumno = id_alumno;
    }

    public String getCodigoAlumno() {
        return codigoAlumno;
    }

    public void setCodigoAlumno(String codigoAlumno) {
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

