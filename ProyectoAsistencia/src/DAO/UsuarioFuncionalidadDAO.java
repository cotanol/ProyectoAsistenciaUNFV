/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Bean.UsuarioFuncionalidad;
import java.sql.*;
import java.util.ArrayList;
import Util.Conexion_BD;

/**
 *
 * @author brigi
 */
public class UsuarioFuncionalidadDAO {
    Connection cn = null;
    PreparedStatement pt = null;
    ResultSet rs = null;
    
    
    public int insertarUsuarioFuncionalidad(UsuarioFuncionalidad usuarioFuncionalidad) {
        int estado = 0;
        try {
            
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("INSERT INTO usuario_funcionalidad (id_usuario, numero_lab, funcionalidad)  VALUES (?,?,?);");
            pt.setInt(1, usuarioFuncionalidad.getIdUsuario());
            pt.setInt(2, usuarioFuncionalidad.getNumeroLab());
            pt.setString(3, usuarioFuncionalidad.getFuncionalidad());
            
            

            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
        } catch (Exception e){
            
        }
        
        return estado;
    }
    
    public int modificarUsuarioFuncionalidad(UsuarioFuncionalidad usuarioFuncionalidad) {
        int estado = 0;
        
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("UPDATE usuario_funcionalidad SET id_usuario = ?, numero_lab = ?, funcionalidad = ? WHERE id_usuario_funcion = ?;");
            pt.setInt(1, usuarioFuncionalidad.getIdUsuario());
            pt.setInt(2, usuarioFuncionalidad.getNumeroLab());
            pt.setString(3, usuarioFuncionalidad.getFuncionalidad());
            pt.setInt(4, usuarioFuncionalidad.getIdUsuarioFuncion());
            
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
            
        } catch (Exception e) {
            
        }
        
        return estado;
    }
    
    public int eliminarUsuarioFuncionalidad(UsuarioFuncionalidad usuarioFuncionalidad) {
        int estado = 0;
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("DELETE FROM usuario_funcionalidad WHERE id_usuario_funcion = ?;");
            pt.setInt(1, usuarioFuncionalidad.getIdUsuarioFuncion());
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
            
        } catch(Exception e) {
            
        }
        
        return estado;
    }
    
    public ArrayList<UsuarioFuncionalidad> enlistarUsuarioFuncionalidad () {
        
        ArrayList<UsuarioFuncionalidad> listaUsuarioFuncionalidades = new ArrayList<>();
        
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("SELECT * FROM usuario_funcionalidad;");
            rs = pt.executeQuery();
            
            while (rs.next()) {
                UsuarioFuncionalidad usuarioFuncionalidad = new UsuarioFuncionalidad();
                usuarioFuncionalidad.setIdUsuarioFuncion(rs.getInt("id_usuario_funcion"));
                usuarioFuncionalidad.setIdUsuario(rs.getInt("id_usuario"));
                usuarioFuncionalidad.setNumeroLab(rs.getInt("numero_lab"));
                usuarioFuncionalidad.setFuncionalidad(rs.getString("funcionalidad"));
                
            
                listaUsuarioFuncionalidades.add(usuarioFuncionalidad);
            }
            
            cn.close();
            pt.close();
            rs.close();
            
        } catch (Exception e){
            
        }
        
        return listaUsuarioFuncionalidades;
    }
}
