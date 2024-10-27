/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Bean.Equipo;
import java.sql.*;
import java.util.ArrayList;
import Util.Conexion_BD;
import Util.Enums.EstadoEquipo;

/**
 *
 * @author brigi
 */
public class EquipoDAO {
    Connection cn = null;
    PreparedStatement pt = null;
    ResultSet rs = null;
    
    
    public int insertarEquipo(Equipo equipo) {
        int estado = 0;
        try {
            
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("INSERT INTO equipo (numero_lab, tipo_equipo, numero_serie, estado) VALUES (?,?,?,?);");
            pt.setInt(1, equipo.getNumeroLab());
            pt.setString(2, equipo.getTipoEquipo());
            pt.setString(3, equipo.getNumeroSerie());
            pt.setString(4, equipo.getEstado().name());
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
        } catch (Exception e){
            
        }
        
        return estado;
    }
    
    public int modificarEquipo(Equipo equipo) {
        int estado = 0;
        
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("UPDATE equipo SET numero_lab = ?, tipo_equipo = ?, numero_serie = ?, estado = ? WHERE cod_patrimonial = ?;");
            pt.setInt(1, equipo.getNumeroLab());
            pt.setString(2, equipo.getTipoEquipo());
            pt.setString(3, equipo.getNumeroSerie());
            pt.setString(4, equipo.getEstado().name());
            pt.setInt(5, equipo.getCodPatrimonial());
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
            
        } catch (Exception e) {
            
        }
        
        return estado;
    }
    
    public int eliminarEquipo(Equipo equipo) {
        int estado = 0;
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("DELETE FROM equipo WHERE cod_patrimonial = ?;");
            pt.setInt(1, equipo.getCodPatrimonial());
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
            
        } catch(Exception e) {
            
        }
        
        return estado;
    }
    
    public ArrayList<Equipo> enlistarAsistencia () {
        
        ArrayList<Equipo> listaEquipos = new ArrayList<>();
        
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("SELECT * FROM equipo;");
            rs = pt.executeQuery();
            
            while (rs.next()) {
                Equipo equipo = new Equipo();
                equipo.setCodPatrimonial(rs.getInt("cod_patrimonial"));
                equipo.setNumeroLab(rs.getInt("numero_lab"));
                equipo.setTipoEquipo(rs.getString("tipo_equipo"));
                equipo.setNumeroSerie(rs.getString("numero_lab"));
                equipo.setEstado(EstadoEquipo.valueOf(rs.getString("estado")));
                listaEquipos.add(equipo);
            }
            
            cn.close();
            pt.close();
            rs.close();
            
        } catch (Exception e){
            
        }
        
        return listaEquipos;
    }
}
