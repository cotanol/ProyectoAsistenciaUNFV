/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Bean.Usuario;
import Util.Conexion_BD;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author brigi
 */
public class UsuarioDAO {
    Connection cn = null;
    PreparedStatement pt = null;
    ResultSet rs = null;
    
    
    public int insertarUsuario(Usuario usuario) {
        int estado = 0;
        try {
            
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("INSERT INTO usuario (nombres, apellidos, tipo_documento, nro_documento, numero, cargo, nombre_usuario, contrasena, email)  VALUES (?,?,?,?,?,?,?,?,?);");
            pt.setString(1, usuario.getNombres());
            pt.setString(2, usuario.getApellidos());
            pt.setString(3, usuario.getTipoDocumento());
            pt.setString(4, usuario.getNroDocumento());
            pt.setString(5, usuario.getNumero());
            pt.setString(6, usuario.getCargo());
            pt.setString(7, usuario.getNombreUsuario());
            pt.setString(8, usuario.getContrasena());
            pt.setString(9, usuario.getEmail());
            
            
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
        } catch (Exception e){
            
        }
        
        return estado;
    }
    
    public int modificarUsuario(Usuario usuario) {
        int estado = 0;
        
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("UPDATE usuario SET nombres = ?, apellidos = ?, tipo_documento = ?, nro_documento = ?, numero = ?, cargo = ?, nombre_usuario = ?, contrasena = ?, email = ? WHERE usuario = ?;");
            pt.setString(1, usuario.getNombres());
            pt.setString(2, usuario.getApellidos());
            pt.setString(3, usuario.getTipoDocumento());
            pt.setString(4, usuario.getNroDocumento());
            pt.setString(5, usuario.getNumero());
            pt.setString(6, usuario.getCargo());
            pt.setString(7, usuario.getNombreUsuario());
            pt.setString(8, usuario.getContrasena());
            pt.setString(9, usuario.getEmail());
            pt.setInt(10, usuario.getIdUsuario());
            
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
            
        } catch (Exception e) {
            
        }
        
        return estado;
    }
    
    public int eliminarUsuario(Usuario usuario) {
        int estado = 0;
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("DELETE FROM usuario WHERE id_usuario = ?;");
            pt.setInt(1, usuario.getIdUsuario());
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
            
        } catch(Exception e) {
            
        }
        
        return estado;
    }
    
    public ArrayList<Usuario> enlistarUsuario () {
        
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("SELECT * FROM usuario;");
            rs = pt.executeQuery();
            
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNombres(rs.getString("nombres"));
                usuario.setApellidos(rs.getString("apellidos"));
                usuario.setTipoDocumento(rs.getString("tipo_documento"));
                usuario.setNroDocumento(rs.getString("nro_documento"));
                usuario.setNumero(rs.getString("numero"));
                usuario.setCargo(rs.getString("cargo"));
                usuario.setNombreUsuario(rs.getString("nombre_usuario"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setEmail(rs.getString("email"));
            
                listaUsuarios.add(usuario);
            }
            
            cn.close();
            pt.close();
            rs.close();
            
        } catch (Exception e){
            
        }
        
        return listaUsuarios;
    }
}
