/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.sql.*;
import java.util.ArrayList;
import Util.Conexion_BD;

/**
 *
 * @author brigi
 */
public class ReporteDAO {
    Connection cn = null;
    PreparedStatement pt = null;
    ResultSet rs = null;
    
    
    public int insertarReporte(Reporte reporte) {
        int estado = 0;
        try {
            
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("INSERT INTO reporte (ano, mes, asistencia_total, numero_lab) VALUES (?,?,?,?);");
            pt.setInt(1, reporte.getAno());
            pt.setInt(2, reporte.getMes());
            pt.setInt(3, reporte.getAsistenciaTotal());
            pt.setInt(4, reporte.getNumeroLab());
            
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
        } catch (Exception e){
            
        }
        
        return estado;
    }
    
    public int modificarReporte(Reporte reporte) {
        int estado = 0;
        
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("UPDATE reporte SET ano = ?, mes = ?, asistencia_total = ?, numero_lab = ? WHERE id_reporte = ?;");
            pt.setInt(1, reporte.getAno());
            pt.setInt(2, reporte.getMes());
            pt.setInt(3, reporte.getAsistenciaTotal());
            pt.setInt(4, reporte.getNumeroLab());
            pt.setInt(5, reporte.getIdReporte());
            
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
            
        } catch (Exception e) {
            
        }
        
        return estado;
    }
    
    public int eliminarReporte(Reporte reporte) {
        int estado = 0;
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("DELETE FROM horario_laboratorio WHERE id_reporte = ?;");
            pt.setInt(1, reporte.getIdReporte());
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
            
        } catch(Exception e) {
            
        }
        
        return estado;
    }
    
    public ArrayList<Reporte> enlistarReporte () {
        
        ArrayList<Reporte> listaReportes = new ArrayList<>();
        
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("SELECT * FROM reporte;");
            rs = pt.executeQuery();
            
            while (rs.next()) {
                Reporte reporte = new Reporte();
                reporte.setIdReporte(rs.getInt("id_reporte"));
                reporte.setAno(rs.getInt("ano"));
                reporte.setMes(rs.getInt("mes"));
                reporte.setAsistenciaTotal(rs.getInt("asistencia_total"));
                reporte.setNumeroLab(rs.getInt("numero_lab"));
                
            
                listaReportes.add(reporte);
            }
            
            cn.close();
            pt.close();
            rs.close();
            
        } catch (Exception e){
            
        }
        
        return listaReportes;
    }
}
