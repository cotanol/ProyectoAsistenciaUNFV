/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.util.ArrayList;
import java.sql.*;
import Util.Conexion_BD;

/**
 *
 * @author brigi
 */
public class LaboratorioDAO {
    Connection cn = null;
    PreparedStatement pt = null;
    ResultSet rs = null;
    
    
    public int insertarLaboratorio(Laboratorio laboratorio) {
        int estado = 0;
        try {
            
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("INSERT INTO laboratorio (capacidad) VALUES (?);");
            pt.setInt(1, laboratorio.getCapacidad());
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
        } catch (Exception e){
            
        }
        
        return estado;
    }
    
    public int modificarLaboratorio(Laboratorio laboratorio) {
        int estado = 0;
        
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("UPDATE laboratorio SET capacidad = ? WHERE id_horario = ?;");
            pt.setInt(1, laboratorio.getCapacidad());
            pt.setInt(2, laboratorio.getNumeroLab());
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
            
        } catch (Exception e) {
            
        }
        
        return estado;
    }
    
    public int eliminarLaboratorio(Laboratorio laboratorio) {
        int estado = 0;
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("DELETE FROM laboratorio WHERE numero_lab = ?;");
            pt.setInt(1, laboratorio.getNumeroLab());
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
            
        } catch(Exception e) {
            
        }
        
        return estado;
    }
    
    public ArrayList<Laboratorio> enlistarLaboratorio () {
        
        ArrayList<Laboratorio> listaLaboratorios = new ArrayList<>();
        
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("SELECT * FROM laboratorio;");
            rs = pt.executeQuery();
            
            while (rs.next()) {
                Laboratorio laboratorio = new Laboratorio();
                laboratorio.setNumeroLab(rs.getInt("numero_lab"));
                laboratorio.setCapacidad(rs.getInt("capacidad"));
            
                listaLaboratorios.add(laboratorio);
            }
            
            cn.close();
            pt.close();
            rs.close();
            
        } catch (Exception e){
            
        }
        
        return listaLaboratorios;
    }
}
