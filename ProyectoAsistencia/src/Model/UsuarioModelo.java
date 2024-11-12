/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import Util.Conexion_BD;
import java.sql.*;
import java.util.ArrayList;
import View.Ventana01RegistrosDeUsuarios;

/**
 *
 * @author brigi
 */
public class UsuarioModelo {
    private int idUsuario;
    private String nombres;
    private String apellidos;
    private String tipoDocumento;
    private String nroDocumento;
    private String numero;
    private String Cargo;
    private String nombreUsuario;
    private String contrasena;
    private String email;
    
    Ventana01RegistrosDeUsuarios objVentReg;
    
    Connection cn = null;
    PreparedStatement pt = null;
    ResultSet rs = null;
    
    public UsuarioModelo(Ventana01RegistrosDeUsuarios objventReg) {
        this.objVentReg = objVentReg;
    }
    
    public UsuarioModelo() {
        
    }
    
    public int insertarUsuarioModelo(UsuarioModelo usuarioModelo) {
        int estado = 0;
        try {
            
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("INSERT INTO usuario (nombres, apellidos, tipo_documento, nro_documento, numero, cargo, nombre_usuario, contrasena, email)  VALUES (?,?,?,?,?,?,?,?,?);");
            pt.setString(1, usuarioModelo.getNombres());
            pt.setString(2, usuarioModelo.getApellidos());
            pt.setString(3, usuarioModelo.getTipoDocumento());
            pt.setString(4, usuarioModelo.getNroDocumento());
            pt.setString(5, usuarioModelo.getNumero());
            pt.setString(6, usuarioModelo.getCargo());
            pt.setString(7, usuarioModelo.getNombreUsuario());
            pt.setString(8, usuarioModelo.getContrasena());
            pt.setString(9, usuarioModelo.getEmail());
            
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
        } catch (Exception e){
            
        }
        
        return estado;
    }
    
    public int modificarUsuarioModelo(UsuarioModelo usuarioModelo) {
        int estado = 0;
        
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("UPDATE usuario SET nombres = ?, apellidos = ?, tipo_documento = ?, nro_documento = ?, numero = ?, cargo = ?, nombre_usuario = ?, contrasena = ?, email = ? WHERE id_usuario = ?;");
            pt.setString(1, usuarioModelo.getNombres());
            pt.setString(2, usuarioModelo.getApellidos());
            pt.setString(3, usuarioModelo.getTipoDocumento());
            pt.setString(4, usuarioModelo.getNroDocumento());
            pt.setString(5, usuarioModelo.getNumero());
            pt.setString(6, usuarioModelo.getCargo());
            pt.setString(7, usuarioModelo.getNombreUsuario());
            pt.setString(8, usuarioModelo.getContrasena());
            pt.setString(9, usuarioModelo.getEmail());
            pt.setInt(10, usuarioModelo.getIdUsuario());
            
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
            
        } catch (Exception e) {
            
        }
        
        return estado;
    }
    
    public int eliminarUsuarioModelo(UsuarioModelo usuarioModelo) {
        int estado = 0;
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("DELETE FROM usuario WHERE id_usuario = ?;");
            pt.setInt(1, usuarioModelo.getIdUsuario());
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
            
        } catch(Exception e) {
            
        }
        
        return estado;
    }
    
    public ArrayList<UsuarioModelo> enlistarUsuarioModelo () {
        
        ArrayList<UsuarioModelo> listaUsuarios = new ArrayList<>();
        
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("SELECT * FROM usuario;");
            rs = pt.executeQuery();
            
            while (rs.next()) {
                UsuarioModelo usuarioModelo = new UsuarioModelo();
                usuarioModelo.setIdUsuario(rs.getInt("id_usuario"));
                usuarioModelo.setNombres(rs.getString("nombres"));
                usuarioModelo.setApellidos(rs.getString("apellidos"));
                usuarioModelo.setTipoDocumento(rs.getString("tipo_documento"));
                usuarioModelo.setNroDocumento(rs.getString("nro_documento"));
                usuarioModelo.setNumero(rs.getString("numero"));
                usuarioModelo.setCargo(rs.getString("cargo"));
                usuarioModelo.setNombreUsuario(rs.getString("nombre_usuario"));
                usuarioModelo.setContrasena(rs.getString("contrasena"));
                usuarioModelo.setEmail(rs.getString("email"));
            
                listaUsuarios.add(usuarioModelo);
            }
            
            cn.close();
            pt.close();
            rs.close();
            
        } catch (Exception e){
            
        }
        
        return listaUsuarios;
    }
    
    public int ultimoId() {
        int id = 0; // Inicializa el ID con un valor predeterminado
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("SELECT MAX(id_usuario) AS max_id FROM usuario;");
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

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCargo() {
        return Cargo;
    }

    public void setCargo(String Cargo) {
        this.Cargo = Cargo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
}
