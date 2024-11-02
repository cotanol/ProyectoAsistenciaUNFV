/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.*;
import Util.Conexion_BD;
import java.util.ArrayList;


public class AlumnoDAO {
    
    Connection cn = null;
    PreparedStatement pt = null;
    ResultSet rs = null;
    
    
    public int insertarAlumno(Alumno alumno) {
        int estado = 0;
        try {
            
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("INSERT INTO alumno (nombres, apellidos) VALUES (?,?);");
            pt.setString(1, alumno.getNombres());
            pt.setString(2, alumno.getApellidos());
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
        } catch (Exception e){
            
        }
        
        return estado;
    }
    
    public int modificarAlumno(Alumno alumno) {
        int estado = 0;
        
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("UPDATE alumno SET nombres = ?, apellidos = ? WHERE codigo_alumno = ?;");
            pt.setString(1, alumno.getNombres());
            pt.setString(2, alumno.getApellidos());
            pt.setInt(3, alumno.getCodigoAlumno());
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
            
        } catch (Exception e) {
            
        }
        
        return estado;
    }
    
    public int eliminarAlumno(Alumno alumno) {
        int estado = 0;
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("DELETE FROM alumno WHERE codigo_alumno = ?;");
            pt.setInt(1, alumno.getCodigoAlumno());
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
            
        } catch(Exception e) {
            
        }
        
        return estado;
    }
    
    public ArrayList<Alumno> enlistarAlumno () {
        
        ArrayList<Alumno> listaAlumnos = new ArrayList<>();
        
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("SELECT * FROM alumno;");
            rs = pt.executeQuery();
            
            while (rs.next()) {
                Alumno alumno = new Alumno();
                alumno.setCodigoAlumno(rs.getInt("codigo_alumno"));
                alumno.setNombres(rs.getString("nombres"));
                alumno.setApellidos(rs.getString("apellidos"));
                listaAlumnos.add(alumno);
            }
            
            cn.close();
            pt.close();
            rs.close();
            
        } catch (Exception e){
            
        }
        
        for (Alumno alumno: listaAlumnos) {
            System.out.println(alumno.getApellidos() + "\n" +alumno.getNombres() + "\n"+alumno.getCodigoAlumno());
        }
        
        return listaAlumnos;
    }
    
    public static void main(String[] args) { // Probando el codigo si muestra los datos de la tabla
        AlumnoDAO dao = new AlumnoDAO();
        dao.enlistarAlumno();
    }
}

