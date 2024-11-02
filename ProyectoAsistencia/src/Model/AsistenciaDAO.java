/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Util.Conexion_BD;
import java.sql.*;
import java.util.ArrayList;
import Util.Enums.EstadoAsistencia;
import java.time.LocalDate;

public class AsistenciaDAO {
    Connection cn = null;
    PreparedStatement pt = null;
    ResultSet rs = null;
    
    
    public int insertarAsistencia(Asistencia asistencia) {
        int estado = 0;
        try {
            
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("INSERT INTO asistencia (fecha, estado, numero_lab, codigo_alumno) VALUES (?,?,?,?);");
            pt.setDate(1, java.sql.Date.valueOf(asistencia.getFecha()));
            pt.setString(2, asistencia.getEstado().name());
            pt.setInt(3, asistencia.getNumeroLab());
            pt.setInt(4, asistencia.getCodigoAlumno());
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
        } catch (Exception e){
            
        }
        
        return estado;
    }
    
    public int modificarAsistencia(Asistencia asistencia) {
        int estado = 0;
        
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("UPDATE asistencia SET fecha = ?, estado = ?, numero_lab = ?, codigo_alumno = ? WHERE id_asistencia = ?;");
            pt.setDate(1, java.sql.Date.valueOf(asistencia.getFecha()));
            pt.setString(2, asistencia.getEstado().name());
            pt.setInt(3, asistencia.getNumeroLab());
            pt.setInt(4, asistencia.getCodigoAlumno());
            pt.setInt(5, asistencia.getIdAsistencia());
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
            
        } catch (Exception e) {
            
        }
        
        return estado;
    }
    
    public int eliminarAsistencia(Asistencia asistencia) {
        int estado = 0;
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("DELETE FROM asistencia WHERE id_asistencia = ?;");
            pt.setInt(1, asistencia.getIdAsistencia());
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
            
        } catch(Exception e) {
            
        }
        
        return estado;
    }
    
    public ArrayList<Asistencia> enlistarAsistencia () {
        
        ArrayList<Asistencia> listaAsistencias = new ArrayList<>();
        
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("SELECT * FROM asistencia;");
            rs = pt.executeQuery();
            
            while (rs.next()) {
                Asistencia asistencia = new Asistencia();
                asistencia.setIdAsistencia(rs.getInt("id_asistencia"));
                asistencia.setFecha(rs.getDate("fecha").toLocalDate());
                asistencia.setEstado(EstadoAsistencia.valueOf(rs.getString("estado")));
                asistencia.setNumeroLab(rs.getInt("numero_lab"));
                asistencia.setCodigoAlumno(rs.getInt("codigo_alumno"));
                listaAsistencias.add(asistencia);
            }
            
            cn.close();
            pt.close();
            rs.close();
            
        } catch (Exception e){
            
        }
        
        return listaAsistencias;
    }
    
    
}
