/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Bean.HorarioLaboratorio;
import java.sql.*;
import java.util.ArrayList;
import Util.Conexion_BD;
import Util.Enums.Dia;
import java.time.LocalTime;

/**
 *
 * @author brigi
 */
public class HorarioLaboratorioDAO {
    Connection cn = null;
    PreparedStatement pt = null;
    ResultSet rs = null;
    
    
    public int insertarHorarioLaboratorio(HorarioLaboratorio horarioLaboratorio) {
        int estado = 0;
        try {
            
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("INSERT INTO horario_laboratorio (numero_lab, asignatura, dia, horario_inicio, horario_fin, docente) VALUES (?,?,?,?,?,?);");
            pt.setInt(1, horarioLaboratorio.getNumeroLab());
            pt.setString(2, horarioLaboratorio.getAsignatura());
            pt.setString(3, horarioLaboratorio.getDia().name());
            pt.setTime(4, java.sql.Time.valueOf(horarioLaboratorio.getHorarioInicio()));
            pt.setTime(5, java.sql.Time.valueOf(horarioLaboratorio.getHorarioFin()));
            pt.setString(2, horarioLaboratorio.getDocente());
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
        } catch (Exception e){
            
        }
        
        return estado;
    }
    
    public int modificarHorarioLaboratorio(HorarioLaboratorio horarioLaboratorio) {
        int estado = 0;
        
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("UPDATE horario_laboratorio SET numero_lab = ?, asignatura = ?, dia = ?, horario_inicio = ?, horario_fin = ?, docente = ? WHERE id_horario = ?;");
            pt.setInt(1, horarioLaboratorio.getNumeroLab());
            pt.setString(2, horarioLaboratorio.getAsignatura());
            pt.setString(3, horarioLaboratorio.getDia().name());
            pt.setTime(4, java.sql.Time.valueOf(horarioLaboratorio.getHorarioInicio()));
            pt.setTime(5, java.sql.Time.valueOf(horarioLaboratorio.getHorarioFin()));
            pt.setString(6, horarioLaboratorio.getDocente());
            pt.setInt(7, horarioLaboratorio.getIdHorario());
            
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
            
        } catch (Exception e) {
            
        }
        
        return estado;
    }
    
    public int eliminarHorarioLaboratorio(HorarioLaboratorio horarioLaboratorio) {
        int estado = 0;
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("DELETE FROM horario_laboratorio WHERE id_horario = ?;");
            pt.setInt(1, horarioLaboratorio.getIdHorario());
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
            
        } catch(Exception e) {
            
        }
        
        return estado;
    }
    
    public ArrayList<HorarioLaboratorio> enlistarHorarioLaboratorio () {
        
        ArrayList<HorarioLaboratorio> listaHorarioLaboratorios = new ArrayList<>();
        
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("SELECT * FROM horario_laboratorio;");
            rs = pt.executeQuery();
            
            while (rs.next()) {
                HorarioLaboratorio horarioLaboratorio = new HorarioLaboratorio();
                horarioLaboratorio.setIdHorario(rs.getInt("id_horario"));
                horarioLaboratorio.setNumeroLab(rs.getInt("numero_lab"));
                horarioLaboratorio.setAsignatura(rs.getString("asignatura"));
                horarioLaboratorio.setDia(Dia.valueOf(rs.getString("dia")));
                horarioLaboratorio.setHorarioInicio(rs.getTime("horario_inicio").toLocalTime());
                horarioLaboratorio.setHorarioInicio(rs.getTime("horario_fin").toLocalTime());
                horarioLaboratorio.setDocente(rs.getString("docente"));
            
                listaHorarioLaboratorios.add(horarioLaboratorio);
            }
            
            cn.close();
            pt.close();
            rs.close();
            
        } catch (Exception e){
            
        }
        
        return listaHorarioLaboratorios;
    }
}
